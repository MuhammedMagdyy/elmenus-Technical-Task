package org.elmenus.drones.service;

import jakarta.validation.Valid;
import org.elmenus.drones.model.dto.MedicationDTO;
import org.elmenus.drones.model.entity.Medication;
import org.elmenus.drones.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<MedicationDTO> getAllMedications() {
        List<Medication> medications = medicationRepository.findAll();

        return medications.stream()
                .map(MedicationDTO::toDto)
                .collect(Collectors.toList());
    }

    public MedicationDTO createMedication(@Valid MedicationDTO medicationDTO) {
        return MedicationDTO.toDto(medicationRepository.save(Medication.toEntity(medicationDTO)));
    }

    public List<MedicationDTO> getRelevantMedications(Long droneId) {
        List<Medication> loadedMedications = medicationRepository.findByDroneId(droneId);
        return loadedMedications.stream()
                .map(MedicationDTO::toDto)
                .toList();
    }
}
