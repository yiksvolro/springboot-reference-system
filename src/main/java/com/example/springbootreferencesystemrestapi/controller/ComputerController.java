package com.example.springbootreferencesystemrestapi.controller;

import com.example.springbootreferencesystemrestapi.api.models.ComputerApiModel;
import com.example.springbootreferencesystemrestapi.service.interfaces.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getsmth")
    public ResponseEntity<String> Get(){
        return new ResponseEntity<String>("Jesus!", HttpStatus.OK);
    }
    @GetMapping("/getById")
    public ResponseEntity<ComputerApiModel> GetById(@RequestParam int id){
        var result = computerService.GetById(id);
        if(result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
