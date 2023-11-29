package com.example.todoapp.service;

import com.example.todoapp.model.dto.UserLoginBindingModel;
import com.example.todoapp.model.dto.UserRegisterBindingModel;

public interface UserService {

    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();
}
