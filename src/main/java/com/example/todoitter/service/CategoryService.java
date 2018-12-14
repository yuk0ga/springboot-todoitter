package com.example.todoitter.service;

import com.example.todoitter.entity.Category;
import com.example.todoitter.entity.Member;
import com.example.todoitter.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Iterable<Category> findAllByMemberId(int memberId) {
        return categoryRepository.findAllByMemberId(memberId);
    }

    public Category findOneById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
