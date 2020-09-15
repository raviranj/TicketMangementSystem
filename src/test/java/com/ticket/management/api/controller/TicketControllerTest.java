
package com.ticket.management.api.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ticket.management.api.model.TicketRequest;
import com.ticket.management.api.model.TicketResposne;
import com.ticket.management.api.service.TicketService;

public class TicketControllerTest {

	@InjectMocks
	private TicketController ticketControllerCreate;
	
	@InjectMocks
	private TicketController ticketControllerView;
	
	@InjectMocks
	private TicketController ticketControllerClose;

	@Mock
	private TicketService closeTicketService;
	
	@Mock
	private TicketService createTicketService;
	
	@Mock
	private TicketService viewTicketService;

	private static ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);

	@Before
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
		ticketControllerCreate.setTicketService(createTicketService);
		ticketControllerClose.setTicketService(closeTicketService);
		ticketControllerView.setTicketService(viewTicketService);
	}

	//@Test
	public void createTicketTest() throws Exception {
		Mockito.when(createTicketService.createTicket(Mockito.any(TicketRequest.class))).thenReturn("Success");
		ResponseEntity<String> closeTicket = ticketControllerCreate.createTicket(Mockito.any(TicketRequest.class));
		assertEquals(response, closeTicket);
	}

	@Test
	public void closeTicketTest() throws Exception {
		Mockito.when(closeTicketService.closeTicket(Mockito.any(TicketRequest.class))).thenReturn("Tickets : 1 is closed");
		ResponseEntity<String> closeTicket = ticketControllerClose.closeTicket(Mockito.any(TicketRequest.class));
		assertEquals(response, closeTicket);
	}

	@Test
	public void viewTicketTest() throws Exception {
		
		TicketResposne ticket1 = new TicketResposne("Swetha",1L,"Normal",25.3 ,"hkjdhd","M1034118");
		TicketResposne ticket2 = new TicketResposne("Ravi",2L,"Normal",5.3 ,"hkjdhd","M1034119");
	       
		List<TicketResposne> ticketResposne=new ArrayList<TicketResposne>();
		ticketResposne.add(ticket1);
		ticketResposne.add(ticket2);
		
		Mockito.when(viewTicketService.viewTicket()).thenReturn(ticketResposne);
		List<TicketResposne> viewTicket = ticketControllerView.viewTicket();
		
		assertEquals(2, viewTicket.size());
		assertEquals(ticket1.getEmployeeName(), viewTicket.get(0).getEmployeeName());
	}
}