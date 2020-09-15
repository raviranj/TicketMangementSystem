package com.ticket.management.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "TICKETS")
public class Tickets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_ID", nullable = false)
	private Long ticketId;

	@ManyToOne
	private Employee loggedBy;

	@Column(name = "RAISED_DATE",nullable=false)
	private LocalDateTime raisedDate;


	@Column(name = "SEVERITY")
	private String severity;

	@Column(name = "TICKET_DESC")
	private String ticketDescription;

	@ManyToOne
	private Employee resolvedBy;

	@Column(name = "RESOLUTION")
	private String resolution;

	@Column(name = "RESOLVED_DATE",nullable=true)
	private LocalDateTime resolvedDate;

	@Column(name = "STATUS")
	private String status;

}
