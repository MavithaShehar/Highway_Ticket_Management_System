package lk.gdse.userservice.service.impl;

import jakarta.transaction.Transactional;
import lk.gdse.userservice.dto.UserDTO;
import lk.gdse.userservice.entity.User;
import lk.gdse.userservice.repository.UserRepo;
import lk.gdse.userservice.service.UserService;
import lk.gdse.userservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepo userRepo;

    @Override
    public String saveUser(UserDTO userDTO) {

        if (userRepo.existsById(userDTO.getUserId())) {
            return VarList.RSP_DUPLICATED;
        } else {
            userRepo.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }
    }

    @Override
    public String updateUser(UserDTO userDTO) {

        if (userRepo.existsById(userDTO.getUserId())){
            userRepo.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }

    }

    @Override
    public UserDTO getUser(String userId) {
        if (userRepo.existsById(userId)) {
            User user = userRepo.findById(userId).orElse(null);
            return modelMapper.map(user,UserDTO.class);
        }

        return null;

    }

    @Override
    public UserDTO getSearchUser(String userId) {
        if (userRepo.existsById(userId)) {
            User user = userRepo.findById(userId).orElse(null);
            return modelMapper.map(user,UserDTO.class);
        }

        return null;
    }

}
