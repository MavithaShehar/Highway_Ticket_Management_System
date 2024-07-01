package lk.gdse.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDTO {
    private String ticketNo;

    private Date date;
    private double price;

    private String fromWhere;

    private String toWhere;

    private String vehicleNo;

    private String userId;

}