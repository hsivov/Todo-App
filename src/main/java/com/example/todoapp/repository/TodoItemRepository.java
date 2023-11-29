package com.example.todoapp.repository;

import com.example.todoapp.model.dto.TodoItemDTO;
import com.example.todoapp.model.entity.TodoItem;
import com.example.todoapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByUser(User user);
}
