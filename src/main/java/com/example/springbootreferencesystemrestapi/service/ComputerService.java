package com.example.springbootreferencesystemrestapi.service;


import com.example.springbootreferencesystemrestapi.api.models.ComputerApiModel;
import com.example.springbootreferencesystemrestapi.models.Computer;
import com.example.springbootreferencesystemrestapi.repository.IComputerRepository;
import com.example.springbootreferencesystemrestapi.service.interfaces.IComputerService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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
        return entity;
    }

    @Override
    public ComputerApiModel GetById(int id) {
        var result = _repository.findById(id);
        if(result.isEmpty()) return null;
        return _mapper.map(result, ComputerApiModel.class);
    }

    @Override
    public ComputerApiModel GetAll() {
        return null;
    }

    @Override
    public ComputerApiModel Update(ComputerApiModel entity) {
        return null;
    }

    @Override
    public void Delete(int id) {

    }
}
