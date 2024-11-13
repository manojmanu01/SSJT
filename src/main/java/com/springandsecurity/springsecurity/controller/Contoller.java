package com.springandsecurity.springsecurity.controller;

import com.springandsecurity.springsecurity.Model.Employee;
import com.springandsecurity.springsecurity.Service.ServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Contoller {
    @Autowired
    private ServiceClass sclass;
    private Employee ee;

    @PostMapping("/empe")
    public ResponseEntity<Employee> add(@RequestBody Employee ee){
        return sclass.add(ee);
    }

    @GetMapping("/employee")
    public Object getAllDetails(){
        return sclass.getAllDetails();
    }


    @GetMapping("/emp")
    public String method(){
        return "Jwt Token received in header";
    }

    @PutMapping("/empe")
    public ResponseEntity update(@RequestBody Employee emp){
        return sclass.update(emp);
    }


    @DeleteMapping("/empe")
    public ResponseEntity delete(@RequestParam String email){
        return sclass.delete(email);
    }



}
