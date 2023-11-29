package com.example.todoapp.controller;

import com.example.todoapp.model.dto.TodoItemViewModel;
import com.example.todoapp.service.impl.LoggedUser;
import com.example.todoapp.service.impl.TodoItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final TodoItemServiceImpl todoItemServiceImpl;
    private final LoggedUser loggedUser;

    public HomeController(TodoItemServiceImpl todoItemServiceImpl, LoggedUser loggedUser) {
        this.todoItemServiceImpl = todoItemServiceImpl;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public ModelAndView index() {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        TodoItemViewModel viewModel = todoItemServiceImpl.getHomeViewData(loggedUser.getUsername());

        return new ModelAndView("home", "todoItems", viewModel);
    }
}
