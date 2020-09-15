package com.ticket.management.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="tickets")
@Table(name="EMPLOYEE")
public class Employee  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="MID")
	private String mid;
	
	@Column(name="EMPLOYEE_NAME")
	private String name;
	
	@Column(name="HIRE_DATE")
	private Date hiredDate;
	
	@Column(name="DEPT")
	private String department;
	
	@OneToMany(mappedBy="loggedBy") 
    private List<Tickets> tickets;

}
