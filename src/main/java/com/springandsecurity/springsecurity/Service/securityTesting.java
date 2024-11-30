//package com.springandsecurity.springsecurity.Service;
//
//import com.springandsecurity.springsecurity.Model.Employee;
//import com.springandsecurity.springsecurity.Repo.RepositoryClass;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.lang.reflect.ReflectPermission;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class securityTesting implements UserDetailsService {
//    private final RepositoryClass repo;
//
//    public securityTesting(RepositoryClass repo) {
//        this.repo = repo;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        Optional<Employee> es = Optional.ofNullable(repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Not found")));
//            Employee e = es.get();
//            List<GrantedAuthority> gs = List.of(new SimpleGrantedAuthority(e.getRole()));
//            return new User(e.getEmail(),e.getPassword(),gs);
//
//    }
//}
