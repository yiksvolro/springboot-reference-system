package com.example.springbootreferencesystemrestapi.service.interfaces;

import com.example.springbootreferencesystemrestapi.api.models.ComponentApiModel;
import com.example.springbootreferencesystemrestapi.api.models.ComputerApiModel;
import com.example.springbootreferencesystemrestapi.helpers.SearchType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public interface IComponentService {
    ComponentApiModel Create(ComponentApiModel entity);
    ComponentApiModel GetById(int id);
    ArrayList<ComponentApiModel> GetAll();
    ArrayList<ComponentApiModel> GetBy(String parameter, SearchType searchType);
    ComponentApiModel Update(ComponentApiModel entity);
    boolean Delete(int id);
}
