package com.app.play11.controller;

import com.app.play11.model.dto.PlayerStats;
import com.app.play11.model.enums.Grounds;
import com.app.play11.model.enums.PlayingStyle;
import com.app.play11.model.enums.TeamNames;
import com.app.play11.service.PlayerStatsService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final PlayerStatsService playerStatsService;

    public HomeController(PlayerStatsService playerStatsService) {
        this.playerStatsService = playerStatsService;
    }

    @GetMapping({ "/" })
    public String showLoginPage() {
        return "Hello";
    }

    @GetMapping("/getStat")
    public List<PlayerStats> getStat(@RequestParam TeamNames team1, @RequestParam TeamNames team2,
            @RequestParam Grounds ground, @RequestParam PlayingStyle playingStyle) {
        return playerStatsService.compareStats(team1, team2, ground, playingStyle);
    }

}
