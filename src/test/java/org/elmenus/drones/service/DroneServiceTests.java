package org.elmenus.drones.service;

import jakarta.transaction.Transactional;
import org.elmenus.drones.helpers.BadRequestException;
import org.elmenus.drones.model.dto.MedicationDTO;
import org.elmenus.drones.model.entity.Drone;
import org.elmenus.drones.model.entity.DroneModel;
import org.elmenus.drones.model.entity.DroneState;
import org.elmenus.drones.model.entity.Medication;
import org.elmenus.drones.repository.DroneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DroneServiceTests {
    private final DroneRepository droneRepository;
    private final DroneService droneService;

    @Autowired
    public DroneServiceTests(DroneRepository droneRepository,
                             DroneService droneService) {
        this.droneRepository = droneRepository;
        this.droneService = droneService;
    }

    @Test
    @Transactional
    void loadMedicationsTest() {
        Drone drone = Drone.builder()
                .serialNumber("ABCDE")
                .model(DroneModel.CRUISERWEIGHT)
                .weightLimit(100)
                .batteryCapacity(100)
                .state(DroneState.LOADED)
                .build();

        droneRepository.save(drone);

        List<Medication> medications = List.of(new Medication("HiHIHI", 0.5, "CODE_1", "image.png", drone));
        List<MedicationDTO> medicationDTOList = MedicationDTO.toDtoList(medications);

        drone.setMedications(medications);

        Assertions.assertThrows(BadRequestException.class,
                () -> droneService.loadMedications(drone.getId(), medicationDTOList)
        );

    }
}

