package com.example.todoitter.repository;

import com.example.todoitter.entity.Category;
import com.example.todoitter.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    List<Category> findAllByMemberId(int memberId);
}
