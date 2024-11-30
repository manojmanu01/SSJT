//package com.springandsecurity.springsecurity.Service;
//
//import com.springandsecurity.springsecurity.Model.Employee;
//import com.springandsecurity.springsecurity.Repo.RepositoryClass;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class ServiceClass {
//    @Autowired
//    private RepositoryClass repo;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    private Employee ee;
//    public Object getAllDetails() {
//        return repo.findAll();
//    }
//
//    public ResponseEntity add(Employee ee) {
//        try{
//            String hashpwd = passwordEncoder.encode(ee.getPassword());
//            ee.setPassword(hashpwd);
//            repo.save(ee);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//        }catch (Exception e){
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    public ResponseEntity update(Employee empp) {
//        try {
//            Optional<Employee> e = repo.findByEmail(empp.getEmail());
//            if (e.isPresent()){
//                Employee emp = e.get();
//                emp.setFirstName(empp.getFirstName());
//                emp.setLastName(empp.getLastName());
//                repo.save(emp);
//                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//            }
//            else {
//                return ResponseEntity.notFound().build();
//            }
//
//        }catch (Exception e){
//            return ResponseEntity.notFound().build();
//        }
//
//    }
//
//    public ResponseEntity delete(String email) {
//        Optional<Employee> e = repo.findByEmail(email);
//        if(e.isPresent()){
//            Employee ee = e.get();
//            repo.delete(ee);
//            return ResponseEntity.status(HttpStatus.OK).build();
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
//}
