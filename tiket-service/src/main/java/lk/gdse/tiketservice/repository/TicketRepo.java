package lk.gdse.tiketservice.repository;

import lk.gdse.tiketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket,String> {
}
