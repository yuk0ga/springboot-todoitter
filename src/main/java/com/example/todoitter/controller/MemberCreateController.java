package com.example.todoitter.controller;

import com.example.todoitter.entity.Member;
import com.example.todoitter.form.MemberSignupForm;
import com.example.todoitter.service.DefaultMemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class MemberCreateController {

    @Autowired
    DefaultMemberDetailsService memberDetailsService;

    @ModelAttribute
    public MemberSignupForm setupForm() {
        return new MemberSignupForm();
    }

    @GetMapping
    String memberSignupForm() {
        return "member/signup";
    }

    @PostMapping
    String create(@Validated MemberSignupForm form,
                  BindingResult result) {
        if (result.hasErrors()) {
            return "member/signup";
        }
        Member member = new Member();
        member.setUsername(form.getUsername());
        member.setEmail(form.getEmail());
        memberDetailsService.createMember(member, form.getPassword());
        return "redirect:/login";
    }
}
