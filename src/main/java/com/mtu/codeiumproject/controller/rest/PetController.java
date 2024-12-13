package com.mtu.codeiumproject.controller.rest;

import com.mtu.codeiumproject.controller.validation.NewPet;
import com.mtu.codeiumproject.entity.Pet;
import com.mtu.codeiumproject.repository.dtos.PetStatistics;
import com.mtu.codeiumproject.repository.dtos.PetSummary;
import com.mtu.codeiumproject.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myapi/pets")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        Pet pet = petService.getPetById(id);
        return ResponseEntity.ok(pet);
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@Valid @RequestBody NewPet pet) {
        Pet p = new Pet(pet.name(), pet.animalType(), pet.age(), pet.breed());
        Pet createdPet = petService.createPet(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @Valid @RequestBody NewPet pet) {
        Pet p = new Pet(id, pet.name(), pet.animalType(), pet.age(), pet.breed());
        Pet updatedPet = petService.updatePet(p);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetById(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-name/{name}")
    public ResponseEntity<Void> deletePetsByName(@PathVariable String name) {
        petService.deletePetsByName(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-animal-type/{animalType}")
    public ResponseEntity<List<Pet>> findPetsByAnimalType(@PathVariable String animalType) {
        List<Pet> pets = petService.findPetsByAnimalType(animalType);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/by-breed/{breed}")
    public ResponseEntity<List<Pet>> findPetsByBreed(@PathVariable String breed) {
        List<Pet> pets = petService.findPetsByBreed(breed);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/name-and-breed-only")
    public ResponseEntity<List<PetSummary>> getNameAndBreedOnly() {
        List<PetSummary> petSummaries = petService.getNameAndBreedOnly();
        return ResponseEntity.ok(petSummaries);
    }

    @GetMapping("/pet-statistics")
    public ResponseEntity<PetStatistics> getPetStatistics() {
        PetStatistics petStatistics = petService.getPetStatistics();
        return ResponseEntity.ok(petStatistics);
    }
}