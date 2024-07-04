package lk.gdse.vehicleservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Vehicle {

    @Id
    @NotNull(message = "vehicle No cannot be null")
    private String vehicleNo ;
    @NotNull(message = "vehicleType No cannot be null")
    private String vehicleType ;
    @NotNull(message = "vehicle Name No cannot be null")
    private String vehicleName ;
}
