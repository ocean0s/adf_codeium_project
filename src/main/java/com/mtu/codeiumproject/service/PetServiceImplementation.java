package com.mtu.codeiumproject.service;

import com.mtu.codeiumproject.entity.Pet;
import com.mtu.codeiumproject.repository.PetRepository;
import com.mtu.codeiumproject.repository.dtos.PetStatistics;
import com.mtu.codeiumproject.repository.dtos.PetSummary;
import com.mtu.codeiumproject.service.exception.PetException;
import com.mtu.codeiumproject.service.exception.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImplementation implements PetService {

    @Autowired
    private PetRepository petRepository;

    // 1. Create Pet
    public Pet createPet(Pet pet) {
        if (pet == null) {
            throw new PetException("Pet cannot be null");
        }
        return petRepository.save(pet);
    }

    // 2. Read All Pets
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // 3. Read Pet by ID
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new PetNotFoundException("Pet not found with id " + id));
    }

    // 4. Update Pet Details
    public Pet updatePet(Pet pet) {
        if (pet == null) {
            throw new PetException("Pet cannot be null");
        }
        Pet existingPet = getPetById(pet.getId());
        existingPet.setName(pet.getName());
        existingPet.setAge(pet.getAge());
        existingPet.setAnimalType(pet.getAnimalType());
        existingPet.setBreed(pet.getBreed());
        // Household update not supported for simplicity
        return petRepository.save(existingPet);
    }

    // 5. Delete Pet by ID
    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }

    // 6. Delete Pets by Name
    public void deletePetsByName(String name) {
        petRepository.deleteByNameIgnoreCase(name);
    }

    // 7. Find Pets by Animal Type
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalType(animalType);
    }

    // 8. Find Pets by Breed
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findByBreed(breed);
    }

    // 9. Get name and breed only
    public List<PetSummary> getNameAndBreedOnly() {
        return petRepository.getNameAndBreedOnly();
    }

    // 10. Get Pet Statistics (Average Age, Oldest Age)
    public PetStatistics getPetStatistics() {
        return petRepository.getPetStatistics();
    }
}
