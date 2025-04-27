package com.app.play11.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.play11.model.dto.PlayerStats;
import com.app.play11.model.enums.Grounds;
import com.app.play11.model.enums.PlayingStyle;
import com.app.play11.model.enums.TeamNames;
import com.app.play11.model.teams.Player;
import com.app.play11.model.teams.Teams;
import com.app.play11.service.PlayerStatsService;
import com.app.play11.util.PlayerStatsUtil;

@Service
public class PlayerStatsServiceImpl implements PlayerStatsService {

    @Override
    public List<PlayerStats> compareStats(TeamNames team1, TeamNames team2, Grounds ground, PlayingStyle playingStyle) {

        List<Player> players = new ArrayList<>();
        players.addAll(Teams.AllTeams.get(team1));
        players.addAll(Teams.AllTeams.get(team2));

        List<PlayerStats> stats = players.parallelStream().map(player -> {
            return PlayerStatsUtil.getPlayerStatByGround(player,
                    playingStyle.getStyle(),
                    ground.getName());

        }).collect(Collectors.toList());

        return stats;

    }

}
