package com.springandsecurity.springsecurity.Connfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityDeclaration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(se -> se.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new Jwtgenerator(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JwtValidator(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((request)->  request
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults())
                .build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user = User.withUsername("admin").password("{noop}admin").build();
//        return new InMemoryUserDetailsManager(user);}
    @Bean
    public PasswordEncoder passwordEncoderFactories(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }
//    @Bean
//    public UserDetailsManager userDetailsManager(){
//        UserDetails usr = User.builder().build();
//        return new JdbcUserDetailsManager()
//    }
//    @Bean
//    public CompromisedPasswordChecker passwordChecker(){
//        return new HaveIBeenPwnedRestApiPasswordChecker();
//    }
}