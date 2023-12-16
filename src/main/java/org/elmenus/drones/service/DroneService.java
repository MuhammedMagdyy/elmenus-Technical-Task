package org.elmenus.drones.service;

import jakarta.validation.Valid;
import org.elmenus.drones.helpers.BadRequestException;
import org.elmenus.drones.helpers.NotFoundException;
import org.elmenus.drones.model.dto.DroneDTO;
import org.elmenus.drones.model.dto.MedicationDTO;
import org.elmenus.drones.model.entity.Drone;
import org.elmenus.drones.model.entity.DroneState;
import org.elmenus.drones.model.entity.Medication;
import org.elmenus.drones.repository.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneService {
    private final DroneRepository droneRepository;
    private static final Logger logger = LoggerFactory.getLogger(DroneService.class);

    @Autowired
    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public List<DroneDTO> getAllDrones() {
        List<Drone> drones = droneRepository.findAll();

        return drones.stream()
                .map(DroneDTO::toDto)
                .collect(Collectors.toList());
    }

    public DroneDTO getDrone(Long id) {
        return DroneDTO.toDto(droneRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Drone not found")
                ));
    }

    public DroneDTO createDrone(@Valid DroneDTO droneDTO) {
        return DroneDTO.toDto(droneRepository.save(Drone.toEntity(droneDTO)));
    }

    public void updateDrone(Long id, @Valid DroneDTO droneDTO) {
        droneRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Drone not found")
                );

        droneDTO.setId(id);
        droneRepository.save(Drone.toEntity(droneDTO));
    }

    public void deleteDrone(Long id) {
        boolean exists = droneRepository.existsById(id);

        if (!exists) {
            throw new NotFoundException("Drone not found");
        }

        droneRepository.deleteById(id);
    }

    private void changeState(long droneId, DroneState state) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(
                        () -> new NotFoundException("Drone not found")
                );
        drone.setState(state);
        droneRepository.save(drone);
    }

    public void loadMedications(Long droneId, List<MedicationDTO> medications) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(
                        () -> new NotFoundException("Drone not found")
                );

        if (drone.getState() != DroneState.IDLE) {
            throw new BadRequestException("Drone must be in IDLE state to load");
        }

        try {
            double totalWeight = medications.stream()
                    .mapToDouble(MedicationDTO::getWeight).sum();


            if (drone.getBatteryCapacity() < 25) {
                throw new BadRequestException("Low battery level - less than 25%");
            }

            if (totalWeight > drone.getWeightLimit()) {
                throw new BadRequestException("Total weight limit exceeded");
            }

            changeState(droneId, DroneState.LOADING);

            List<Medication> medicationEntities = Medication.toListEntity(medications);
            for (Medication medication : medicationEntities) {
                medication.setDrone(drone);
            }
            drone.setMedications(medicationEntities);
            drone.setState(DroneState.LOADED);

            droneRepository.save(drone);
        } catch (BadRequestException e) {
            drone.setState(DroneState.IDLE);
            droneRepository.save(drone);
            throw e;
        }
    }

    public List<DroneDTO> availableToLoad() {
        List<Drone> availableDrones = droneRepository.findByState(DroneState.IDLE);

        return availableDrones.stream()
                .map(DroneDTO::toDto)
                .collect(Collectors.toList());
    }

    public int getBatteryLevel(Long id) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Drone not found"));

        return drone.getBatteryCapacity();
    }

    @Scheduled(cron = "0 0/30 * * * ?") // Logs every 30 minutes
    public void logBatteryLevel() {
        List<Drone> drones = droneRepository.findAll();

        for (Drone drone : drones) {
            int batteryLevel = getBatteryLevel(drone.getId());

            logger.info("Battery level for drone " + drone.getId() + " : " + batteryLevel);
        }
    }
}
