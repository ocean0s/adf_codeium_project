package com.mtu.codeiumproject.controller.rest;

import com.mtu.codeiumproject.controller.validation.NewHousehold;
import com.mtu.codeiumproject.entity.Household;
import com.mtu.codeiumproject.service.HouseholdService;
import com.mtu.codeiumproject.service.dtos.HouseholdStatistics;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myapi/households")
public class HouseholdController {

    private final HouseholdService householdService;

    @Autowired
    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @GetMapping
    public ResponseEntity<List<Household>> getAllHouseholds() {
        List<Household> households = householdService.getAllHouseholds();
        return ResponseEntity.ok(households);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Household> getHouseholdById(@PathVariable String id) {
        Household household = householdService.getHouseholdById(id);
        return ResponseEntity.ok(household);
    }

    @PostMapping
    public ResponseEntity<Household> createHousehold(@Valid @RequestBody NewHousehold household) {
        Household h = new Household(household.eircode(), household.numberOfOccupants(), household.maxNumberOfOccupants(), household.isOwnerOccupied(), household.pets());
        Household createdHousehold = householdService.createHousehold(h);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHousehold);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Household> updateHousehold(@PathVariable String id, @Valid @RequestBody NewHousehold household) {
        Household h = new Household(id, household.numberOfOccupants(), household.maxNumberOfOccupants(), household.isOwnerOccupied(), household.pets());
        Household updatedHousehold = householdService.updateHousehold(h);
        return ResponseEntity.ok(updatedHousehold);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouseholdById(@PathVariable String id) {
        householdService.deleteHouseholdById(id);
        return ResponseEntity.noContent().build();
    }

    // 4. Read Household by ID - with pets details
    @GetMapping("/{id}/with-pets")
    public ResponseEntity<Household> getHouseholdByIdWithPets(@PathVariable String id) {
        Household household = householdService.getHouseholdByIdWithPets(id);
        return ResponseEntity.ok(household);
    }

    // 9. Find Households that are owner-occupied
    @GetMapping("/owner-occupied")
    public ResponseEntity<List<Household>> findOwnerOccupiedHouseholds() {
        List<Household> households = householdService.findOwnerOccupiedHouseholds();
        return ResponseEntity.ok(households);
    }

    // 10. Get Household Statistics
    @GetMapping("/statistics")
    public ResponseEntity<HouseholdStatistics> getHouseholdStatistics() {
        HouseholdStatistics statistics = householdService.getHouseholdStatistics();
        return ResponseEntity.ok(statistics);
    }

    // Find list of households with no pets
    @GetMapping("/no-pets")
    public ResponseEntity<List<Household>> findHouseholdsNoPets() {
        List<Household> households = householdService.findHouseholdsNoPets();
        return ResponseEntity.ok(households);
    }
}