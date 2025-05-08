package com.app.play11.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.play11.service.TeamCreationService;
import com.app.play11.service.impl.TeamCreationServiceImpl.TeamRecord;

@RestController
public class TeamController {

    private final TeamCreationService teamCreationService;

    public TeamController(TeamCreationService teamCreationService) {
        this.teamCreationService = teamCreationService;
    }

    @PostMapping("/getTeamCombination")
    public List<TeamRecord> getTeamCombination(@RequestBody String team) {
        String[] arr = team.split(",");
        return teamCreationService.createTeams(Arrays.asList(arr).parallelStream().collect(Collectors.toSet()));
    }

    @PostMapping("/shuffle")
    public List<String> postMethodName(@RequestBody String team, @RequestParam int teamCount,
            @RequestParam int playerCount) {
        return teamCreationService.shufflePlayers(team, teamCount, playerCount);
    }

}
