package com.springandsecurity.springsecurity.Connfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.List;

@Component
public class JwtValidator extends OncePerRequestFilter {
    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = request.getHeader("authorization");

        if(null != jwt){
            try {
                String secret = "fdsaf54646545dfsfasdddsfsdfsfsad";
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));


                Claims claims = Jwts.parser().
                        verifyWith(secretKey).
                        build().
                        parseSignedClaims(jwt).
                        getPayload();

                String username = String.valueOf(claims.get("username"));
                String authorities = String.valueOf(claims.get("authorization"));

                Authentication authentication = new UsernamePasswordAuthenticationToken(authorities, username, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                throw new UsernameNotFoundException("Nalla");
            }
        }



        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/emp");
    }

    //gpt



//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String jwt = request.getHeader("Authorization");
//
//        if (jwt != null && jwt.startsWith("Bearer ")) {  // Check if the JWT is prefixed with 'Bearer'
//            try {
//                // Remove the 'Bearer ' prefix
//                jwt = jwt.substring(7);
//
//                // Secret key should come from a secure source (environment, config, etc.)
//                String secretKeyString = Jwts.SIG.HS256.key().toString();
//                if (secretKeyString == null || secretKeyString.isEmpty()) {
//                    throw new RuntimeException("JWT Secret key is missing");
//                }
//                SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));
//
//                // Parse and verify the JWT using the new API
//                Claims claims = Jwts.parser()
//                        .setSigningKey(secretKey)  // Set the key for verifying the JWT signature
//                        .build()
//                        .parseClaimsJws(jwt)  // Parse the JWT (verify signature, parse claims)
//                        .getBody();  // Get the claims from the parsed JWT
//
//                String username = claims.get("username", String.class);
//                List<GrantedAuthority> authorities = (List<GrantedAuthority>) claims.get("authorities");
//
//                // Create authentication object
//                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
//
//                // Set the authentication in the SecurityContext
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } catch (JwtException | IllegalArgumentException e) {
//                // Log the error and handle the case where the JWT is invalid
//                logger.error("Invalid JWT token", e);
//                throw new SecurityException("Invalid JWT token", e);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return request.getServletPath().equals("/emp");  // Apply filter only to specific paths
//    }
//
//

}
