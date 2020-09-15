package com.ticket.management.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResposne {

	private String employeeName;
	private Long ticketId;
	private String severity;
	private double turnaroundTime;
	private String discription;
	private String resolvedBy;
}
