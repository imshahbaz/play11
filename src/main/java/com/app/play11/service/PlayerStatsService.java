package com.app.play11.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.play11.model.dto.PlayerStats;
import com.app.play11.model.enums.Grounds;
import com.app.play11.model.enums.PlayingStyle;
import com.app.play11.model.enums.TeamNames;

@Service
public interface PlayerStatsService {

    List<PlayerStats> compareStats(TeamNames team1, TeamNames team2, Grounds ground, PlayingStyle playingStyle);

}
