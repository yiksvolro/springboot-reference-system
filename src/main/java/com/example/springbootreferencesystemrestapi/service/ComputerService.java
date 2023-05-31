package com.example.springbootreferencesystemrestapi.service;


import com.example.springbootreferencesystemrestapi.api.models.ComponentApiModel;
import com.example.springbootreferencesystemrestapi.api.models.ComputerApiModel;
import com.example.springbootreferencesystemrestapi.models.Computer;
import com.example.springbootreferencesystemrestapi.repository.IComputerRepository;
import com.example.springbootreferencesystemrestapi.service.interfaces.IComputerService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service()
public class ComputerService implements IComputerService {

    private final IComputerRepository _repository;
    private final Mapper _mapper;

    public ComputerService(IComputerRepository repository) {
        _repository = repository;
        _mapper = DozerBeanMapperBuilder.buildDefault();
    }

    @Override
    public ComputerApiModel Create(ComputerApiModel entity) {
        var result = _mapper.map(entity, Computer.class);
        _repository.save(result);
        return _mapper.map(result, ComputerApiModel.class);
    }

    @Override
    public ComputerApiModel GetById(int id) {
        var result = _repository.findById(id);
        if(result.isEmpty()) return null;
        return _mapper.map(result.get(), ComputerApiModel.class);
    }

    @Override
    public ArrayList<ComputerApiModel> GetAll() {
        var result = _repository.findAll();
        if(result.isEmpty()) return null;
        ArrayList<ComputerApiModel> apiModels = new ArrayList<ComputerApiModel>();
        result.forEach(computer -> {
            ComputerApiModel apiModel = _mapper.map(computer, ComputerApiModel.class);
            apiModels.add(apiModel);
        });
        return apiModels;
    }

    @Override
    public ComputerApiModel Update(ComputerApiModel entity) {
        //check if entity exists in db
        var entityNotExists = _repository.findById(entity.getId()).isEmpty();
        if(entityNotExists) return null;

        var model = _mapper.map(entity, Computer.class);
        var result = _repository.save(model);
        return _mapper.map(result, ComputerApiModel.class);
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
}
