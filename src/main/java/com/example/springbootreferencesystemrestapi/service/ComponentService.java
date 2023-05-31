package com.example.springbootreferencesystemrestapi.service;

import com.example.springbootreferencesystemrestapi.api.models.ComponentApiModel;
import com.example.springbootreferencesystemrestapi.api.models.ComputerApiModel;
import com.example.springbootreferencesystemrestapi.helpers.SearchType;
import com.example.springbootreferencesystemrestapi.models.Component;
import com.example.springbootreferencesystemrestapi.models.Computer;
import com.example.springbootreferencesystemrestapi.repository.IComponentRepository;
import com.example.springbootreferencesystemrestapi.repository.IComputerRepository;
import com.example.springbootreferencesystemrestapi.service.interfaces.IComponentService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service()
public class ComponentService implements IComponentService {
    private final IComponentRepository _repository;
    private final IComputerRepository _computerRepository;
    private final Mapper _mapper;
    public ComponentService(IComponentRepository repository, IComputerRepository computerRepository){
        _repository = repository;
        _computerRepository = computerRepository;
        _mapper = DozerBeanMapperBuilder.buildDefault();
    }
    @Override
    public ComponentApiModel Create(ComponentApiModel entity) {
        var model = _mapper.map(entity, Component.class);
        model = AddComputerToModel(entity);

        var result = _repository.save(model);
        var newModel = _mapper.map(result, ComponentApiModel.class);
        if(result.getComputer() != null){
            newModel.setComputerId(result.getComputer().getId());
        }
        return newModel;
    }

    @Override
    public ComponentApiModel GetById(int id) {
        var result = _repository.findById(id);
        if(result.isEmpty()) return null;
        var model = _mapper.map(result.get(), ComponentApiModel.class);
        if(result.get().getComputer() != null){
            model.setComputerId(result.get().getComputer().getId());
        }
        return model;
    }

    @Override
    public ArrayList<ComponentApiModel> GetAll() {
        var result = _repository.findAll();
        if(result.isEmpty()) return null;
        ArrayList<ComponentApiModel> apiModels = CreateListOfComponents(result);
        return apiModels;
    }

    @Override
    public ArrayList<ComponentApiModel> GetBy(String parameter, SearchType searchType) {

        var result = FindData(parameter, searchType);
        if(result.isEmpty()) return null;
        ArrayList<ComponentApiModel> apiModels = CreateListOfComponents(result);
        return apiModels;
    }

    @Override
    public ComponentApiModel Update(ComponentApiModel entity) {
        Component existingComponent = _repository.findById(entity.getId()).orElse(null);
        if (existingComponent != null) {
            // Оновлення полів компонента
            existingComponent.setType(entity.getType());
            existingComponent.setBrand(entity.getBrand());
            existingComponent.setInventoryNumber(entity.getInventoryNumber());

            int computerId = 0;
            // Перевірка, чи компонент пов'язаний з комп'ютером
            if (entity.getComputerId() != 0) {
                // Встановлення нового зв'язку з комп'ютером
                existingComponent.setComputer(_computerRepository.findById(entity.getComputerId()).get());
                computerId = existingComponent.getComputer().getId();
            } else {
                // Видалення зв'язку з комп'ютером, якщо компонент не пов'язаний з жодним комп'ютером
                existingComponent.setComputer(null);
            }

            // Збереження оновленого компонента
            var result = _mapper.map(_repository.save(existingComponent), ComponentApiModel.class);
            result.setComputerId(computerId);
            return result;
        }
        return null;
    }

    @Override
    public boolean Delete(int id) {
        try{
            _repository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    private Component AddComputerToModel(ComponentApiModel entity){
        var model = _mapper.map(entity, Component.class);
        if(entity.getComputerId() != 0){
            var computer = _computerRepository.findById(entity.getComputerId());
            if(computer.isPresent()){
                model.setComputer(computer.get());
                model.setFree(false);
                computer.get().addComponent(model);
            }
        }
        else{
            model.setFree(true);
        }
        return model;
    }
    private List<Component> FindData(String parameter, SearchType searchType) {
        List<Component> result = null;
        switch (searchType) {
            case Type:
                result = _repository.findByType(parameter);
                break;
            case InventoryNumber:
                result = _repository.findByInventoryNumber(parameter);
                break;
            case Brand:
                result = _repository.findByBrand(parameter);
                break;
            case Free:
                result = _repository.findByIsFree(true);
                break;
            case NonFree:
                result = _repository.findByIsFree(false);
                break;
            case ComputerId:
                result = _repository.findByComputerId(Integer.parseInt(parameter));
                break;
            default:
                result = null;
                break;
        }
        return result;
    }
    private ArrayList<ComponentApiModel> CreateListOfComponents(List<Component> components){
        var apiModels = new ArrayList<ComponentApiModel>();
        components.forEach(component -> {
            ComponentApiModel apiModel = _mapper.map(component, ComponentApiModel.class);
            if(component.getComputer() != null){
                apiModel.setComputerId(component.getComputer().getId());
            }
            apiModels.add(apiModel);
        });
        return apiModels;
    }
}
