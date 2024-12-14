package com.mtu.codeiumproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "households")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Household {
    @Id
    private String eircode;
    private Integer numberOfOccupants;
    private Integer maxNumberOfOccupants;
    private Boolean isOwnerOccupied;
    @OneToMany(mappedBy = "household")
    private List<Pet> pets;

    public Household(String eircode, Integer numberOfOccupants, Integer maxNumberOfOccupants, Boolean isOwnerOccupied) {
        this.eircode = eircode;
        this.numberOfOccupants = numberOfOccupants;
        this.maxNumberOfOccupants = maxNumberOfOccupants;
        this.isOwnerOccupied = isOwnerOccupied;
    }
}
