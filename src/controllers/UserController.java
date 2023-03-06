package controllers;

import dtos.requests.LoginUserRequest;
import dtos.requests.RegisterUserRequest;
import services.UserService;
import services.UserServiceImpl;

public class UserController {
    private final UserService userService = new UserServiceImpl();

    public Object registerUser(RegisterUserRequest request) {
        try {
            return userService.register(request);
        } catch (IllegalArgumentException error) {
            return error.getMessage();
        }
    }

    public Object loginUser(LoginUserRequest request) {
        try {
            return userService.login(request);
        } catch (IllegalArgumentException error) {
            return error.getMessage();
        }
    }
}