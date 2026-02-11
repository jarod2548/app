package org.example.service;

import org.example.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    public List<UserDto> getUsers() {
        return List.of(
                new UserDto(1L, "Alice"),
                new UserDto(2L, "Bob")
        );
    }
}