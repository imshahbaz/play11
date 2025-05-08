package com.app.play11.service;

import java.util.List;
import java.util.Set;

import com.app.play11.service.impl.TeamCreationServiceImpl.TeamRecord;

public interface TeamCreationService {
    List<TeamRecord> createTeams(Set<String> team);

    List<String> shufflePlayers(String players, int teamCount, int playerCount);
}
