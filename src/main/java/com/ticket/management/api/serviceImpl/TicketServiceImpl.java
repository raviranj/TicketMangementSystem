package com.ticket.management.api.serviceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.management.api.Exception.EmployeeException;
import com.ticket.management.api.Exception.TicketExceptions;
import com.ticket.management.api.doa.EmployeeDao;
import com.ticket.management.api.doa.TicketDao;
import com.ticket.management.api.model.Employee;
import com.ticket.management.api.model.TicketRequest;
import com.ticket.management.api.model.TicketResposne;
import com.ticket.management.api.model.Tickets;
import com.ticket.management.api.service.TicketService;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

	public static final String closedstatus = "CLOSED";
	public static final String openstatus = "OPEN";
	public static final String department = "ISSD";

	@Autowired
	@Setter
	private TicketDao ticketDao;

	@Autowired
	@Setter
	private EmployeeDao employeeDao;

	@Override
	public String createTicket(TicketRequest ticketInput) throws EmployeeException {
		String savedStatus = null;
		Tickets tickets = null;
		Employee emp = employeeDao.findEmployeeByMid(ticketInput.getLoggedBy());
		log.info("employee info object " + emp.toString());
		log.info("employee info object " + emp.getDepartment());
		if (emp != null && (!emp.getDepartment().equals(department))) {
			tickets = new Tickets();
			tickets.setLoggedBy(emp);
			tickets.setRaisedDate(LocalDateTime.now().minusMinutes(5));
			tickets.setSeverity(ticketInput.getSeverity());
			tickets.setTicketDescription(ticketInput.getTicketDescription());
			tickets.setStatus("OPEN");
			log.info("tickets info " + tickets);
			try {
				ticketDao.save(tickets);
				savedStatus = "Success";
			} catch (Exception e) {
				log.info(e.getMessage());
			}

		} else {
			throw new EmployeeException(
					"Employee is not authorised to logged in " + emp.getMid() + " department " + emp.getDepartment());
		}
		return savedStatus;
	}

	@Override
	public String closeTicket(TicketRequest ticketInput) throws EmployeeException, TicketExceptions {
		String message = null;
		Tickets tickets = ticketDao.findTicketById(ticketInput.getTicketId());
		if (tickets != null && tickets.getStatus().equals(openstatus)) {
			Employee emp = employeeDao.findEmployeeByMid(ticketInput.getResolvedBy());
			if (emp != null) {
				tickets.setResolution(ticketInput.getResolution());
				tickets.setResolvedBy(emp);
				tickets.setResolvedDate(LocalDateTime.now());
				tickets.setStatus(closedstatus);
				try {
					Tickets saved = ticketDao.save(tickets);
					message = "Ticket: " + saved.getTicketId() + " is closed";
				} catch (Exception e) {
					log.info(e.getMessage());
				}
			} else {
				throw new EmployeeException("Resolving Employee mid is not available " + ticketInput.getResolvedBy());
			}
		} else {
			throw new TicketExceptions(
					"Ticket id  already closed " + tickets.getTicketId() + " status " + tickets.getStatus());
		}
		return message;
	}

	@Override
	public List<TicketResposne> viewTicket() throws TicketExceptions {
		List<TicketResposne> ticketResponse = new ArrayList<TicketResposne>();
		List<Tickets> resultList = ticketDao.findTicketsByStatus(closedstatus);
		if (resultList != null) {
			resultList.stream().forEach(e -> {
				TicketResposne response = new TicketResposne();
				response.setEmployeeName(e.getLoggedBy().getName());
				response.setSeverity(e.getSeverity());
				response.setDiscription(e.getTicketDescription());
				response.setResolvedBy(e.getResolvedBy().getName());
				response.setTicketId(e.getTicketId());
				response.setTurnaroundTime(calculateTunAroundTime(e.getRaisedDate(), e.getResolvedDate()));
				ticketResponse.add(response);
			});
		} else {
			throw new TicketExceptions("No tickets are avaible to close");
		}
		return ticketResponse;
	}

	private double calculateTunAroundTime(LocalDateTime raisedDate, LocalDateTime resolvedDate) {
		Duration duration = Duration.between(raisedDate, resolvedDate);
		long minutes = duration.toMinutes();
		double hours = (double) minutes / 60.0;
		double diff = Math.round(hours * 100.0) / 100.0;
		return diff;

	}

}
