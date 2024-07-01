package lk.gdse.vehicleservice.service;

import lk.gdse.vehicleservice.dto.VehicleDTO;

public interface VehicleService {
    String saveVehicle(VehicleDTO vehicleDTO);
    String updateVehicle(VehicleDTO vehicleDTO);
    VehicleDTO getVehicle(String vehicleId);
    VehicleDTO getSearchVehicleNo(String vehicleId);
}
