package org.elmenus.drones.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
}
