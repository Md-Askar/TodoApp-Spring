package Javapractice.helloworld;

import Javapractice.helloworld.utils.jwtutils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public  class JwtFilter extends OncePerRequestFilter {
    private final jwtutils Jwtutils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        System.out.println("AUTH HEADER = " + authHeader);
        if(authHeader !=null && authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            System.out.println("TOKEN = " + token);
            boolean valid = Jwtutils.validateToken(token);

            System.out.println("TOKEN VALID = " + valid);
            if(Jwtutils.validateToken(token)){
                String email= Jwtutils.extractEmail(token);
                System.out.println("EMAIL = " + email);
                UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println(
                        "AUTHENTICATION = " +
                                SecurityContextHolder
                                        .getContext()
                                        .getAuthentication()
                );

            }

        }
        filterChain.doFilter(request,response);
    }

}
