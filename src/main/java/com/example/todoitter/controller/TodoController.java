package com.example.todoitter.controller;

import com.example.todoitter.entity.Member;
import com.example.todoitter.service.CategoryService;
import com.example.todoitter.service.TodoService;
import com.example.todoitter.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@Controller
@RequestMapping("/categories/{category_id}/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String listTodos(
            @ModelAttribute Todo todo,
            @AuthenticationPrincipal Member member,
            Model model,
            @PathVariable int category_id) {
        model.addAttribute("todos", todoService.findAllByCategoryId(category_id)); // pass attribute to view
        return "todo/list";
    }

    @GetMapping("/json")
    public @ResponseBody Iterable<Todo> json(@PathVariable int category_id) {
        return todoService.findAll();
    }

    @PostMapping
    String postTodo(
            @ModelAttribute @Validated Todo todo,
            @AuthenticationPrincipal Member member,
            BindingResult result,
            Model model,
            @PathVariable int category_id) {
        todo.setMember(member);
        todo.setCategory(categoryService.findOneById(category_id));
        todoService.save(todo);

        return "redirect:/categories/" + category_id + "/todos";
    }

    @GetMapping("/{id}")
    String deleteTodo(
            @PathVariable int id,
            @PathVariable int category_id) {
        Todo todo = todoService.findOneById(id);
        todoService.delete(todo);
        return "redirect:/categories/" + category_id + "/todos";
    }
}
