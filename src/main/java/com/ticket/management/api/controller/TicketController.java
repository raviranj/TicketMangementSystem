package com.ticket.management.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.management.api.model.TicketRequest;
import com.ticket.management.api.model.TicketResposne;
import com.ticket.management.api.service.TicketService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/ticketManagement")
@Slf4j
public class TicketController {

	@Autowired
	@Getter
	@Setter
	private TicketService ticketService;

	@PostMapping(value = "/createTicket", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createTicket(@RequestBody TicketRequest ticketInput) throws Exception {
		log.info(" request body " + ticketInput.toString());
		HttpStatus status = null;
		String message = null;
		try {
			message = ticketService.createTicket(ticketInput);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error(e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			message = e.getMessage();
		}
		return new ResponseEntity<String>(message, status);
	}

	@PutMapping(value = "/closeTicket", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> closeTicket(@RequestBody TicketRequest ticketInput) throws Exception {
		log.info(" subscriber " + ticketInput);
		HttpStatus status = null;
		String message = null;
		try {
			message = ticketService.closeTicket(ticketInput);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error(e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			message = e.getMessage();
		}
		return new ResponseEntity<String>(message, status);
	}

	@RequestMapping(value = "/viewTicket", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TicketResposne> viewTicket() throws Exception {
		List<TicketResposne> ticketResposne = ticketService.viewTicket();
		return ticketResposne;
	}

}
