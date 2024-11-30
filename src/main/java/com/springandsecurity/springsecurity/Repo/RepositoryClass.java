package com.springandsecurity.springsecurity.Repo;

import com.springandsecurity.springsecurity.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryClass extends JpaRepository<Employee,String> {

    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);
}
