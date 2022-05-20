package com.app.ticketingsystemserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public User(String firstName,
                String lastName,
                String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
