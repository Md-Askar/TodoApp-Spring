package Javapractice.helloworld.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@RestController
@Component
public class jwtutils {
    private final String secret = "Askar hii tokyo Askar hii tokyo Askar hii tokyo";
    private  final long expiry=1000*60*60;
    private  final Key secretkey= Keys.hmacShaKeyFor( secret.getBytes(StandardCharsets.UTF_8) );


    public  String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +expiry))
                .signWith( secretkey, SignatureAlgorithm.HS256 )
                .compact();

    }
    public  String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretkey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }
    public boolean validateToken(String token){
        try{
           extractEmail(token);

            return true;

        }catch(JwtException exception){
            return false;
        }


    }

}
