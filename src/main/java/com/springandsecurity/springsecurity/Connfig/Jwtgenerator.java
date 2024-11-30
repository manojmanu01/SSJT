//package com.springandsecurity.springsecurity.Connfig;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.crypto.SecretKey;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//@Component
//public class Jwtgenerator extends OncePerRequestFilter {
//    /**
//     * @param request
//     * @param response
//     * @param filterChain
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(null!=authentication){
//
//            //secret key generation
//            String secret = "fdsaf54646545dfsfasdddsfsdfsfsad";
//            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//            //Token generation
//            String jwt = Jwts.builder().issuer("it me").subject("Jwt token")
//                    .claim("username",authentication.getName())
//                    .claim("authority",authentication.getAuthorities())
//                    .issuedAt(new Date())
//                    .expiration(new Date(new Date().getTime()+30000000))
//                    .signWith(secretKey)
//                    .compact();
//
//            //token appending with header
//            response.setHeader("authorization",jwt);
//        }
//        filterChain.doFilter(request,response);
//
//    }
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return !request.getServletPath().equals("/emp");
//    }
//
////ChatGpt
//
////    @Override
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
////            throws ServletException, IOException {
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////
////        if (authentication != null) {
////            try {
////                // Secret key should come from a secure source (environment, config, etc.)
////                String secretKeyString = Jwts.SIG.HS256.key().toString();  // Retrieve secret from env variable
////                if (secretKeyString == null || secretKeyString.isEmpty()) {
////                    throw new RuntimeException("JWT Secret key is missing");
////                }
////                SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));
////
////                // JWT Token generation
////                String jwt = Jwts.builder()
////                        .issuer("your-app") // Customize issuer
////                        .subject("JWT Authentication Token")
////                        .claim("username", authentication.getName()) // Store username in the JWT
////                        .claim("authorities", authentication.getAuthorities()) // Store authorities/roles
////                        .issuedAt(new Date())
////                        .expiration(new Date(System.currentTimeMillis() + 300000)) // 5-minute expiration
////                        .signWith(secretKey)
////                        .compact();
////
////                // Add the JWT to the Authorization header
////                response.setHeader("Authorization", "Bearer " + jwt);  // Prefix with "Bearer"
////            } catch (Exception e) {
////                // Log the error and handle appropriately
////                logger.error("Error generating JWT", e);
////                throw new ServletException("Error generating JWT token", e);
////            }
////        }
////
////        filterChain.doFilter(request, response);
////    }
////
////    @Override
////    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
////        return !request.getServletPath().equals("/emp");  // Apply filter only to specific paths
////    }
//
//
//
//}
