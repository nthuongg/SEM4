package org.example.exam.service;

import org.example.exam.entity.Vehicle;
import org.example.exam.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(int id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicleById(int id) {
        vehicleRepository.deleteById(id);
    }
}
