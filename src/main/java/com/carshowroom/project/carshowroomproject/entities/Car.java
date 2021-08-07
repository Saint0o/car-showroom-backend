package com.carshowroom.project.carshowroomproject.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private Integer price;

    @Column(name = "power")
    private Integer power;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
