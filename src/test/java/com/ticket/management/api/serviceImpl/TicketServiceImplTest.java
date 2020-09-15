package com.ticket.management.api.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ticket.management.api.Exception.EmployeeException;
import com.ticket.management.api.Exception.TicketExceptions;
import com.ticket.management.api.doa.EmployeeDao;
import com.ticket.management.api.doa.TicketDao;
import com.ticket.management.api.doaImpl.TicketDaoImpl;
import com.ticket.management.api.model.Employee;
import com.ticket.management.api.model.TicketRequest;
import com.ticket.management.api.model.TicketResposne;
import com.ticket.management.api.model.Tickets;

public class TicketServiceImplTest {

	@Mock
	private TicketDao ticketDao = new TicketDaoImpl();

	@Mock
	private EmployeeDao employeeDao;

	@InjectMocks
	private TicketServiceImpl ticketServiceImpl;

	private Employee emp;

	private TicketRequest tr;

	private Tickets tickets = new Tickets();

	@Before
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
		ticketServiceImpl.setEmployeeDao(employeeDao);
		ticketServiceImpl.setTicketDao(ticketDao);
		emp = new Employee();
		emp.setName("M100102");
		emp.setName("Swetha");
		emp.setDepartment("BIZ");
		

		tr = new TicketRequest();
		tr.setLoggedBy("M1034104");
		tr.setResolution("HardwareOk");
		tr.setResolvedBy("M100101");
		tr.setTicketId(1L);
		tickets.setStatus("OPEN");

	}

	@Test
	public void createTicket() throws EmployeeException {
		Mockito.when(employeeDao.findEmployeeByMid(Mockito.any())).thenReturn(emp);
		Tickets t = new Tickets();
		Mockito.when(ticketDao.save(Mockito.any())).thenReturn(t);
		String expected = "Success";
		String actual = ticketServiceImpl.createTicket(tr);
		assertEquals(expected, actual);
	}

	@Test
	public void closeTicket() throws TicketExceptions, EmployeeException {
		Mockito.when(employeeDao.findEmployeeByMid(Mockito.any())).thenReturn(emp);
		Mockito.when(ticketDao.findTicketById(Mockito.any())).thenReturn(tickets);

		Tickets t = new Tickets();
		t.setTicketId(1L);
		Mockito.when(ticketDao.save(Mockito.any())).thenReturn(t);
		String expected = "Ticket: " + 1L + " is closed";
		String actual = ticketServiceImpl.closeTicket(tr);
		assertEquals(expected, actual);
	}

	@Test
	public void viewTicket() throws EmployeeException, TicketExceptions {
		List<Tickets> actualList = new ArrayList<>();
		Tickets tickets = new Tickets();
		tickets.setLoggedBy(emp);
		tickets.setRaisedDate(LocalDateTime.now().minusMinutes(1523));
		tickets.setResolution("changed the Ram");
		tickets.setStatus("CLOSE");
		tickets.setTicketDescription("system is getting hanged");
		tickets.setTicketId(1L);
		tickets.setResolvedDate(LocalDateTime.now());
		tickets.setResolvedBy(emp);
		tickets.setSeverity("Normal");
		actualList.add(tickets);
		Mockito.when(ticketDao.findTicketsByStatus(Mockito.any())).thenReturn(actualList);
		List<TicketResposne> expectedList = new ArrayList<TicketResposne>();
		TicketResposne ticketRespnse = new TicketResposne("Swetha", 1L, "Normal", 25.38, "system is getting hanged",
				"Swetha");
		expectedList.add(ticketRespnse);
		List<TicketResposne> actual = ticketServiceImpl.viewTicket();
		assertEquals(expectedList, actual);
	}
}
