package com.example.todoitter;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Todo {

    @Id
    @GeneratedValue
    private int id;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dueDate;

    private boolean done;
}
