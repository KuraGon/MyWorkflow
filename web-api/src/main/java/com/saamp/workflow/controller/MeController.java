package com.saamp.workflow.controller;

import java.security.Principal;
import java.util.Map;

import com.saamp.workflow.entity.PhoneUserEntity;
import com.saamp.workflow.entity.UserEntity;
import com.saamp.workflow.mapper.UserMapper;
import com.saamp.workflow.repository.PhoneUserRepository;
import com.saamp.workflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class MeController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public UserEntity me(Authentication auth) {
        String username = auth.getName();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow();
        return user;
    }
}
