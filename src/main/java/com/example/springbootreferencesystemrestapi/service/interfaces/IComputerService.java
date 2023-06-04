package com.example.springbootreferencesystemrestapi.service.interfaces;

import com.example.springbootreferencesystemrestapi.api.models.ComponentApiModel;
import com.example.springbootreferencesystemrestapi.api.models.ComputerApiModel;
import com.example.springbootreferencesystemrestapi.models.Computer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface IComputerService{
    ComputerApiModel Create(ComputerApiModel entity);
    ComputerApiModel GetById(int id);
    ArrayList<ComputerApiModel> GetAll();
    ComputerApiModel Update(ComputerApiModel entity);
    boolean Delete(int id);
    boolean CreateFromFile(List<Computer> computers);
}
