package com.ticket.management.api.doaImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ticket.management.api.doa.TicketDao;
import com.ticket.management.api.doa.TicketRepository;
import com.ticket.management.api.model.Tickets;

import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Slf4j
public class TicketDaoImpl implements TicketDao {

	@Autowired(required = true)
	private TicketRepository ticketrepo;

	@Override
	public Tickets save(Tickets tickets) {
		return ticketrepo.save(tickets);
	}

	@Override
	public String closeTicket(Tickets tickets) {
		ticketrepo.save(tickets);
		return null;
	}

	@Override
	public Tickets findTicketById(Long ticketId) {

		return ticketrepo.findById(ticketId).get();
	}

	@Override
	public List<Tickets> findTicketsByStatus(String status) {
		return ticketrepo.findTicketsByStatus(status);
	}

}