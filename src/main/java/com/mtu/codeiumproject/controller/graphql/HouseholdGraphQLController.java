package com.mtu.codeiumproject.controller.graphql;

import com.mtu.codeiumproject.controller.validation.NewHousehold;
import com.mtu.codeiumproject.entity.Household;
import com.mtu.codeiumproject.service.HouseholdService;
import com.mtu.codeiumproject.service.dtos.HouseholdStatistics;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class HouseholdGraphQLController {

    private final HouseholdService householdService;

    @QueryMapping
    List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @QueryMapping
    Household getHouseholdById(@Argument String id) {
        return householdService.getHouseholdById(id);
    }

    @QueryMapping
    Household getHouseholdByIdWithPets(@Argument String id) {
        return householdService.getHouseholdByIdWithPets(id);
    }

    @QueryMapping
    List<Household> findHouseholdsNoPets() {
        return householdService.findHouseholdsNoPets();
    }

    @QueryMapping
    List<Household> findOwnerOccupiedHouseholds() {
        return householdService.findOwnerOccupiedHouseholds();
    }

    @QueryMapping
    HouseholdStatistics getHouseholdStatistics() {
        return householdService.getHouseholdStatistics();
    }

    @QueryMapping
    Household findHouseholdByEircodeWithPets(@Argument String eircode) {
        return householdService.findHouseholdByEircodeWithPets(eircode);
    }

    @MutationMapping
    Household createHousehold(@Argument("household") @Valid NewHousehold input) {
        Household household = new Household(input.eircode(), input.numberOfOccupants(), input.maxNumberOfOccupants(), input.isOwnerOccupied(), input.pets());
        return householdService.createHousehold(household);
    }

    @MutationMapping
    Household updateHousehold(@Argument("id") String id, @Argument("household") @Valid NewHousehold input) {
        Household household = new Household(id, input.numberOfOccupants(), input.maxNumberOfOccupants(), input.isOwnerOccupied(), input.pets());
        return householdService.updateHousehold(household);
    }

    @MutationMapping
    Boolean deleteHouseholdById(@Argument String id) {
        householdService.deleteHouseholdById(id);
        return true;
    }
}