package org.elmenus.drones.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elmenus.drones.model.entity.Drone;
import org.elmenus.drones.model.entity.DroneModel;
import org.elmenus.drones.model.entity.DroneState;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneDTO {
    private Long id;
    @Size(min = 1, max = 100)
    private String serialNumber;
    @NotNull
    private DroneModel model;
    @Min(value = 0)
    @Max(value = 500)
    private double weightLimit;
    @Min(value = 1)
    @Max(value = 100)
    private int batteryCapacity;
    @NotNull
    private DroneState state;
    private List<MedicationDTO> medications = new ArrayList<>();

    public static DroneDTO toDto(Drone drone) {
        return DroneDTO.builder()
                .id(drone.getId())
                .serialNumber(drone.getSerialNumber())
                .model(drone.getModel())
                .weightLimit(drone.getWeightLimit())
                .batteryCapacity(drone.getBatteryCapacity())
                .state(drone.getState())
                .medications(MedicationDTO.toDtoList(drone.getMedications()))
                .build();
    }
}