package com.example.todoitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping
    public String home(@ModelAttribute Todo todo, Model model) {
        model.addAttribute("todos", todoService.findAll());
        return "home";
    }

    @GetMapping("/json")
    public @ResponseBody Iterable<Todo> json() {
        return todoService.findAll();
    }

    @PostMapping
    String postTodo(@ModelAttribute @Validated Todo todo,
                    BindingResult result,
                    Model model) {
        todoService.save(todo);

        return "redirect:/";
    }

    @GetMapping("{id}")
    String deleteTodo(@PathVariable int id) {
        Todo todo = todoService.findOneById(id);
        todoService.delete(todo);
        return "redirect:/";
    }
}
