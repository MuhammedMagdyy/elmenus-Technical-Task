package org.elmenus.drones.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elmenus.drones.model.entity.Medication;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDTO {
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
    private String name;
    @Positive
    private double weight;
    @Pattern(regexp = "^[A-Z0-9_]+$")
    private String code;
    private String image;

    public static MedicationDTO toDto(Medication medication) {
        return MedicationDTO.builder()
                .id(medication.getId())
                .name(medication.getName())
                .weight(medication.getWeight())
                .code(medication.getCode())
                .image(medication.getImage())
                .build();
    }

    public static List<MedicationDTO> toDtoList(List<Medication> medications) {
        List<MedicationDTO> medicationDTOS = new ArrayList<>();

        if (medications == null) {
            return medicationDTOS;
        }

        for (Medication medication : medications) {
            medicationDTOS.add(toDto(medication));
        }

        return medicationDTOS;
    }
}
