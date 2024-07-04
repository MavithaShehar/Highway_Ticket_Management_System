package lk.gdse.paymentservice.controller;

import lk.gdse.paymentservice.dto.PaymentDTO;
import lk.gdse.paymentservice.dto.ResponseDTO;
import lk.gdse.paymentservice.service.PaymentService;
import lk.gdse.paymentservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService ;
    private final ResponseDTO responseDTO;

    @PostMapping
    public String savePayment(@RequestBody PaymentDTO paymentDTO) {

        String req = paymentService.savePayment(paymentDTO);

        return  req;
    }

    @PutMapping
    public String updatePayment(@RequestBody PaymentDTO paymentDTO) {

        String req = paymentService.updatePayment(paymentDTO);

        return  req;
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity delete(@PathVariable String paymentId){

        try {
            String req = paymentService.delete(paymentId);
            if (req.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("SUCCESS");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For This ID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
