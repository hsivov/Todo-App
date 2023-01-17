package com.example.todoapp.service;

import com.example.todoapp.model.TodoItem;
import com.example.todoapp.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    public Collection<TodoItem> getAll() {
        return todoItemRepository.findAll();
    }

    public Optional<TodoItem> getById(Long id) {
        return todoItemRepository.findById(id);
    }

    public TodoItem save(TodoItem todoItem) {
        if (todoItem.getId() == null) {
            todoItem.setCreateDate(LocalDateTime.now());
        }
        todoItem.setModifyDate(LocalDateTime.now());
        return todoItemRepository.save(todoItem);
    }

    public void delete(TodoItem todoItem) {
        todoItemRepository.delete(todoItem);
    }
}
