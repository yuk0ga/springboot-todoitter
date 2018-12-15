package com.example.todoitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BaseController {
    @GetMapping
    public String redirect() {
        return "redirect:/categories";
    }

    @GetMapping("login")
    public String login() { return "member/login"; }
}
