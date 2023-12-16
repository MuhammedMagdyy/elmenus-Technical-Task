package org.elmenus.drones.controller;

import jakarta.validation.Valid;
import org.elmenus.drones.helpers.ApiResponse;
import org.elmenus.drones.model.dto.MedicationDTO;
import org.elmenus.drones.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medications")
public class MedicationController {
    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<MedicationDTO>>> getAllMedications() {
        List<MedicationDTO> medication = medicationService.getAllMedications();
        ApiResponse<List<MedicationDTO>> response = new ApiResponse<>("Medications fetched successfully", medication);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ApiResponse<MedicationDTO>> createMedication(@RequestBody @Valid MedicationDTO medicationDTO) {
        MedicationDTO medication = medicationService.createMedication(medicationDTO);
        ApiResponse<MedicationDTO> response = new ApiResponse<>("Medication created successfully", medication);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{droneId}")
    public ResponseEntity<ApiResponse<List<MedicationDTO>>> relevantMedications(@PathVariable("droneId") Long droneId) {
        List<MedicationDTO> medicationDTOS = medicationService.getRelevantMedications(droneId);
        ApiResponse<List<MedicationDTO>> response = new ApiResponse<>("Medications fetched successfully", medicationDTOS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}