package com.mtu.codeiumproject.controller.graphql;

import com.mtu.codeiumproject.controller.validation.NewPet;
import com.mtu.codeiumproject.entity.Pet;
import com.mtu.codeiumproject.repository.dtos.PetStatistics;
import com.mtu.codeiumproject.repository.dtos.PetSummary;
import com.mtu.codeiumproject.service.PetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class PetGraphQLController {

    private final PetService petService;

    @QueryMapping
    List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @QueryMapping
    Pet getPetById(@Argument Integer id) {
        return petService.getPetById((long) id);
    }

    @QueryMapping
    List<Pet> findPetsByAnimalType(@Argument String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    @QueryMapping
    List<Pet> findPetsByBreed(@Argument String breed) {
        return petService.findPetsByBreed(breed);
    }

    @QueryMapping
    List<PetSummary> getNameAndBreedOnly() {
        return petService.getNameAndBreedOnly();
    }

    @QueryMapping
    PetStatistics getPetStatistics() {
        return petService.getPetStatistics();
    }

    @MutationMapping
    Pet createPet(@Argument @Valid NewPet input) {
        Pet pet = new Pet(input.name(), input.animalType(), input.age(), input.breed());
        return petService.createPet(pet);
    }

    @MutationMapping
    Pet updatePet(@Argument Integer id, @Argument @Valid NewPet input) {
        Pet pet = new Pet(id, input.name(), input.animalType(), input.age(), input.breed());
        return petService.updatePet(pet);
    }

    @MutationMapping
    Boolean deletePetById(@Argument Integer id) {
        petService.deletePetById((long) id);
        return true;
    }

    @MutationMapping
    Boolean deletePetsByName(@Argument String name) {
        petService.deletePetsByName(name);
        return true;
    }
}