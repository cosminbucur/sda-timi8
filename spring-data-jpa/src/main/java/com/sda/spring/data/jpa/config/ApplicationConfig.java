package com.sda.spring.data.jpa.config;

import com.sda.spring.data.jpa.user.UserDto;
import com.sda.spring.data.jpa.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Autowired
    public UserService userService;

    @Bean
    public CommandLineRunner crudData() {
        return args -> {
            UserDto userDto = new UserDto();
            userDto.setName("jon");
            userDto.setEmail("jonsnow@gmail.com");

            UserDto result = userService.save(userDto);
            logger.info("saved user: {}", result);
        };
    }

}
