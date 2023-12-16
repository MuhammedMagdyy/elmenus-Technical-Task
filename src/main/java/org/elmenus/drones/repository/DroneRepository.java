package org.elmenus.drones.repository;

import org.elmenus.drones.model.entity.Drone;
import org.elmenus.drones.model.entity.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findByState(DroneState state);
}
