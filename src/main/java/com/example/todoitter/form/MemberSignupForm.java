package com.example.todoitter.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberSignupForm implements Serializable {
    private String username;
    private String email;
    private String password;
}
