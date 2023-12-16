package org.elmenus.drones.config;

import org.elmenus.drones.model.entity.Drone;
import org.elmenus.drones.model.entity.DroneModel;
import org.elmenus.drones.model.entity.DroneState;
import org.elmenus.drones.model.entity.Medication;
import org.elmenus.drones.repository.DroneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DatabaseSeeder {
    @Bean
    CommandLineRunner commandLineRunner(DroneRepository droneRepository) {

        return args -> {
            Drone drone_v1 = new Drone(
                    "123456",
                    DroneModel.LIGHTWEIGHT,
                    70,
                    100,
                    DroneState.IDLE
            );

            drone_v1.setState(DroneState.LOADING);

            Medication medication1_v1 = new Medication("medication1_v1", 0.5, "CODE_001", "image1.jpg", drone_v1);
            Medication medication2_v1 = new Medication("medication2_v1", 1.0, "CODE_002", "image2.jpg", drone_v1);
            Medication medication3_v1 = new Medication("medication3_v1", 0.8, "CODE_003", "image3.jpg", drone_v1);

            drone_v1.setMedications(Arrays.asList(medication1_v1, medication2_v1, medication3_v1));
            drone_v1.setState(DroneState.LOADED);

            Drone drone_v2 = new Drone(
                    "ABCEDFG",
                    DroneModel.HEAVYWEIGHT,
                    250,
                    100,
                    DroneState.IDLE
            );

            drone_v2.setState(DroneState.LOADING);

            Medication medication1_v2 = new Medication("medication1_v2", 0.5, "CODE_001", "image1.jpg", drone_v2);
            Medication medication2_v2 = new Medication("medication2_v2", 1.0, "CODE_002", "image2.jpg", drone_v2);
            Medication medication3_v2 = new Medication("medication3_v2", 0.8, "CODE_003", "image3.jpg", drone_v1);


            drone_v2.setMedications(Arrays.asList(medication1_v2, medication2_v2, medication3_v2));
            drone_v2.setState(DroneState.LOADED);

            droneRepository.saveAll(Arrays.asList(drone_v1, drone_v2));
        };
    }
}