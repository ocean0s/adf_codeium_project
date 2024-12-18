package com.mtu.codeiumproject.repository;

import com.mtu.codeiumproject.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, String> {
    // Find Household by Eircode with pets eagerly loaded
    @Query("SELECT h FROM Household h JOIN FETCH h.pets WHERE h.eircode = :eircode")
    Household findByEircodeWithPets(String eircode);

    // Find list of households with no pets
    @Query("SELECT h FROM Household h WHERE h.pets IS EMPTY")
    List<Household> findHouseholdsNoPets();

    // Read Household by ID with pets eagerly loaded
    @Query("SELECT h FROM Household h JOIN FETCH h.pets WHERE h.eircode = :id")
    Optional<Household> findByIdWithPets(@Param("id") String id);

    // Find Households that are owner-occupied
    @Query("SELECT h FROM Household h WHERE h.isOwnerOccupied = true")
    List<Household> findOwnerOccupiedHouseholds();

    // Count empty houses
    @Query("SELECT COUNT(h) FROM Household h WHERE h.numberOfOccupants = 0")
    long countEmptyHouses();

    // Count full houses
    @Query("SELECT COUNT(h) FROM Household h WHERE h.numberOfOccupants = h.maxNumberOfOccupants")
    long countFullHouses();
}