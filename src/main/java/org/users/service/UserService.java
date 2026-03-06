package org.users.service;

import org.users.api.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    public List<UserDto> GetUsers() {
        return List.of(
                new UserDto(1L, "Alice"),
                new UserDto(2L, "Bob")
        );
    }
}
