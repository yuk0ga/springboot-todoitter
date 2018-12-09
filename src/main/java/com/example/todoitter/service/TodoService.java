package com.example.todoitter.service;

import com.example.todoitter.repository.TodoRepository;
import com.example.todoitter.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Iterable<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Iterable<Todo> findAllByCategoryId(int category_id) { return todoRepository.findAllByCategoryId(category_id); }

    public Todo findOneById(int id) {
        return todoRepository.findById(id).orElse(null);
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo toggleDone(Todo todo) {
        todo.setDone(!todo.isDone());
        return todo;
    }

    public void delete(Todo todo) {
        todoRepository.delete(todo);
    }
}
