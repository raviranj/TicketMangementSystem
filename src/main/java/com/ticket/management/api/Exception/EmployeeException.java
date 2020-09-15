package com.ticket.management.api.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeException  extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
}
