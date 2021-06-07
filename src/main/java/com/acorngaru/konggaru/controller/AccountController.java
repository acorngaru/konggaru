package com.acorngaru.konggaru.controller;


import com.acorngaru.konggaru.model.Member;
import com.acorngaru.konggaru.security.MemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AccountController {

    @Autowired
    MemberDetailsService memberDetailsService;

    @GetMapping
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }

    @PostMapping("/register")
    public String register(@RequestBody Member user) {
        int result = memberDetailsService.idChk(user.getNickName());
        System.out.println(result);
        if (result ==0){

            memberDetailsService.signUp(user);
            System.out.println(">>>>성공"+user);
        }

        return "account/login";
    }
}