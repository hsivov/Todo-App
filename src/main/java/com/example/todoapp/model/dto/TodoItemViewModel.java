package com.example.todoapp.model.dto;

import java.util.ArrayList;
import java.util.List;

public class TodoItemViewModel {

    private List<TodoItemDTO> items;

    public TodoItemViewModel() {
        this(new ArrayList<>());
    }

    public TodoItemViewModel(List<TodoItemDTO> items) {
        this.items = items;
    }

    public List<TodoItemDTO> getItems() {
        return items;
    }

    public void setItems(List<TodoItemDTO> items) {
        this.items = items;
    }
}
