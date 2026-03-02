package org.example.users.api;


import org.example.users.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


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