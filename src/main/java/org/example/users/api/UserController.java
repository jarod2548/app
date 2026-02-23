package org.example.users.api;

import org.example.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.service.UserService;

import java.util.List;
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/api/users")
    public List<UserDto> getUsers() {
        return userService.GetUsers();
    }
}