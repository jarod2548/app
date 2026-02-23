package org.example.service;

import org.example.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void GetUsersTest()
    {
        List<UserDto> users = userService.GetUsers();
    }

}
