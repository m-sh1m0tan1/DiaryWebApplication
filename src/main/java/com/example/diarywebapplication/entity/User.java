package com.example.diarywebapplication.entity;

import lombok.Data;

@Data
public class User {
    private long id;
    private String mail;
    private String name;
    private String hashedPw;
}
