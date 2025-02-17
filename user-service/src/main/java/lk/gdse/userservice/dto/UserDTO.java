package lk.gdse.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String address;

    public String toString() {
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + password + '\'' +
                ", userEmail='" + email + '\'' +
                ", userEmail='" + phone + '\'' +
                ", userEmail='" + address + '\'' +
                '}';
    }
}


