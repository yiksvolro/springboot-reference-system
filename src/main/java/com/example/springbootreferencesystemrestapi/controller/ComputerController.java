package com.example.springbootreferencesystemrestapi.controller;

import com.example.springbootreferencesystemrestapi.api.models.ComponentApiModel;
import com.example.springbootreferencesystemrestapi.api.models.ComputerApiModel;
import com.example.springbootreferencesystemrestapi.service.interfaces.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/computers")
public class ComputerController {
    @Autowired
    IComputerService computerService;
    @PostMapping("/create")
    public ResponseEntity<ComputerApiModel> CreateComputer(@RequestBody ComputerApiModel computer){
        var result = computerService.Create(computer);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ComputerApiModel> GetById(@PathVariable int id){
        var result = computerService.GetById(id);
        if(result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ArrayList<ComputerApiModel>> GetAll(){
        var result = computerService.GetAll();
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<ComputerApiModel> Update(@RequestBody ComputerApiModel model){
        var result = computerService.Update(model);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> Delete(@PathVariable int id){
        var result = computerService.Delete(id);
        if(result){
            return  new ResponseEntity<>("Комп'ютер видалено успішно!", HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<>("Помилка при видаленні комп'ютера", HttpStatus.BAD_REQUEST);
        }

    }

}
