package com.dusanbran.ticketConcert.controller;

import com.dusanbran.ticketConcert.controller.dto.TicketDTO;
import com.dusanbran.ticketConcert.controller.dto.UserDTO;
import com.dusanbran.ticketConcert.controller.mapper.UserMapper;
import com.dusanbran.ticketConcert.domain.Ticket;
import com.dusanbran.ticketConcert.domain.User;
import com.dusanbran.ticketConcert.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public UserDTO addNewUser(@RequestBody User newUser) throws Exception {
        return userMapper.toDto(userService.addNewUser(newUser));
    }

   @GetMapping("/{user_id}/boughtTickets")
    public List<TicketDTO> getBoughtTickets(@PathVariable int user_id) throws Exception {
        return userService.getBoughtTickets(user_id);
   }

   @GetMapping
    public List<UserDTO> getAll(){
        return userService.getAll();
   }

}
