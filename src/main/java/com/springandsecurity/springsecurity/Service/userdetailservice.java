package com.springandsecurity.springsecurity.Service;

import com.springandsecurity.springsecurity.Model.Employee;
import com.springandsecurity.springsecurity.Repo.RepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class userdetailservice implements UserDetailsService {
    @Autowired
    RepositoryClass repo;
    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = repo.findByEmail(username);
        if(employee.isPresent()){
            Employee e = employee.get();
            String pwd = e.getPassword();
            List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+e.getRole()));
            return new User(username,pwd,grantedAuthorities);
        }else {
            return (UserDetails) new UsernameNotFoundException("Not Found");
        }
    }
}
