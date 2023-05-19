package com.example.springbootreferencesystemrestapi.service.interfaces;

import com.example.springbootreferencesystemrestapi.api.models.ComponentApiModel;
import com.example.springbootreferencesystemrestapi.api.models.ComputerApiModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public interface IComponentService {
    ComponentApiModel Create(ComponentApiModel entity);
    ComponentApiModel GetById(int id);
    ArrayList<ComponentApiModel> GetAll();
    ArrayList<ComponentApiModel> GetComponentsByComputerId(int computerId);
    ComponentApiModel Update(ComponentApiModel entity);
    boolean Delete(int id);
}
