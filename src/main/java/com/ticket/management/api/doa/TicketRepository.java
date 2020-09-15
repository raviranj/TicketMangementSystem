package com.ticket.management.api.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticket.management.api.model.Tickets;


@Repository
public interface TicketRepository  extends JpaRepository<Tickets,Long>{
	List<Tickets> findTicketsByStatus(@Param("status") String status);
}
