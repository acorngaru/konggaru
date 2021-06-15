package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.security.MemberDetails;
import com.acorngaru.konggaru.util.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String showHome(@CurrentUser MemberDetails member) {
        System.out.println(member.member);
        return "index";
    }
}
