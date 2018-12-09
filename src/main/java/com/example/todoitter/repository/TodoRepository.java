package com.example.todoitter.repository;

import com.example.todoitter.entity.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {
    List<Todo> findAllByCategoryId(int category_id);
}
