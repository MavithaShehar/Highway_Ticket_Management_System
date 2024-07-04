package lk.gdse.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @NotBlank(message = "User ID is NO be Empty")
    private String userId;
    @NotBlank(message = "Name is NO be Empty")
    private String userName;
    @NotBlank(message = "Password is NO be Empty")
    private String password;
    @NotBlank(message = "Email is NO be Empty")
    private String email;
    @NotBlank(message = "Phone Number is NO be Empty")
    private String phone;
    @NotBlank(message = "Address is NO be Empty")
    private String address;
}
