package com.springandsecurity.springsecurity.Service;

import com.springandsecurity.springsecurity.Model.Employee;
import com.springandsecurity.springsecurity.Repo.RepositoryClass;
import jakarta.persistence.Embeddable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestingService {

    @Autowired
    RepositoryClass repo;

    @Autowired
    AuthenticationManager authenticationManager;

    public ResponseEntity getAllDetails() {
        List<Employee> emp = repo.findAll();
        if(emp.isEmpty()){
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
        else {
            return ResponseEntity.ok(emp);
        }
    }

    public ResponseEntity getByEmail(String email) {

        if(!repo.existsByEmail(email)){
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
        try {
            Optional<Employee> returnedEmp = repo.findByEmail(email);
            if(returnedEmp.isPresent()){
             return ResponseEntity.ok(returnedEmp);
            }
            else {
                return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }

    public ResponseEntity update(Employee e) {
        Optional<Employee> emp  = repo.findByEmail(e.getEmail());
        if(!emp.isPresent()){
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
        try{
            Employee employee = emp.get();
            employee.setEmail(e.getEmail());
            employee.setLastName(e.getLastName());
            employee.setFirstName(e.getFirstName());
            repo.save(employee);
            return ResponseEntity.ok(employee);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }

    }

    public ResponseEntity add(Employee employee) {
        if(repo.existsByEmail(employee.getEmail())){
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
        try {
            repo.save(employee);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(employee);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }

    }

    public ResponseEntity delete(String id) {
        Optional<Employee> emp  = repo.findById(id);
        if (emp.isPresent()){
            repo.deleteById(id);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).varyBy("Deleted").build();
        }
        else
        {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }

    public ResponseEntity login(Employee employee) {

        String email = employee.getEmail();
        String pwd = employee.getPassword();

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email,pwd));

        if (authentication.isAuthenticated()){
            return ResponseEntity.ok("Good");
        }
        else {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
        }

    }
}
