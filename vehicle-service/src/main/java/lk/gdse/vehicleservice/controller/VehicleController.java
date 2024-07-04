package lk.gdse.vehicleservice.controller;

import lk.gdse.vehicleservice.dto.ResponseDTO;
import lk.gdse.vehicleservice.dto.VehicleDTO;
import lk.gdse.vehicleservice.service.VehicleService;
import lk.gdse.vehicleservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vehicle")
@RequiredArgsConstructor

public class VehicleController {

    private final VehicleService vehicleService ;
    private final ResponseDTO responseDTO ;

    @GetMapping("/health")
    public String health() {
        return "vehicle service is running" ;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO){

        try {
            String req = vehicleService.saveVehicle(vehicleDTO);
            return buildResponse(req, vehicleDTO);
        } catch (Exception ex) {
            return buildErrorResponse(ex);
        }
    }
    @PutMapping
    public ResponseEntity<ResponseDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO){

        try {
            String req = vehicleService.updateVehicle(vehicleDTO);
            return buildResponse(req, vehicleDTO);
        } catch (Exception ex) {
            return buildErrorResponse(ex);
        }
    }

    @GetMapping("/search/{vehicleNo}")
    public String getVehicleNoSearch(@PathVariable String vehicleNo) {

        VehicleDTO vehicleDTO = vehicleService.getSearchVehicleNo(vehicleNo);

        if (vehicleDTO != null && vehicleDTO.getVehicleNo() != null) {

            return vehicleDTO.getVehicleNo();
        } else {
            return null;
        }
    }

    @GetMapping("/{vehicleNo}")
    public ResponseEntity getVehicle(@PathVariable String vehicleNo){
        try {
            VehicleDTO vehicleDTO = vehicleService.getVehicle(vehicleNo);
            if (vehicleDTO !=null){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("SUCCESS");
                responseDTO.setContent(vehicleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("ERROR");
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

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity delete(@PathVariable String vehicleId){

        try {
            String req = vehicleService.delete(vehicleId);
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

    private ResponseEntity<ResponseDTO> buildResponse(String req, VehicleDTO vehicleDTO) {
        if ("00".equals(req)) {
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("SUCCESS");
            responseDTO.setContent(vehicleDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } else if ("06".equals(req)) {
            responseDTO.setCode(VarList.RSP_DUPLICATED);
            responseDTO.setMessage("NOT SUCCESS");
            responseDTO.setContent(vehicleDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        } else {
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("ERROR");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<ResponseDTO> buildErrorResponse(Exception ex) {
        responseDTO.setCode(VarList.RSP_ERROR);
        responseDTO.setMessage(ex.getMessage());
        responseDTO.setContent("wrong Id");
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
