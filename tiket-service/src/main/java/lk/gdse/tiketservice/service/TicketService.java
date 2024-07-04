package lk.gdse.tiketservice.service;

import lk.gdse.tiketservice.dto.TicketDTO;

public interface TicketService {
    String saveTicket(TicketDTO ticketDTO);
    String updateTicket(TicketDTO ticketDTO);
    TicketDTO getTicket(String ticketId);
    public String delete(String ticketNo) ;
}
