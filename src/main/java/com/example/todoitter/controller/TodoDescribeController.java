package com.example.todoitter.controller;

import com.example.todoitter.entity.Member;
import com.example.todoitter.entity.Todo;
import com.example.todoitter.service.CategoryService;
import com.example.todoitter.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories/{category_id}/todos/describe/{id}")
public class TodoDescribeController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping
    public String getTodo(
            @ModelAttribute Todo todo,
            @AuthenticationPrincipal Member member,
            @PathVariable int category_id,
            @PathVariable int id,
            Model model) {
        model.addAttribute("todo", todoService.findOneById(id));
        model.addAttribute("category", categoryService.findOneById(category_id));
        model.addAttribute("member", member);
        return "todo/describe";
    }

}
