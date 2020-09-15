package com.ticket.management.api.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {

	private Long ticketId;
	private String loggedBy;
	private String raisedDate;
	private String severity;
	private String ticketDescription;
	private String resolvedBy;	
	private String resolution;
	private Date resolvedDate;
	private String status;
	
}
