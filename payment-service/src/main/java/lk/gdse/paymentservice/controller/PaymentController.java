package lk.gdse.paymentservice.controller;

import lk.gdse.paymentservice.dto.PaymentDTO;
import lk.gdse.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService ;

    @PostMapping
    public String savePayment(@RequestBody PaymentDTO paymentDTO) {

        String req = paymentService.savePayment(paymentDTO);

        return "response is "+ req;
    }


}
