package com.mtu.codeiumproject.repository;

import com.mtu.codeiumproject.entity.Pet;
import com.mtu.codeiumproject.repository.dtos.PetStatistics;
import com.mtu.codeiumproject.repository.dtos.PetSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    // 7. Find Pets by Animal Type
    List<Pet> findByAnimalType(String animalType);

    // 8. Find Pets by Breed
    List<Pet> findByBreed(String breed);

    // 9. Get name and breed only
    @Query("SELECT new com.mtu.codeiumproject.repository.dtos.PetSummary(p.name, p.breed) FROM Pet p")
    List<PetSummary> getNameAndBreedOnly();

    // 10. Get Pet Statistics (Average Age, Oldest Age)
    @Query("SELECT new com.mtu.codeiumproject.repository.dtos.PetStatistics(AVG(p.age), MAX(p.age)) FROM Pet p")
    PetStatistics getPetStatistics();

    // Delete Pets by Name (ignoring case)
    @Modifying
    @Transactional
    @Query("DELETE FROM Pet p WHERE LOWER(p.name) = LOWER(:name)")
    void deleteByNameIgnoreCase(@Param("name") String name);
}