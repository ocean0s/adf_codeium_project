package com.mtu.codeiumproject;

import com.mtu.codeiumproject.entity.Pet;
import com.mtu.codeiumproject.repository.PetRepository;
import com.mtu.codeiumproject.repository.dtos.PetStatistics;
import com.mtu.codeiumproject.repository.dtos.PetSummary;
import com.mtu.codeiumproject.service.PetService;
import com.mtu.codeiumproject.service.exception.PetNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PetServiceTest {

    @Autowired
    private PetService petService;

    @Autowired
    private PetRepository petRepository;

    @BeforeEach
    public void setup() {
        petRepository.deleteAll();
    }

    @Test
    public void testCreatePet() {
        Pet pet = new Pet("Fido", "Dog", 3, "Unknown");
        Pet createdPet = petService.createPet(pet);
        assertNotNull(createdPet);
        assertEquals(pet.getName(), createdPet.getName());
        assertEquals(pet.getAnimalType(), createdPet.getAnimalType());
        assertEquals(pet.getAge(), createdPet.getAge());
    }

    @Test
    public void testGetAllPets() {
        Pet pet1 = new Pet("Fido", "Dog", 3, "Unknown");
        Pet pet2 = new Pet("Whiskers", "Cat", 2, "Unknown");
        petService.createPet(pet1);
        petService.createPet(pet2);
        List<Pet> pets = petService.getAllPets();
        assertEquals(2, pets.size());
    }

    @Test
    public void testGetPetById() {
        Pet pet = new Pet("Fido", "Dog", 3, "Unknown");
        Pet createdPet = petService.createPet(pet);
        Pet retrievedPet = petService.getPetById(createdPet.getId());
        assertNotNull(retrievedPet);
        assertEquals(pet.getName(), retrievedPet.getName());
        assertEquals(pet.getAnimalType(), retrievedPet.getAnimalType());
        assertEquals(pet.getAge(), retrievedPet.getAge());
    }

    @Test
    public void testUpdatePet() {
        Pet pet = new Pet("Fido", "Dog", 3, "Unknown");
        Pet createdPet = petService.createPet(pet);
        createdPet.setAge(4);
        createdPet.setBreed("Test");
        Pet updatedPet = petService.updatePet(createdPet);
        assertNotNull(updatedPet);
        assertEquals(4, updatedPet.getAge());
        assertEquals("Test", updatedPet.getBreed());
    }

    @Test
    public void testDeletePetById() {
        Pet pet = new Pet("Fido", "Dog", 3, "Unknown");
        Pet createdPet = petService.createPet(pet);
        petService.deletePetById(createdPet.getId());
        assertThrows(PetNotFoundException.class, () -> petService.getPetById(createdPet.getId()));
    }

    @Test
    public void testDeletePetsByName() {
        Pet pet1 = new Pet("Fido", "Dog", 3, "Unknown");
        Pet pet2 = new Pet("Fido", "Cat", 2, "Unknown");
        petService.createPet(pet1);
        petService.createPet(pet2);
        petService.deletePetsByName("Fido");
        List<Pet> pets = petService.getAllPets();
        assertEquals(0, pets.size());
    }

    @Test
    public void testFindPetsByAnimalType() {
        Pet pet1 = new Pet("Fido", "Dog", 3, "Unknown");
        Pet pet2 = new Pet("Whiskers", "Cat", 2, "Unknown");
        petService.createPet(pet1);
        petService.createPet(pet2);
        List<Pet> pets = petService.findPetsByAnimalType("Dog");
        assertEquals(1, pets.size());
    }

    @Test
    public void testFindPetsByBreed() {
        Pet pet1 = new Pet("Fido", "Golden Retriever Pro", 3, "Unknown");
        Pet pet2 = new Pet("Whiskers", "Siamese", 2, "Unknown");
        petService.createPet(pet1);
        petService.createPet(pet2);
        List<Pet> pets = petService.findPetsByAnimalType("Golden Retriever Pro");
        assertEquals(1, pets.size());
    }

    @Test
    public void testGetNameAndBreedOnly() {
        Pet pet1 = new Pet("Fido", "Golden Retriever", 3, "Unknown");
        Pet pet2 = new Pet("Whiskers", "Siamese", 2, "Unknown");
        petService.createPet(pet1);
        petService.createPet(pet2);
        List<PetSummary> pets = petService.getNameAndBreedOnly();
        assertEquals(2, pets.size());
    }

    @Test
    public void testGetPetStatistics() {
        Pet pet1 = new Pet("Fido", "Dog", 3, "Unknown");
        Pet pet2 = new Pet("Whiskers", "Cat", 2, "Unknown");
        petService.createPet(pet1);
        petService.createPet(pet2);
        PetStatistics statistics = petService.getPetStatistics();
        assertNotNull(statistics);
    }
}
