package com.example.springbootreferencesystemrestapi.service.interfaces;

import com.example.springbootreferencesystemrestapi.api.models.ComponentApiModel;
import com.example.springbootreferencesystemrestapi.api.models.ComputerApiModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface IComputerService{
    ComputerApiModel Create(ComputerApiModel entity);
    ComputerApiModel GetById(int id);
    ArrayList<ComputerApiModel> GetAll();
    ComputerApiModel Update(ComputerApiModel entity);
    boolean Delete(int id);
}
