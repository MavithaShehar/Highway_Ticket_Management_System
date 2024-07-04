package lk.gdse.paymentservice.service.impl;

import jakarta.transaction.Transactional;
import lk.gdse.paymentservice.dto.PaymentDTO;
import lk.gdse.paymentservice.dto.ResponseDTO;
import lk.gdse.paymentservice.dto.TicketDTO;
import lk.gdse.paymentservice.entity.Payment;
import lk.gdse.paymentservice.repository.PaymentRepo;
import lk.gdse.paymentservice.service.PaymentService;
import lk.gdse.paymentservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor

public class PaymentServiceImpl implements PaymentService {

    private final ModelMapper modelMapper;
    private final PaymentRepo  paymentRepo;
    private final RestTemplate restTemplate;


    @Override
    public String savePayment(PaymentDTO paymentDTO) {

        if (paymentRepo.existsById(paymentDTO.getPaymentNo())) {
            return "Payment is already defined " + paymentDTO;
        } else {
            try {
                String url = "http://localhost:8080/api/v1/ticket/" + paymentDTO.getTicketNo();

                ResponseEntity<ResponseDTO<TicketDTO>> responseEntity = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ResponseDTO<TicketDTO>>() {}
                );

                ResponseDTO<TicketDTO> responseDTO = responseEntity.getBody();

                if (responseDTO != null) {
                    TicketDTO ticketDTO = responseDTO.getContent();

                    if (ticketDTO != null && ticketDTO.getTicketNo().equals(paymentDTO.getTicketNo())) {
                        System.out.println("Ticket data is " + ticketDTO);

                        double remainingAmount =(paymentDTO.getGiven_Amount() - ticketDTO.getPrice())  ;

                        Payment payment = new Payment(
                                paymentDTO.getPaymentNo(),
                                new Date(),
                                ticketDTO.getPrice(),
                                paymentDTO.getGiven_Amount(),
                                remainingAmount,
                                ticketDTO.getTicketNo(),
                                ticketDTO.getUserId(),
                                ticketDTO.getVehicleNo()
                        );



                        paymentRepo.save(payment);
                        return "Payment is successful";
                    } else {
                        return "Ticket not found or ticket number mismatch";
                    }
                } else {
                    return "No response from ticket service";
                }
            } catch (Exception ex) {
                return "Error occurred while retrieving ticket: " + ex.getMessage();
            }
        }
    }


    @Override
    public String updatePayment(PaymentDTO paymentDTO) {
       if (paymentRepo.existsById(paymentDTO.getPaymentNo())){

           try {
               String url = "http://localhost:8080/api/v1/ticket/" + paymentDTO.getTicketNo();

               ResponseEntity<ResponseDTO<TicketDTO>> responseEntity = restTemplate.exchange(
                       url,
                       HttpMethod.GET,
                       null,
                       new ParameterizedTypeReference<ResponseDTO<TicketDTO>>() {}
               );

               ResponseDTO<TicketDTO> responseDTO = responseEntity.getBody();

               if (responseDTO != null) {
                   TicketDTO ticketDTO = responseDTO.getContent();

                   if (ticketDTO != null && ticketDTO.getTicketNo().equals(paymentDTO.getTicketNo())) {
                       System.out.println("Ticket data is " + ticketDTO);

                       double remainingAmount =(paymentDTO.getGiven_Amount() - ticketDTO.getPrice())  ;

                       Payment payment = new Payment(
                               paymentDTO.getPaymentNo(),
                               new Date(),
                               ticketDTO.getPrice(),
                               paymentDTO.getGiven_Amount(),
                               remainingAmount,
                               ticketDTO.getTicketNo(),
                               ticketDTO.getUserId(),
                               ticketDTO.getVehicleNo()
                       );



                       paymentRepo.save(payment);
                       return "Payment is successful";
                   } else {
                       return "Ticket not found or ticket number mismatch";
                   }
               } else {
                   return "No response from ticket service";
               }
           } catch (Exception ex) {
               return "Error occurred while retrieving ticket: " + ex.getMessage();
           }
       }else {
           return VarList.RSP_DUPLICATED;
       }
    }

    @Override
    public PaymentDTO getPayment(String paymentNo) {
        return null;
    }


}
