package lk.gdse.tiketservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
@Data
@Entity
public class Ticket {

    @Id
    private String ticketNo;

    private Date date;
    private double price;

    private String fromWhere;

    private String toWhere;


    private String vehicleNo;

    private String userId;

}