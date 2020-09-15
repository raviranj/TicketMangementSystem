package com.ticket.management.api.doa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ticket.management.api.model.Tickets;

@Repository
public interface TicketDao {

	Tickets save(Tickets tickets);

	String closeTicket(Tickets tickets);

	Tickets findTicketById(Long ticketId);

	List<Tickets> findTicketsByStatus(String status);

}
