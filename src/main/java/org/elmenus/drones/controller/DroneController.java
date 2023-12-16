package org.elmenus.drones.controller;

import jakarta.validation.Valid;
import org.elmenus.drones.helpers.ApiResponse;
import org.elmenus.drones.helpers.NotFoundException;
import org.elmenus.drones.model.dto.DroneDTO;
import org.elmenus.drones.model.dto.MedicationDTO;
import org.elmenus.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/drones")
public class DroneController {
    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<DroneDTO>>> getAllDrones() {
        List<DroneDTO> drone = droneService.getAllDrones();
        ApiResponse<List<DroneDTO>> response = new ApiResponse<>("Drones fetched successfully", drone);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{droneId}")
    public ResponseEntity<ApiResponse<DroneDTO>> getDrone(@PathVariable("droneId") Long droneId) {
        DroneDTO droneDTO = droneService.getDrone(droneId);
        ApiResponse<DroneDTO> response = new ApiResponse<>("Drone fetched successfully", droneDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<DroneDTO>> createDrone(@RequestBody @Valid DroneDTO droneDTO) {
        DroneDTO drone = droneService.createDrone(droneDTO);
        ApiResponse<DroneDTO> response = new ApiResponse<>("Drone created successfully", drone);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<DroneDTO>> updateDrone(@PathVariable("id") Long id,
                                                             @RequestBody @Valid DroneDTO droneDTO) {
        try {
            droneService.updateDrone(id, droneDTO);
            ApiResponse<DroneDTO> response = new ApiResponse<>("Drone updated successfully", droneDTO);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            ApiResponse<DroneDTO> response = new ApiResponse<>(e.getMessage(), null);

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<ApiResponse<Void>> deleteDrone(@PathVariable("id") Long id) {
        try {
            droneService.deleteDrone(id);
            ApiResponse<Void> response = new ApiResponse<>("Drone deleted successfully", null);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            ApiResponse<Void> response = new ApiResponse<>(e.getMessage(), null);

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/load/{droneId}")
    public ResponseEntity<ApiResponse<Void>> loadMedications(@PathVariable("droneId") Long droneId,
                                                             @RequestBody List<MedicationDTO> medications) {
        droneService.loadMedications(droneId, medications);
        ApiResponse<Void> response = new ApiResponse<>("Medications loaded successfully", null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/available-to-load")
    public ResponseEntity<ApiResponse<List<DroneDTO>>> availableToLoad() {
        List<DroneDTO> availableDrones = droneService.availableToLoad();
        ApiResponse<List<DroneDTO>> response = new ApiResponse<>("Drones fetched successfully", availableDrones);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/battery-level/{droneId}")
    public ResponseEntity<ApiResponse<Integer>> getBatteryLevel(@PathVariable("droneId") Long droneId) {
        int batteryLevel = droneService.getBatteryLevel(droneId);
        ApiResponse<Integer> response = new ApiResponse<>("Battery level fetched successfully", batteryLevel);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
