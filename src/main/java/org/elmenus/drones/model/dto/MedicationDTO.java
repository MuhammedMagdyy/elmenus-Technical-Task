package org.elmenus.drones.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
}
