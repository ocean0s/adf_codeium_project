package com.mtu.codeiumproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String animalType;
    private String breed;
    private Integer age;
    @ManyToOne
    @JoinColumn(name = "eircode", referencedColumnName = "eircode")
    private Household household;

    public Pet(String name, String animalType, int age, String breed) {
        this.name = name;
        this.animalType = animalType;
        this.age = age;
        this.breed = breed;
    }

    public Pet(long id, String name, String animalType, int age, String breed) {
        this.name = name;
        this.animalType = animalType;
        this.age = age;
        this.breed = breed;
        this.id = id;
    }
}