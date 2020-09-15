package com.ticket.management.api.service;

import java.util.List;

import com.ticket.management.api.Exception.EmployeeException;
import com.ticket.management.api.Exception.TicketExceptions;
import com.ticket.management.api.model.TicketRequest;
import com.ticket.management.api.model.TicketResposne;

public interface TicketService {

	String createTicket(TicketRequest ticketInput) throws  EmployeeException;

	String closeTicket(TicketRequest ticketInput) throws EmployeeException, TicketExceptions;

	List<TicketResposne> viewTicket() throws TicketExceptions;
}
