package com.app.ticketingsystemserver.repository;

import com.app.ticketingsystemserver.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> { }
