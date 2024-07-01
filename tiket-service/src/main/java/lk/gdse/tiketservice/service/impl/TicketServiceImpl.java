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
        } else {

            String userId = restTemplate.getForObject("http://localhost:8080/api/v1/user/search/" + ticketDTO.getUserId(), String.class);
            String vehicleNO = restTemplate.getForObject("http://localhost:8080/api/v1/vehicle/search/" + ticketDTO.getVehicleNo(), String.class);

            if (userId != null && userId.equals(ticketDTO.getUserId()) && vehicleNO != null && vehicleNO.equals(ticketDTO.getVehicleNo())) {
                ticketRepo.save(modelMapper.map(ticketDTO, Ticket.class));
                return VarList.RSP_SUCCESS;
            } else {

                return VarList.RSP_NO_DATA_FOUND;
            }
        }
    }



    @Override
    public String updateTicket(TicketDTO ticketDTO) {

        if (ticketRepo.existsById(ticketDTO.getTicketNo())){

            String userId = restTemplate.getForObject("http://localhost:8080/api/v1/user/search/" + ticketDTO.getUserId(), String.class);
            String vehicleNO = restTemplate.getForObject("http://localhost:8080/api/v1/vehicle/search/" + ticketDTO.getVehicleNo(), String.class);

            if (userId != null && userId.equals(ticketDTO.getUserId()) && vehicleNO != null && vehicleNO.equals(ticketDTO.getVehicleNo())) {
                ticketRepo.save(modelMapper.map(ticketDTO, Ticket.class));
                return VarList.RSP_SUCCESS;
            } else {

                return VarList.RSP_NO_DATA_FOUND;
            }

        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }

    }

    @Override
    public TicketDTO getTicket(String ticketNo) {
        if (ticketRepo.existsById(ticketNo)) {
            Ticket ticket = ticketRepo.findById(ticketNo).orElse(null);
            return modelMapper.map(ticket,TicketDTO.class);
        }

        return null;

    }

}
