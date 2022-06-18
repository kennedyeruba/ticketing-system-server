package com.app.ticketingsystemserver.controller;

import com.app.ticketingsystemserver.model.User;
import com.app.ticketingsystemserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    //Inject dependency in constructor and remove @Autowired

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userService.createNewUser(newUser);
    }

    @GetMapping
    public List<User> fetchAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Optional<User> fetchUserById(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void removeUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
    }
}
