//package com.springandsecurity.springsecurity.Connfig;
//
//import jakarta.security.auth.message.config.AuthConfigProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TestingAuthProvider implements AuthenticationProvider {
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    /**
//     * @param authentication
//     * @return
//     * @throws AuthenticationException
//     */
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String pwd = authentication.getCredentials().toString();
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        if(passwordEncoder.matches(pwd,userDetails.getPassword())){
//            return new UsernamePasswordAuthenticationToken(username,pwd,userDetails.getAuthorities());
//        }else {
//            throw new BadCredentialsException("Wrong Password");
//        }
//
//    }
//
//    /**
//     * @param authentication
//     * @return
//     */
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//}
