package org.elmenus.drones.repository;

import jakarta.transaction.Transactional;
import org.elmenus.drones.model.entity.Drone;
import org.elmenus.drones.model.entity.DroneModel;
import org.elmenus.drones.model.entity.DroneState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class DroneRepositoryTests {
    private final DroneRepository droneRepository;

    @Autowired
    public DroneRepositoryTests(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Test
    @Transactional
    void saveDroneTest() {
        Drone drone = Drone.builder()
                .serialNumber("ez")
                .model(DroneModel.LIGHTWEIGHT)
                .weightLimit(100)
                .batteryCapacity(100)
                .state(DroneState.IDLE)
                .build();

        droneRepository.save(drone);

        Assertions.assertTrue(drone.getId() > 0);
    }

    @Test
    void getFoundDroneTest() {
        Optional<Drone> drone = droneRepository.findById(1L);

        Assertions.assertTrue(drone.isPresent());
        Assertions.assertEquals(1L, drone.get().getId());
    }

    @Test
    void getNotFoundDroneTest() {
        Optional<Drone> drone = droneRepository.findById(20L);

        Assertions.assertFalse(drone.isPresent());
    }
}