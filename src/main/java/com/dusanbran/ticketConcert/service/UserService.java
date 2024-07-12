package com.dusanbran.ticketConcert.service;

import com.dusanbran.ticketConcert.controller.dto.TicketDTO;
import com.dusanbran.ticketConcert.controller.dto.UserDTO;
import com.dusanbran.ticketConcert.controller.mapper.TicketMapper;
import com.dusanbran.ticketConcert.controller.mapper.UserMapper;
import com.dusanbran.ticketConcert.domain.User;
import com.dusanbran.ticketConcert.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final TicketMapper ticketMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, TicketMapper ticketMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.ticketMapper = ticketMapper;
    }

    public User addNewUser(User newUser) throws Exception {
        Optional<User> optionalUser = userRepository.findByUsername(newUser.getUsername());
        if(optionalUser.isEmpty()){
            return userRepository.save(newUser);
        }
        else throw new Exception("There is a user with that username, please try different username.");
    }

    public List<TicketDTO> getBoughtTickets(int userId) throws Exception {
        Optional<User> optionalUser = userRepository.findById((long) userId);
        if(optionalUser.isPresent()){
            return optionalUser.get().getTickets()
                    .stream()
                    .map(ticketMapper::toTicketDTO)
                    .collect(Collectors.toList());
        }
        else throw new Exception("There is no user with that id, please try different id.");
    }


    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
