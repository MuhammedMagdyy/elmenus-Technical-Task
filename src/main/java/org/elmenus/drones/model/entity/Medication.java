package org.elmenus.drones.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.elmenus.drones.model.dto.MedicationDTO;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "medication")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Medication {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private double weight;
    private String code;
    private String image;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drone_id", nullable = true)
    private Drone drone;

    public Medication(String name,
                      double weight,
                      String code,
                      String image,
                      Drone drone) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
        this.drone = drone;
    }

    public static Medication toEntity(MedicationDTO medicationDTO) {
        return Medication.builder()
                .id(medicationDTO.getId())
                .name(medicationDTO.getName())
                .weight(medicationDTO.getWeight())
                .code(medicationDTO.getCode())
                .image(medicationDTO.getImage())
                .build();
    }

    public static List<Medication> toListEntity(List<MedicationDTO> medicationDTO) {
        List<Medication> medications = new ArrayList<>();
        for (MedicationDTO dto : medicationDTO) {
            medications.add(Medication.toEntity(dto));
        }

        return medications;
    }
}
