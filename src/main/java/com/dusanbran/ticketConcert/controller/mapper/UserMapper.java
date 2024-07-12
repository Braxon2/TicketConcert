package com.dusanbran.ticketConcert.controller.mapper;

import com.dusanbran.ticketConcert.controller.dto.UserDTO;
import com.dusanbran.ticketConcert.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserDTO dto) {
        var user = new User();
        user.setId(dto.id());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setUsername(dto.username());
        return user;
    }

    public UserDTO toDto(User user){
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername());
    }

}
