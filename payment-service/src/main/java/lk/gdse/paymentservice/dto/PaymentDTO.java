package lk.gdse.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private String paymentNo;
    private Date date;
    private double price;
    private double given_Amount;
    private double remaining_Amount;
    private String ticketNo;
    private String userId;
    private String vehicleNo;

}
