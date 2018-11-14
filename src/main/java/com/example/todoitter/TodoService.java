package com.example.todoitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Iterable<Todo> findAll() {
        return todoRepository.findAll();
    }

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
