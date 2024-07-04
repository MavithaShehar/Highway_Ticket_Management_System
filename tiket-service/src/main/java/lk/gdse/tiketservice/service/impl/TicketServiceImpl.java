package lk.gdse.tiketservice.service.impl;

import jakarta.transaction.Transactional;
import lk.gdse.tiketservice.dto.TicketDTO;
import lk.gdse.tiketservice.entity.Ticket;
import lk.gdse.tiketservice.repository.TicketRepo;
import lk.gdse.tiketservice.service.TicketService;
import lk.gdse.tiketservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@RequiredArgsConstructor

public class TicketServiceImpl implements TicketService {

    private final ModelMapper modelMapper;
    private final TicketRepo ticketRepo;
    private final RestTemplate restTemplate;

    @Override
    public String saveTicket(TicketDTO ticketDTO) {
        if (ticketRepo.existsById(ticketDTO.getTicketNo())) {
            return VarList.RSP_DUPLICATED;
        }

        if (isValidUserAndVehicle(ticketDTO)) {
            ticketRepo.save(modelMapper.map(ticketDTO, Ticket.class));
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }


    @Override
    public String updateTicket(TicketDTO ticketDTO) {
        if (ticketRepo.existsById(ticketDTO.getTicketNo()) && isValidUserAndVehicle(ticketDTO)) {
            ticketRepo.save(modelMapper.map(ticketDTO, Ticket.class));
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    private boolean isValidUserAndVehicle(TicketDTO ticketDTO) {
        String userId = restTemplate.getForObject("http://localhost:8080/api/v1/user/search/" + ticketDTO.getUserId(), String.class);
        String vehicleNo = restTemplate.getForObject("http://localhost:8080/api/v1/vehicle/search/" + ticketDTO.getVehicleNo(), String.class);

        return userId != null && userId.equals(ticketDTO.getUserId()) && vehicleNo != null && vehicleNo.equals(ticketDTO.getVehicleNo());
    }

    @Override
    public TicketDTO getTicket(String ticketNo) {
        if (ticketRepo.existsById(ticketNo)) {
            Ticket ticket = ticketRepo.findById(ticketNo).orElse(null);
            return modelMapper.map(ticket,TicketDTO.class);
        }

        return null;

    }

    @Override
    public String delete(String ticketNo) {
        if (ticketRepo.existsById(ticketNo)){
            ticketRepo.deleteById(ticketNo);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

}
