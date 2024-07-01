package lk.gdse.paymentservice.service;

import lk.gdse.paymentservice.dto.PaymentDTO;

public interface PaymentService {
    String savePayment(PaymentDTO paymentDTO);
    String updatePayment(PaymentDTO paymentDTO);
    PaymentDTO getPayment(String paymentNo);
}
