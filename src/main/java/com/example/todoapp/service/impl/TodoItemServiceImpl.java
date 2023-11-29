package com.example.todoapp.service.impl;

import com.example.todoapp.model.dto.TodoItemDTO;
import com.example.todoapp.model.dto.TodoItemViewModel;
import com.example.todoapp.model.entity.TodoItem;
import com.example.todoapp.model.entity.User;
import com.example.todoapp.repository.TodoItemRepository;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.TodoItemService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    final private TodoItemRepository todoItemRepository;
    private final UserRepository userRepository;

    public TodoItemServiceImpl(TodoItemRepository todoItemRepository,
                               UserRepository userRepository) {
        this.todoItemRepository = todoItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TodoItemViewModel getHomeViewData(String username) {
        User user = userRepository.findByUsername(username);

        List<TodoItemDTO> assignedTasks = todoItemRepository.findByUser(user).stream()
                .map(TodoItemDTO::createFromTodoItem)
                .toList();

        return new TodoItemViewModel(assignedTasks);
    }

    @Override
    public TodoItem getById(Long id) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findById(id);

        return optionalTodoItem.orElse(null);
    }

    @Override
    public void add(TodoItemDTO todoItemDTO, String username) {
        TodoItem todo = new TodoItem();
        User user = userRepository.findByUsername(username);

        todo.setDescription(todoItemDTO.getDescription());
        todo.setCreateDate(LocalDateTime.now());
        todo.setModifyDate(LocalDateTime.now());
        todo.setUser(user);

        todoItemRepository.save(todo);
    }

    @Override
    public void update(Long id, TodoItemDTO todoItemDTO) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findById(id);

        if (optionalTodoItem.isPresent()) {
            TodoItem todoItem = optionalTodoItem.get();

            todoItem.setDescription(todoItemDTO.getDescription());
            todoItem.setModifyDate(LocalDateTime.now());
            todoItem.setCompleted(todoItemDTO.isCompleted());

            todoItemRepository.save(todoItem);
        }
    }

    @Override
    public void delete(Long id) {
        todoItemRepository.deleteById(id);
    }
}
