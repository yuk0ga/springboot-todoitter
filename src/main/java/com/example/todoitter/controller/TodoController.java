package com.example.todoitter.controller;

import com.example.todoitter.service.CategoryService;
import com.example.todoitter.service.TodoService;
import com.example.todoitter.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@Controller
@RequestMapping("/{category_id}")
public class TodoController {

    @Autowired
    TodoService todoService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String listTodos(
            @ModelAttribute Todo todo,
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
            BindingResult result,
            Model model,
            @PathVariable int category_id) {
        todo.setCategory(categoryService.findOneById(category_id));
        todoService.save(todo);

        return "redirect:/" + category_id;
    }

    @GetMapping("/{id}")
    String deleteTodo(
            @PathVariable int id,
            @PathVariable int category_id) {
        Todo todo = todoService.findOneById(id);
        todoService.delete(todo);
        return "redirect:/" + category_id;
    }
}
