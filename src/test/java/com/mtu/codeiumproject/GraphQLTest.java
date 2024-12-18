package com.mtu.codeiumproject;

import com.mtu.codeiumproject.entity.Pet;
import com.mtu.codeiumproject.repository.dtos.PetStatistics;
import com.mtu.codeiumproject.repository.dtos.PetSummary;
import com.mtu.codeiumproject.service.PetService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GraphQLTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    private PetService petService;

    @Order(100)
    @Test
    void testGetAllPets() {
        // Given
        String query = "query { getAllPets { id name animalType age breed } }";

        // When
        graphQlTester.document(query)
                .execute()
                .path("getAllPets")
                .entityList(Pet.class)
                .hasSize(petService.getAllPets().size());
    }

    @Order(0)
    @Test
    void testGetPetById() {
        // Given
        String query = "query { getPetById(id: 1) { id name animalType age breed } }";

        // When
        graphQlTester.document(query)
                .execute()
                .path("getPetById")
                .entity(Pet.class)
                .isEqualTo(new Pet(1L, "Fido", "Dog", 3, "Labrador"));
    }
    @Order(100)
    @Test
    void testFindPetsByAnimalType() {
        // Given
        String query = "query { findPetsByAnimalType(animalType: \"Dog\") { id name animalType age breed } }";

        // When
        graphQlTester.document(query)
                .execute()
                .path("findPetsByAnimalType")
                .entityList(Pet.class)
                .hasSize(petService.findPetsByAnimalType("Dog").size());
    }
    @Order(100)
    @Test
    void testFindPetsByBreed() {
        // Given
        String query = "query { findPetsByBreed(breed: \"Siamese\") { id name animalType age breed } }";

        // When
        graphQlTester.document(query)
                .execute()
                .path("findPetsByBreed")
                .entityList(Pet.class)
                .hasSize(petService.findPetsByBreed("Siamese").size())
                .containsExactly(petService.findPetsByBreed("Siamese").toArray(new Pet[0]));
    }
    @Order(100)
    @Test
    void testGetNameAndBreedOnly() {
        // Given
        String query = "query { getNameAndBreedOnly { name breed } }";

        // When
        graphQlTester.document(query)
                .execute()
                .path("getNameAndBreedOnly")
                .entityList(PetSummary.class)
                .hasSize(2)
                .containsExactly(
                        petService.getNameAndBreedOnly().toArray(new PetSummary[0])
                );
    }
    @Order(100)
    @Test
    void testGetPetStatistics() {
        // Given
        String query = "query { getPetStatistics { averageAge maxAge } }";

        // When
        graphQlTester.document(query)
                .execute()
                .path("getPetStatistics")
                .entity(PetStatistics.class)
                .isEqualTo(petService.getPetStatistics());
    }
    @Order(100)
    @Test
    void testCreatePet() {
        // Given
        String mutation = "mutation { createPet(input: { name: \"Buddy\", animalType: \"Dog\", age: 1, breed: \"Labrador\" }) { id name animalType age breed } }";

        // When
        graphQlTester.document(mutation)
                .execute()
                .path("createPet")
                .entity(Pet.class)
                .isEqualTo(new Pet(3L, "Buddy", "Dog", 1, "Labrador"));
    }
    @Order(100)
    @Test
    void testUpdatePet() {
        // Given
        String mutation = "mutation { updatePet(id: 1, input: { name: \"Fido Updated\", animalType: \"Dog\", age: 4, breed: \"Golden Retriever\" }) { id name animalType age breed } }";

        // When
        graphQlTester.document(mutation)
                .execute()
                .path("updatePet")
                .entity(Pet.class)
                .isEqualTo(new Pet(1L, "Fido Updated", "Dog", 4, "Golden Retriever"));
    }
    @Order(100)
    @Test
    void testDeletePet() {
        // Given
        String mutation = "mutation { deletePetById(id: 1) }";

        // When
        graphQlTester.document(mutation)
                .execute()
                .path("deletePetById")
                .entity(Boolean.class)
                .isEqualTo(true);
    }
    @Order(100)
    @Test
    void testDeletePetsByName() {
        // Given
        String mutation = "mutation { deletePetsByName(name: \"Fido\") }";

        // When
        graphQlTester.document(mutation)
                .execute()
                .path("deletePetsByName")
                .entity(Boolean.class)
                .isEqualTo(true);
    }
}
