package com.app.ticketingsystemserver.repository;

import com.app.ticketingsystemserver.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> { }
