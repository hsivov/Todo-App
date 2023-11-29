package com.example.todoapp.model.dto;

import com.example.todoapp.model.entity.TodoItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TodoItemDTO {
    private Long id;
    private String description;
    private boolean isCompleted;
    private String createDate;
    private String modifyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public static TodoItemDTO createFromTodoItem(TodoItem todoItem) {
        TodoItemDTO todoItemDTO = new TodoItemDTO();

        todoItemDTO.setId(todoItem.getId());
        todoItemDTO.setDescription(todoItem.getDescription());
        todoItemDTO.setCreateDate(todoItem.createDateFormat());
        todoItemDTO.setModifyDate(todoItem.modifyDateFormat());
        todoItemDTO.setCompleted(todoItem.isCompleted());

        return todoItemDTO;
    }
}
