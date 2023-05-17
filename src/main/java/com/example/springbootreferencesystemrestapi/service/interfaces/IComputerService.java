package com.example.springbootreferencesystemrestapi.service.interfaces;

import com.example.springbootreferencesystemrestapi.api.models.ComputerApiModel;
import org.springframework.stereotype.Service;

@Service
public interface IComputerService{
    ComputerApiModel Create(ComputerApiModel entity);
    ComputerApiModel GetById(int id);
    ComputerApiModel GetAll();
    ComputerApiModel Update(ComputerApiModel entity);
    void Delete(int id);
}
