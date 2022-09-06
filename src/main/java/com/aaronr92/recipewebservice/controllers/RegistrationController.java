package com.aaronr92.recipewebservice.controllers;

import com.aaronr92.recipewebservice.entities.User;
import com.aaronr92.recipewebservice.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class RegistrationController {

    private final UserDetailsServiceImpl userService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody User user) {
        userService.register(user);
    }
}
