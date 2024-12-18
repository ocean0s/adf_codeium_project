package com.mtu.codeiumproject.service;

import com.mtu.codeiumproject.entity.Pet;
import com.mtu.codeiumproject.repository.dtos.PetStatistics;
import com.mtu.codeiumproject.repository.dtos.PetSummary;

import java.util.List;

public interface PetService {

    // 1. Create Pet
    Pet createPet(Pet pet);

    // 2. Read All Pets
    List<Pet> getAllPets();

    // 3. Read Pet by ID
    Pet getPetById(Long id);

    // 4. Update Pet Details
    Pet updatePet(Pet pet);

    // 5. Delete Pet by ID
    void deletePetById(Long id);

    // 6. Delete Pets by Name
    void deletePetsByName(String name);

    // 7. Find Pets by Animal Type
    List<Pet> findPetsByAnimalType(String animalType);

    // 8. Find Pets by Breed
    List<Pet> findPetsByBreed(String breed);

    // 9. Get Name and Breed Only
    List<PetSummary> getNameAndBreedOnly();

    // 10. Get Pet Statistics
    PetStatistics getPetStatistics();
}
