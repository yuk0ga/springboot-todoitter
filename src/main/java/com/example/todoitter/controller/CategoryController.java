package com.example.todoitter.controller;

import com.example.todoitter.entity.Todo;
import com.example.todoitter.service.CategoryService;
import com.example.todoitter.entity.Category;
import com.example.todoitter.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    TodoService todoService;

    @GetMapping
    public String listCategories(
            @ModelAttribute Category category,
            Model model) {
        model.addAttribute("categories", categoryService.findAll()); // pass attribute to view
        return "category/list";
    }

    @PostMapping
    String postCategory(@ModelAttribute @Validated Category category,
                    BindingResult result,
                    Model model) {
        categoryService.save(category);

        return "redirect:/categories";
    }

    @GetMapping("{id}/delete")
    String deleteCategory(@PathVariable int id) {
        Iterable<Todo> todos = todoService.findAllByCategoryId(id);
        for (Todo todo: todos) {
            todoService.delete(todo);
        }
        Category category = categoryService.findOneById(id);
        categoryService.delete(category);
        return "redirect:/categories";
    }
}
