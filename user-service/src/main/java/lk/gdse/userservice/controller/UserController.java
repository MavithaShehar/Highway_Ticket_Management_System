package lk.gdse.userservice.controller;

import lk.gdse.userservice.dto.ResponseDTO;
import lk.gdse.userservice.dto.UserDTO;
import lk.gdse.userservice.service.UserService;
import lk.gdse.userservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseDTO<UserDTO> responseDTO;

    @GetMapping("/health")
    public String health() {
        return "user service is running";
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> saveUser(@RequestBody UserDTO userDTO) {
        try {
            String req = userService.saveUser(userDTO);
            return buildResponse(req, userDTO);
        } catch (Exception ex) {
            return null;
        }
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO userDTO) {
        try {
            String req = userService.updateUser(userDTO);
            return buildResponse(req, userDTO);
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/search/{userId}")
    public String getUserSearch(@PathVariable String userId) {

        System.out.println("user is " + userId);

        UserDTO userDTO = userService.getSearchUser(userId);

        if (userDTO != null && userDTO.getUserId() != null) {

            return userDTO.getUserId();
        } else {
            return null;
        }
    }


    @GetMapping("/{userId}")
    public ResponseEntity getUser(@PathVariable String userId){

        System.out.println(userId);

        try {
            UserDTO userDTO = userService.getUser(userId);
            if (userDTO !=null){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("SUCCESS");
                responseDTO.setContent(userDTO);
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

    private ResponseEntity<ResponseDTO> buildResponse(String req, UserDTO userDTO) {
        if ("00".equals(req)) {
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("SUCCESS");
            responseDTO.setContent(userDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } else if ("06".equals(req)) {
            responseDTO.setCode(VarList.RSP_DUPLICATED);
            responseDTO.setMessage("NOT SUCCESS");
            responseDTO.setContent(userDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        } else {
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("ERROR");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<ResponseDTO<UserDTO>> buildErrorResponse(Exception ex) {
        responseDTO.setCode(VarList.RSP_ERROR);
        responseDTO.setMessage(ex.getMessage());
        responseDTO.setContent(null);
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
