package Javapractice.helloworld.controller;


import Javapractice.helloworld.model.User;
import Javapractice.helloworld.repo.Userrepo;
import Javapractice.helloworld.service.UserService;
import Javapractice.helloworld.utils.jwtutils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Auth")
public class AuthController {
    private final UserService userService;
    private final Userrepo userrepo;
    private final PasswordEncoder passwordEncoder;
    private final jwtutils Jwtutils;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String,String> body){
        String email=body.get("email");
        String password=body.get("password");
        password=passwordEncoder.encode(password);
        if(userrepo.findByEmail(email).isPresent()){
            return new ResponseEntity<>("email already exists", HttpStatus.CONFLICT);
        }
        userService.create(User.builder().email(email).password(password).build());
        return new ResponseEntity<>("success", HttpStatus.OK);

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        String email=body.get("email");
        String password=body.get("password");
        var userOptional=userrepo.findByEmail(email);
        if(userOptional.isEmpty()){
            return new ResponseEntity<>("email not exists", HttpStatus.CONFLICT);
        }
        User user=userOptional.get();
        if(!passwordEncoder.matches(password,user.getPassword())){
            return new ResponseEntity<>("incorrect password", HttpStatus.UNAUTHORIZED);
        }
        String token=Jwtutils.generateToken(user.getEmail());
        return new ResponseEntity<>(Map.of("token",token), HttpStatus.OK);



    }
}
