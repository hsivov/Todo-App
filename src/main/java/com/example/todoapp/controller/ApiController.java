package com.example.todoapp.controller;

import com.example.todoapp.model.entity.TodoItem;
import com.example.todoapp.service.impl.TodoItemServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 3600, allowCredentials = "false" )
@RestController
public class ApiController {

    private final TodoItemServiceImpl todoItemServiceImpl;

    public ApiController(TodoItemServiceImpl todoItemServiceImpl) {
        this.todoItemServiceImpl = todoItemServiceImpl;
    }

//    @GetMapping("/todos")
//    public List<TodoItem> findTodoItems() {
//
//    }

    @PostMapping("/todos")
    public TodoItem addTodo(@RequestBody TodoItem todoItem) {
        //todoItemServiceImpl.save(todoItem);
        return todoItem;
    }

    @PatchMapping("/todos/{id}")
    public String updateTodo(@PathVariable long id, @RequestBody TodoItem todoItem) {
        TodoItem updatedTodo = todoItemServiceImpl.getById(id);

        updatedTodo.setDescription(todoItem.getDescription());
        updatedTodo.setCompleted(todoItem.isCompleted());
        //todoItemServiceImpl.save(updatedTodo);
        return String.format("Todo with id: %d was updated.", id);
    }

    @DeleteMapping("/todos/{id}")
    public String deleteTodo(@PathVariable long id) {

        todoItemServiceImpl.delete(id);
        return String.format("Todo item with id: %d was deleted!", id);
    }
}
