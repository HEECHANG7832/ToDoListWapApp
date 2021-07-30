package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.model.AccountDto;
import org.example.repository.AccountRepository;
import org.example.services.UserRegisterService;
import org.example.tools.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/")
public class UserRegisterController {

    @Autowired
    UserRegisterService userRegisterService;

    @PostMapping("/register")
    public String registerUser(@RequestBody AccountDto accountdto){
        log.info("Sign-Up");
        return userRegisterService.registerUser(accountdto);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AccountDto accountdto, HttpServletRequest request){
        log.info("Sign-In");

        HttpSession session = request.getSession();

        if(userRegisterService.loginUser(accountdto)){
            session.setAttribute(Sessions.SESSION_ID, accountdto);
            return "success";
        }else{
            session.setAttribute(Sessions.SESSION_ID, null);
            return "failed";
        }
    }
}
