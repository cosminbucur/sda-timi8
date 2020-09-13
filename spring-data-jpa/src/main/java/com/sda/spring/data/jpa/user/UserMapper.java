package com.sda.spring.data.jpa.user;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public List<User> toEntity(List<UserDto> dtos) {
        return dtos.stream()
            .map(userDto -> toEntity(userDto))
            .collect(Collectors.toList());
    }

    public List<UserDto> toDto(List<User> users) {
        return users.stream()
            .map(user -> toDto(user))
            .collect(Collectors.toList());
    }

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
