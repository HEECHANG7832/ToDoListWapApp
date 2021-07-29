package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.model.AccountDto;
import org.example.repository.AccountRepository;
import org.example.services.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class UserRegisterController {

    @Autowired
    UserRegisterService userRegisterService;

    @PostMapping("/register")
    public String registerUser(@RequestBody AccountDto accountdto){
        log.info("Sign-Up");
        System.out.println(accountdto.getUserName());
        return userRegisterService.registerUser(accountdto);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AccountDto accountdto){
        log.info("Sign-In");
        System.out.println(accountdto.getUserName());
        return userRegisterService.loginUser(accountdto);
    }
}
