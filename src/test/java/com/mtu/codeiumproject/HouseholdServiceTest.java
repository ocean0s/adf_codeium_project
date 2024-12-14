package com.mtu.codeiumproject;

import com.mtu.codeiumproject.entity.Household;
import com.mtu.codeiumproject.entity.Pet;
import com.mtu.codeiumproject.repository.HouseholdRepository;
import com.mtu.codeiumproject.service.HouseholdService;
import com.mtu.codeiumproject.service.PetService;
import com.mtu.codeiumproject.service.dtos.HouseholdStatistics;
import com.mtu.codeiumproject.service.exception.HouseholdNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HouseholdServiceTest {

    @Autowired
    private HouseholdService householdService;

    @Autowired
    private HouseholdRepository householdRepository;
    
    @Autowired
    private PetService petService;

    @Test
    public void testCreateHousehold() {
        Household household = new Household("Eircode2", 2, 4, true, new ArrayList<Pet>());
        Household createdHousehold = householdService.createHousehold(household);
        assertNotNull(createdHousehold);
        assertEquals(household.getEircode(), createdHousehold.getEircode());
        assertEquals(household.getNumberOfOccupants(), createdHousehold.getNumberOfOccupants());
        assertEquals(household.getMaxNumberOfOccupants(), createdHousehold.getMaxNumberOfOccupants());
        assertEquals(household.getIsOwnerOccupied(), createdHousehold.getIsOwnerOccupied());
    }

    @Test
    public void testGetAllHouseholds() {
        Household household1 = new Household("Eircode10", 2, 4, true);
        Household household2 = new Household("Eircode20", 3, 5, false);
        householdService.createHousehold(household1);
        householdService.createHousehold(household2);
        List<Household> households = householdService.getAllHouseholds();
        assertEquals(8, households.size());
    }

    @Test
    public void testGetHouseholdById() {
        Household household = new Household("Eircode3", 2, 4, true);
        Household createdHousehold = householdService.createHousehold(household);
        Household retrievedHousehold = householdService.getHouseholdById(createdHousehold.getEircode());
        assertNotNull(retrievedHousehold);
        assertEquals(household.getEircode(), retrievedHousehold.getEircode());
        assertEquals(household.getNumberOfOccupants(), retrievedHousehold.getNumberOfOccupants());
        assertEquals(household.getMaxNumberOfOccupants(), retrievedHousehold.getMaxNumberOfOccupants());
        assertEquals(household.getIsOwnerOccupied(), retrievedHousehold.getIsOwnerOccupied());
    }

    @Test // Does not work due to AI implementation
    public void testGetHouseholdByIdWithPets() {
        // Assuming pets are added to the household
        Household household = new Household("Eircode4", 2, 4, true);
        Household createdHousehold = householdService.createHousehold(household);
        Household retrievedHousehold = householdService.getHouseholdByIdWithPets(createdHousehold.getEircode());
        petService.createPet(
                new Pet(123L, "Fido", "Dog", "Unknown", 3, retrievedHousehold)
        );
        assertNotNull(retrievedHousehold);
        assertEquals(household.getEircode(), retrievedHousehold.getEircode());
        assertEquals(household.getNumberOfOccupants(), retrievedHousehold.getNumberOfOccupants());
        assertEquals(household.getMaxNumberOfOccupants(), retrievedHousehold.getMaxNumberOfOccupants());
        assertEquals(household.getIsOwnerOccupied(), retrievedHousehold.getIsOwnerOccupied());
        assertEquals(1, retrievedHousehold.getPets().size());
    }

    @Test
    public void testUpdateHousehold() {
        Household household = new Household("Eircode5", 2, 4, true);
        Household createdHousehold = householdService.createHousehold(household);
        createdHousehold.setNumberOfOccupants(3);
        Household updatedHousehold = householdService.updateHousehold(createdHousehold);
        assertNotNull(updatedHousehold);
        assertEquals(3, updatedHousehold.getNumberOfOccupants());
    }

    @Test
    public void testDeleteHouseholdById() {
        Household household = new Household("Eircode6", 2, 4, true);
        Household createdHousehold = householdService.createHousehold(household);
        householdService.deleteHouseholdById(createdHousehold.getEircode());
        assertThrows(HouseholdNotFoundException.class, () -> householdService.getHouseholdById(createdHousehold.getEircode()));
    }

    @Test
    public void testFindHouseholdsNoPets() {
        Household household1 = new Household("Eircode11", 2, 4, true);
        Household household2 = new Household("Eircode21", 3, 5, false);
        householdService.createHousehold(household1);
        Household h2 = householdService.createHousehold(household2);
        petService.createPet(
                new Pet(123L, "Fido", "Dog", "Unknown", 3, h2)
        );
        List<Household> households = householdService.findHouseholdsNoPets();
        assertEquals(9, households.size());
    }

    @Test
    public void testFindOwnerOccupiedHouseholds() {
        Household household1 = new Household("Eircode12", 2, 4, true);
        Household household2 = new Household("Eircode22", 3, 5, false);
        householdService.createHousehold(household1);
        householdService.createHousehold(household2);
        List<Household> households = householdService.findOwnerOccupiedHouseholds();
        assertEquals(3, households.size());
    }

    @Test
    public void testGetHouseholdStatistics() {
        Household household1 = new Household("Eircode13", 2, 4, true);
        Household household2 = new Household("Eircode23", 3, 5, false);
        householdService.createHousehold(household1);
        householdService.createHousehold(household2);
        HouseholdStatistics statistics = householdService.getHouseholdStatistics();
        assertNotNull(statistics);
    }

    @Test // Does not work due to AI implementation
    public void testFindHouseholdByEircodeWithPets() {
        Household household = new Household("Eircode7", 2, 4, true);
        householdService.createHousehold(household);
        Household retrievedHousehold = householdService.findHouseholdByEircodeWithPets(household.getEircode());
        petService.createPet(
                new Pet(123L, "Fido", "Dog", "Unknown", 3, retrievedHousehold)
        );
        assertNotNull(retrievedHousehold);
        assertEquals(household.getEircode(), retrievedHousehold.getEircode());
        assertEquals(household.getNumberOfOccupants(), retrievedHousehold.getNumberOfOccupants());
        assertEquals(household.getMaxNumberOfOccupants(), retrievedHousehold.getMaxNumberOfOccupants());
        assertEquals(household.getIsOwnerOccupied(), retrievedHousehold.getIsOwnerOccupied());
        assertEquals(1, retrievedHousehold.getPets().size());
    }
}