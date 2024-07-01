package lk.gdse.tiketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseDTO<T> {

    private String code;
    private String message;
    private T content;

}
