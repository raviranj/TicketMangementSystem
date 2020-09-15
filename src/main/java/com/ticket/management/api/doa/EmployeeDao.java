package com.ticket.management.api.doa;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticket.management.api.model.Employee;

@Repository
@Transactional
public interface EmployeeDao  extends JpaRepository<Employee, String>{
	Employee findEmployeeByMid(@Param("mid")String loggedBy);
}
