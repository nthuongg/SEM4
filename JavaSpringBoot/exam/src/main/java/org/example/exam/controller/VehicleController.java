package org.example.exam.controller;

import org.example.exam.entity.Vehicle;
import org.example.exam.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;



    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        return "catalog";
    }

    //crud admin
    @GetMapping("/admin/vehicles")
    public String adminVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        return "admin/vehicles";
    }

    //add new vehicle
    @GetMapping("/admin/vehicle/new")
    public String newVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "admin/vehicle-form";
    }


    @GetMapping("/admin/vehicle/edit/{id}")
    public String editVehicle(@PathVariable("id") Integer id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "admin/edit-vehicle";
    }

    // Save vehicle (add new or update)
    @PostMapping("/admin/vehicles")
    public String saveVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
        vehicleService.saveVehicle(vehicle);
        return "redirect:/admin/vehicles";
    }


    // Cập nhật phương tiện
    @PostMapping("/admin/vehicle/edit/{id}")
    public String updateVehicle(@PathVariable("id") Integer id, @ModelAttribute("vehicle") Vehicle vehicle) {
        vehicle.setId(id); // Đảm bảo đối tượng vehicle có id đúng
        vehicleService.saveVehicle(vehicle);
        return "redirect:/admin/vehicles";
    }



    //Delete
    @GetMapping("/admin/vehicle/delete/{id}")
    public String deleteVehicle(@PathVariable("id") Integer id, Model model) {
        vehicleService.deleteVehicleById(id);
        return "redirect:/admin/vehicles";
    }

}
