package com.app.play11.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.app.play11.service.TeamCreationService;

@Service
public class TeamCreationServiceImpl implements TeamCreationService {

    @Override
    public List<TeamRecord> createTeams(Set<String> team) {
        Map<String, Set<String>> teams = new ConcurrentHashMap<>(11);
        List<TeamRecord> response = new ArrayList<>(110);

        for (String player : team) {
            String captain = player.trim();
            Set<String> viceCaptain = teams.getOrDefault(captain, new HashSet<>());
            for (String other : team) {
                String vc = other.trim();
                if (captain.equalsIgnoreCase(vc)) {
                    continue;
                }
                viceCaptain.add(vc);
            }
            teams.put(captain, viceCaptain);
        }

        for (Entry<String, Set<String>> e : teams.entrySet()) {
            String captain = e.getKey();
            for (String vc : e.getValue()) {
                System.out.println("Captain : " + captain + "   Vice Captain : " + vc);
                response.add(new TeamRecord(captain, vc));
            }
        }

        return response;
    }

    public static record TeamRecord(String Captain, String ViceCaptain) {

    }

    @Override
    public List<String> shufflePlayers(String players, int teamCount, int playerCount) {
        List<String> names = Arrays.asList(players.split(","));
        // names = names.stream().map(name -> name.trim()).toList();
        Map<String, String> map = new HashMap<>(teamCount);
        for (int i = 0; i < teamCount;) {
            Collections.shuffle(names);
            List<String> subList = names.subList(0, playerCount);
            String key = "";
            String team = "";
            for (int j = 0; j < subList.size(); j++) {
                key += subList.get(j);
                team += subList.get(j) + " ,";
            }
            if (map.containsKey(key)) {
                continue;
            }

            map.put(key, team);
            i++;
        }

        return map.values().stream().toList();
    }

}
