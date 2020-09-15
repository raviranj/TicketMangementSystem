package com.ticket.management.api.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class TicketExceptions extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
}
