package com.example.todoapp.controller;

import com.example.todoapp.model.dto.TodoItemDTO;
import com.example.todoapp.model.entity.TodoItem;
import com.example.todoapp.service.impl.LoggedUser;
import com.example.todoapp.service.impl.TodoItemServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoFormController {

    private final TodoItemServiceImpl todoItemServiceImpl;
    private final LoggedUser loggedUser;

    public TodoFormController(TodoItemServiceImpl todoItemServiceImpl, LoggedUser loggedUser) {
        this.todoItemServiceImpl = todoItemServiceImpl;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/todo/add")
    public ModelAndView showCreateForm(@ModelAttribute("TodoItemDTO") TodoItemDTO todoItemDTO) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView("new-todo-form");
        modelAndView.addObject(todoItemDTO);

        return modelAndView;
    }

    @PostMapping("/todo/add")
    public ModelAndView createTodoItem(@ModelAttribute("TodoItemDTO") @Valid TodoItemDTO todoItemDTO,
                                       BindingResult bindingResult) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("new-todo-form");
        }

        todoItemServiceImpl.add(todoItemDTO, loggedUser.getUsername());

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        todoItemServiceImpl.delete(id);
        return "redirect:/home";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        TodoItem todoItem = todoItemServiceImpl.getById(id);
        model.addAttribute("todo", todoItem);

        return "edit-todo-form";
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateTodoItem(@PathVariable("id") Long id, @Valid TodoItemDTO todoItemDTO) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        todoItemServiceImpl.update(id, todoItemDTO);

        return new ModelAndView("redirect:/home");
    }
}
