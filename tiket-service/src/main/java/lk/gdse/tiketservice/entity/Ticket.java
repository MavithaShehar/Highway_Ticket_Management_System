package lk.gdse.tiketservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
@Data
@Entity
public class Ticket {

    @Id
    @NotNull(message = "Ticket No cannot be null")
    private String ticketNo;

    private Date date;
    @NotNull(message = "Price No cannot be null")
    private double price;

    @NotNull(message = "From Where No cannot be null")
    private String fromWhere;

    @NotNull(message = "To Where No cannot be null")
    private String toWhere;

    @NotNull(message = "Vehicle No  cannot be null")
    private String vehicleNo;

    @NotNull(message = "User Id No cannot be null")
    private String userId;

}