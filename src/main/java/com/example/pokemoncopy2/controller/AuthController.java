package com.example.pokemoncopy2.controller;

import com.example.pokemoncopy2.dto.RegisterDto;
import com.example.pokemoncopy2.entity.Role;
import com.example.pokemoncopy2.entity.UserEntity;
import com.example.pokemoncopy2.repository.RoleRepository;
import com.example.pokemoncopy2.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        if (userRepository.existsByUsername(registerDto.getUsername())){

            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();

        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role role = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        return new ResponseEntity<>("user register success",HttpStatus.OK);
    }

}
