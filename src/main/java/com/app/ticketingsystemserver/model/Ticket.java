package com.app.ticketingsystemserver.model;

import com.app.ticketingsystemserver.TicketStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "tickets")
public class Ticket {
    @Id
    private String id;
    private LocalDateTime creationDate;
    private String title;
    private String description;
    private User assignee;
    private TicketStatus status;

    public Ticket(LocalDateTime creationDate,
                  String title,
                  String description,
                  User assignee,
                  TicketStatus  status) {
        this.creationDate = creationDate;
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.status = status;
    }
}
