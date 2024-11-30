package com.springandsecurity.springsecurity.controller;

import com.springandsecurity.springsecurity.Model.Employee;
import com.springandsecurity.springsecurity.Service.TestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestingController {

    @Autowired
    TestingService testingService;


    @GetMapping("/testing")
    public ResponseEntity getAllDetails(){
        return testingService.getAllDetails();
    }

    @GetMapping("/testing/email")
    public ResponseEntity getByEmail(@RequestParam String email){
        return testingService.getByEmail(email);
    }


    @PutMapping("testing")
    public ResponseEntity update(@RequestBody Employee e) {
        return testingService.update(e);
    }


    @PostMapping("testing")
    public ResponseEntity add(@RequestBody Employee e){
        return testingService.add(e);
    }


    @DeleteMapping("testing/{id}")
    public ResponseEntity delete(@PathVariable String id){
        return testingService.delete(id);
    }

    @GetMapping("login")
    public ResponseEntity login(@RequestBody Employee employee){
        return testingService.login(employee);
    }

}
