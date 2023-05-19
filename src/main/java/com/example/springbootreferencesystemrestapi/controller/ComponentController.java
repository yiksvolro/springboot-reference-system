package com.example.springbootreferencesystemrestapi.controller;

import com.example.springbootreferencesystemrestapi.api.models.ComponentApiModel;
import com.example.springbootreferencesystemrestapi.service.interfaces.IComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/components")
public class ComponentController {
    @Autowired
    IComponentService componentService;
    @PostMapping("/create")
    public ResponseEntity<ComponentApiModel> CreateComputer(@RequestBody ComponentApiModel component){
        var result = componentService.Create(component);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ComponentApiModel> GetById(@PathVariable int id){
        var result = componentService.GetById(id);
        if(result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ArrayList<ComponentApiModel>> GetAll(){
        var result = componentService.GetAll();
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getComponentsByComputer/{id}")
    public ResponseEntity<ArrayList<ComponentApiModel>> GetComponentsByComputerId(@PathVariable int id){
        var result = componentService.GetComponentsByComputerId(id);
        if(result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update")
    public ResponseEntity<ComponentApiModel> Update(@RequestBody ComponentApiModel model){
        var result = componentService.Update(model);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> Delete(@PathVariable int id){
        var result = componentService.Delete(id);
        if(result){
            return  new ResponseEntity<>("Компонент видалено успішно!", HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<>("Помилка при видаленні компонента", HttpStatus.BAD_REQUEST);
        }

    }

}
