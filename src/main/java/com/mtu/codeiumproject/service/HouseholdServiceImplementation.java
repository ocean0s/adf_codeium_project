package com.mtu.codeiumproject.service;

import com.mtu.codeiumproject.entity.Household;
import com.mtu.codeiumproject.repository.HouseholdRepository;
import com.mtu.codeiumproject.service.dtos.HouseholdStatistics;
import com.mtu.codeiumproject.service.exception.HouseholdException;
import com.mtu.codeiumproject.service.exception.HouseholdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdServiceImplementation implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    // Find Household by Eircode with pets eagerly loaded
    public Household findHouseholdByEircodeWithPets(String eircode) {
        return householdRepository.findByEircodeWithPets(eircode);
    }

    // Find list of households with no pets
    public List<Household> findHouseholdsNoPets() {
        return householdRepository.findHouseholdsNoPets();
    }

    // 1. Create Household
    public Household createHousehold(Household household) {
        if (household == null) {
            throw new HouseholdException("Household cannot be null");
        }
        return householdRepository.save(household);
    }

    // 2. Read All households
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    // 3. Read Household by ID - no pets details
    public Household getHouseholdById(String id) {
        return householdRepository.findById(id).orElseThrow(() -> new HouseholdNotFoundException("Household not found with id " + id));
    }

    // 4. Read Household by ID - with pets details
    public Household getHouseholdByIdWithPets(String id) {
        return householdRepository.findByIdWithPets(id).orElseThrow(() -> new HouseholdNotFoundException("Household not found with id " + id));
    }

    // 5. Update Household Details
    public Household updateHousehold(Household household) {
        if (household == null) {
            throw new HouseholdException("Household cannot be null");
        }
        Household existingHousehold = getHouseholdById(household.getEircode());
        existingHousehold.setNumberOfOccupants(household.getNumberOfOccupants());
        existingHousehold.setMaxNumberOfOccupants(household.getMaxNumberOfOccupants());
        return householdRepository.save(existingHousehold);
    }

    // 6. Delete Household by ID
    public void deleteHouseholdById(String id) {
        householdRepository.deleteById(id);
    }

    // 9. Find Households that are owner-occupied
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findOwnerOccupiedHouseholds();
    }

    // 10. Get Household Statistics
    public HouseholdStatistics getHouseholdStatistics() {
        HouseholdStatistics statistics = new HouseholdStatistics(
                householdRepository.countEmptyHouses(),
                householdRepository.countFullHouses()
        );
        return statistics;
    }
}