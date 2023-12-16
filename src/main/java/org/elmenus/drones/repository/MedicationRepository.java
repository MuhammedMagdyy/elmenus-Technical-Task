package org.elmenus.drones.repository;

import org.elmenus.drones.model.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findByDroneId(Long droneId);
}
