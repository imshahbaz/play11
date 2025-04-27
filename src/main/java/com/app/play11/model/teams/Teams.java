package com.app.play11.model.teams;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.app.play11.model.enums.TeamNames;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.annotation.PostConstruct;

@Component
public class Teams {

    private final Type type = new TypeToken<List<Player>>() {
    }.getType();

    public static final ConcurrentHashMap<TeamNames, List<Player>> AllTeams = new ConcurrentHashMap<>();

    private final Gson gson;

    public Teams(Gson gson) {
        this.gson = gson;
    }

    @PostConstruct
    void init() {
        AllTeams.put(TeamNames.CSK, gson.fromJson(CSK.json, type));
        AllTeams.put(TeamNames.DC, gson.fromJson(DC.json, type));
        AllTeams.put(TeamNames.KKR, gson.fromJson(KKR.json, type));
        AllTeams.put(TeamNames.PBKS, gson.fromJson(PBKS.json, type));
        AllTeams.put(TeamNames.GT, gson.fromJson(GT.json, type));
        AllTeams.put(TeamNames.LSG, gson.fromJson(LSG.json, type));
        AllTeams.put(TeamNames.MI, gson.fromJson(MI.json, type));
        AllTeams.put(TeamNames.RCB, gson.fromJson(RCB.json, type));
        AllTeams.put(TeamNames.RR, gson.fromJson(RR.json, type));
        AllTeams.put(TeamNames.SRH, gson.fromJson(SRH.json, type));
    }

}
