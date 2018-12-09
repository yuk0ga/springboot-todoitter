package com.example.todoitter.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @OneToMany(mappedBy = "category")
    private List<Todo> todos;

    private String name;
}
