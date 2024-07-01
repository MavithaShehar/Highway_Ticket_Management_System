package lk.gdse.vehicleservice.service.impl;

import jakarta.transaction.Transactional;
import lk.gdse.vehicleservice.dto.VehicleDTO;
import lk.gdse.vehicleservice.entity.Vehicle;
import lk.gdse.vehicleservice.repo.VehicleRepo;
import lk.gdse.vehicleservice.service.VehicleService;
import lk.gdse.vehicleservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor

public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepo vehicleRepo;
    private final ModelMapper modelMapper;

    @Override
    public String saveVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepo.existsById(vehicleDTO.getVehicleNo())){
            return VarList.RSP_DUPLICATED;
        }else {
            vehicleRepo.save(modelMapper.map(vehicleDTO, Vehicle.class));
            return VarList.RSP_SUCCESS;
        }
    }

    @Override
    public String updateVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepo.existsById(vehicleDTO.getVehicleNo())){
            vehicleRepo.save(modelMapper.map(vehicleDTO, Vehicle.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    @Override
    public VehicleDTO getVehicle(String vehicleNo) {
        if (vehicleRepo.existsById(vehicleNo)) {
            Vehicle vehicle = vehicleRepo.findById(vehicleNo).orElse(null);
                return modelMapper.map(vehicle,VehicleDTO.class);
        }

        return null;

    }

    @Override
    public VehicleDTO getSearchVehicleNo(String vehicleNo) {

        if (vehicleRepo.existsById(vehicleNo)) {
            Vehicle vehicle = vehicleRepo.findById(vehicleNo).orElse(null);
            return modelMapper.map(vehicle,VehicleDTO.class);
        }
        return null;
    }
}
