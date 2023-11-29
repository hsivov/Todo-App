package com.example.todoapp.service;

import com.example.todoapp.model.dto.TodoItemDTO;
import com.example.todoapp.model.dto.TodoItemViewModel;
import com.example.todoapp.model.entity.TodoItem;

import java.util.List;
import java.util.Optional;

public interface TodoItemService {

    TodoItemViewModel getHomeViewData(String username);

    TodoItem getById(Long id);

    void add(TodoItemDTO todoItemDTO, String username);

    void update(Long id, TodoItemDTO todoItemDTO);

    void delete(Long id);
}
