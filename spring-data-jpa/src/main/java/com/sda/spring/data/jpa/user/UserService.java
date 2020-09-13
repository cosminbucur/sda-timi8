package com.sda.spring.data.jpa.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserMapper userMapper;
    private UserRepository userRepository;

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    // user comes as a json object with all the details from the front end
    public UserDto save(UserDto userDto) {
        // TODO: validate user info

        // convert dto to entity
        // user dto -> user
        User userToBeSaved = userMapper.toEntity(userDto);

        // save to db
        User savedUser = userRepository.save(userToBeSaved);

        // convert entity to dto
        // user -> user dto
        return userMapper.toDto(savedUser);
    }
}
