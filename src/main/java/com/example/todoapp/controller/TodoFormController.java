package com.example.todoapp.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class TodoFormController {

    @GetMapping("/create-todo")
    public String showCreateForm() {
        return "new-todo-form";
    }
}
