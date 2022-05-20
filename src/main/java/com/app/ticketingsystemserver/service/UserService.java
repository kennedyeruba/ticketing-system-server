package com.app.ticketingsystemserver.service;

import com.app.ticketingsystemserver.model.User;
import com.app.ticketingsystemserver.repository.TicketRepository;
import com.app.ticketingsystemserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public User createNewUser(User newUser) {
        User persistedUser = new User(newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getEmail());

       return userRepository.insert(persistedUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public void deleteUser(String userId) {
        //Find tickets with user and update before deleting user
        userRepository.deleteById(userId);
    }
}
