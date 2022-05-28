package com.app.ticketingsystemserver.controller;

import com.app.ticketingsystemserver.model.Ticket;
import com.app.ticketingsystemserver.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/tickets")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class TicketController {
    @Autowired
    private final TicketService ticketService;

    //Inject pendency in constructor and remove @Autowired

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket newTicket) {
        return ticketService.createNewTicket(newTicket);
    }

    @GetMapping
    public List<Ticket> fetchAllTickets() {
        return ticketService.getAllTickets();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void updateTicket(@RequestBody Ticket modifiedTicket) {
        ticketService.updateTicket(modifiedTicket);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{ticketId}")
    public void removeTicket(@PathVariable("ticketId") String ticketId) {
        ticketService.deleteTicket(ticketId);
    }
}
