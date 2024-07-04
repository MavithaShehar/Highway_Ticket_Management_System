package lk.gdse.userservice.service;

import lk.gdse.userservice.dto.UserDTO;

public interface UserService {
    String saveUser(UserDTO userDTO);
    String updateUser(UserDTO userDTO);
    UserDTO getUser(String userId);
    UserDTO getSearchUser(String userId);
    public String delete(String userId) ;
}
