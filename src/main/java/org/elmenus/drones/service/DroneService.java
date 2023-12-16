package org.elmenus.drones.service;

import jakarta.validation.Valid;
import org.elmenus.drones.helpers.NotFoundException;
import org.elmenus.drones.model.dto.DroneDTO;
import org.elmenus.drones.model.entity.Drone;
import org.elmenus.drones.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneService {
    private final DroneRepository droneRepository;

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
}
