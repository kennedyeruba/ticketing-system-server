package com.app.ticketingsystemserver.service;

import com.app.ticketingsystemserver.TicketStatus;
import com.app.ticketingsystemserver.model.Ticket;
import com.app.ticketingsystemserver.model.User;
import com.app.ticketingsystemserver.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Ticket createNewTicket(Ticket newTicket) {
        String userId = newTicket.getAssignee().getId();
        Ticket persistedTicket = new Ticket(LocalDateTime.now(),
                newTicket.getTitle(),
                newTicket.getDescription(),
                newTicket.getAssignee(),
                TicketStatus.OPEN);

        if (userId.length() > 1) {
            Optional<User> assignedUser = userService.getUserById(userId);
            assignedUser.ifPresent(persistedTicket::setAssignee);
        }

        return ticketRepository.insert(persistedTicket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public void updateTicket(Ticket modifiedTicket) {
        Optional<Ticket> ticket = ticketRepository.findById(modifiedTicket.getId());
        if (ticket.isPresent()) {
            ticket.get().setTitle(modifiedTicket.getTitle());
            ticket.get().setDescription(modifiedTicket.getDescription());
            ticket.get().setStatus(modifiedTicket.getStatus());

            if (!modifiedTicket.getAssignee().getId().equals(ticket.get().getAssignee().getId())) {
                Optional<User> assignedUser = userService.getUserById(modifiedTicket.getAssignee().getId());
                assignedUser.ifPresent(ticket.get()::setAssignee);
            } else {
                ticket.get().setAssignee(modifiedTicket.getAssignee());
            }
        }

        mongoTemplate.save(ticket.get());
    }

    public void deleteTicket(String ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
