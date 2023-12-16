package org.elmenus.drones.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "drone")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Drone {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "serial_number")
    private String serialNumber;
    private DroneModel model;
    @Column(name = "weight_limit")
    private double weightLimit;
    @Column(name = "battery_capacity")
    private int batteryCapacity;
    private DroneState state;
    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Medication> medications = new ArrayList<>();

    public Drone(String serialNumber,
                 DroneModel model,
                 double weightLimit,
                 int batteryCapacity,
                 DroneState state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }
}
