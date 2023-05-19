package com.example.springbootreferencesystemrestapi.service;

import com.example.springbootreferencesystemrestapi.api.models.ComponentApiModel;
import com.example.springbootreferencesystemrestapi.api.models.ComputerApiModel;
import com.example.springbootreferencesystemrestapi.models.Component;
import com.example.springbootreferencesystemrestapi.models.Computer;
import com.example.springbootreferencesystemrestapi.repository.IComponentRepository;
import com.example.springbootreferencesystemrestapi.repository.IComputerRepository;
import com.example.springbootreferencesystemrestapi.service.interfaces.IComponentService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        _repository.save(model);
        return entity;
    }

    @Override
    public ComponentApiModel GetById(int id) {
        var result = _repository.findById(id);
        if(result.isEmpty()) return null;
        return _mapper.map(result.get(), ComponentApiModel.class);
    }

    @Override
    public ArrayList<ComponentApiModel> GetAll() {
        var result = _repository.findAll();
        if(result.isEmpty()) return null;
        ArrayList<ComponentApiModel> apiModels = new ArrayList<>();
        result.forEach(computer -> {
            ComponentApiModel apiModel = _mapper.map(computer, ComponentApiModel.class);
            apiModels.add(apiModel);
        });
        return apiModels;
    }

    @Override
    public ArrayList<ComponentApiModel> GetComponentsByComputerId(int computerId) {
        var result = _repository.findByComputerId(computerId);
        if(result.isEmpty()) return null;
        ArrayList<ComponentApiModel> apiModels = new ArrayList<>();
        result.forEach(computer -> {
            ComponentApiModel apiModel = _mapper.map(computer, ComponentApiModel.class);
            apiModels.add(apiModel);
        });
        return apiModels;
    }

    @Override
    public ComponentApiModel Update(ComponentApiModel entity) {
        //check if entity exists in db
        var entityNotExists = _repository.findById(entity.getId()).isEmpty();
        if(entityNotExists) return null;

        var model = _mapper.map(entity, Component.class);
        model = AddComputerToModel(entity);
        var result = _repository.save(model);
        return _mapper.map(result, ComponentApiModel.class);
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
            }
        }
        else{
            model.setFree(true);
        }
        return model;
    }
}
