package com.space.management.controllers;

import com.space.management.model.Mission;
import com.space.management.services.ManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/missions")
public class MissionController {

    private final ManagementService managementService;

    @Autowired
    public MissionController(final ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping
    public Mission addMission(@RequestBody final Mission mission) {
        log.info("Adding mission: " + mission);
        return managementService.addMission(mission);
    }

    @PutMapping
    public Mission editMission(@RequestBody final Mission mission) {
        log.info("Updating mission: " + mission);
        return managementService.addMission(mission);
    }

    @DeleteMapping(path = "/{missionId}")
    public void removeProduct(@PathVariable(name = "missionId") final Long missionId) {
        log.warn("Deleting mission: " + missionId);
        managementService.removeMission(missionId);
    }

    @GetMapping
    public List<Mission> getMissions() {
        log.info("Finding missions");
        return managementService.retrieveMissions();
    }

}
