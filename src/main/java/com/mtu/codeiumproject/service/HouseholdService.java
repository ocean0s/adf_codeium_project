package com.mtu.codeiumproject.service;

import com.mtu.codeiumproject.entity.Household;
import com.mtu.codeiumproject.service.dtos.HouseholdStatistics;

import java.util.List;

public interface HouseholdService {
    // 1. Create Household
    Household createHousehold(Household household);

    // 2. Read All households
    List<Household> getAllHouseholds();

    // 3. Read Household by ID - no pets details
    Household getHouseholdById(String id);

    // 4. Read Household by ID - with pets details
    Household getHouseholdByIdWithPets(String id);

    // 5. Update Household Details
    Household updateHousehold(Household household);

    // 6. Delete Household by ID
    void deleteHouseholdById(String id);

    // 8. Find Households with no pets
    List<Household> findHouseholdsNoPets();

    // 9. Find Households that are owner-occupied
    List<Household> findOwnerOccupiedHouseholds();

    // 10. Get Household Statistics
    HouseholdStatistics getHouseholdStatistics();

    // 5. Find Household by Eircode with pets
    Household findHouseholdByEircodeWithPets(String eircode);
}


