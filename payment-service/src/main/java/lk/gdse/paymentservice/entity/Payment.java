package lk.gdse.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "payment")
@Entity

public class Payment {

    @Id
    @NotBlank(message = "payment No is Not Be Null")
    private String paymentNo;
    private Date date;
    private double price;
    private double given_Amount;
    private double remaining_Amount;
    private String ticketNo;
    @NotBlank(message = "User Id No is Not Be Null")
    private String userId;
    @NotBlank(message = "Vehicle No Id No is Not Be Null")
    private String vehicleNo;
}
