package com.example.todoapp.controller;

import com.example.todoapp.model.TodoItem;
import com.example.todoapp.service.TodoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoFormController {

    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem) {
        return "new-todo-form";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem) {
        TodoItem item = new TodoItem();
        item.setDescription(todoItem.getDescription());
        item.setCompleted(todoItem.isCompleted());

        todoItemService.save(item);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id) {
        TodoItem todoItem = todoItemService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id:" + id + "not found"));

        todoItemService.delete(todoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        TodoItem todoItem = todoItemService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id:" + id + "not found"));

        model.addAttribute("todo", todoItem);

        return "edit-todo-form";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid TodoItem todoItem) {
        TodoItem item = todoItemService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id:" + id + "not found"));

        item.setDescription(todoItem.getDescription());
        item.setCompleted(todoItem.isCompleted());

        todoItemService.save(item);

        return "redirect:/";
    }
}
