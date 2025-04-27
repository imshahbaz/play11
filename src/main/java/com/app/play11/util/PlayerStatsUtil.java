package com.app.play11.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.client.RestTemplate;

import com.app.play11.model.dto.BattingStats;
import com.app.play11.model.dto.BowlingStats;
import com.app.play11.model.dto.PlayerStats;
import com.app.play11.model.enums.PlayingStyle;
import com.app.play11.model.teams.Player;

public class PlayerStatsUtil {

    public static final String url = "https://www.howstat.com/Cricket/Statistics/IPL/PlayerGrounds.asp?PlayerID=%s#%s";

    public static RestTemplate restTemplate = new RestTemplate();

    public static PlayerStats getPlayerStatByGround(Player player, String playingStyle, String ground) {
        String html = restTemplate.getForObject(String.format(url, player.getId(), playingStyle), String.class);

        PlayerStats temp = null;

        if (playingStyle.equalsIgnoreCase(PlayingStyle.BATSMAN.getStyle())) {
            temp = BattingStats.builder().name(player.getName()).build();
        } else {
            temp = BowlingStats.builder().name(player.getName()).build();
        }

        if (StringUtils.isBlank(html)) {
            return temp;
        }

        Document doc = Jsoup.parse(html);

        if (doc == null) {
            return temp;
        }

        Element element = doc.getElementById(playingStyle);

        if (element == null) {
            return temp;
        }

        Element table = element.selectFirst("table");

        if (table == null) {
            return temp;
        }

        Elements rows = table.select("tr");

        if (rows == null) {
            return temp;
        }

        for (int i = 1; i < rows.size() - 1; i++) {
            Elements cells = rows.get(i).select("td");

            String gr = cells.get(0).text();

            if (!gr.equalsIgnoreCase(ground)) {
                continue;
            }

            if (playingStyle.equalsIgnoreCase(PlayingStyle.BATSMAN.getStyle())) {
                temp = BattingStats.builder()
                        .name(player.getName())
                        .ground(cells.get(0).text())
                        .mat(parseDoubleOrDefault(cells.get(1).text()))
                        .inns(parseDoubleOrDefault(cells.get(2).text()))
                        .no(parseDoubleOrDefault(cells.get(3).text()))
                        .hundreds(parseDoubleOrDefault(cells.get(4).text()))
                        .fifties(parseDoubleOrDefault(cells.get(5).text()))
                        .ducks(parseDoubleOrDefault(cells.get(6).text()))
                        .fours(parseDoubleOrDefault(cells.get(7).text()))
                        .sixes(parseDoubleOrDefault(cells.get(8).text()))
                        .hs(parseDoubleOrDefault(cells.get(9).text()))
                        .runs(parseDoubleOrDefault(cells.get(10).text()))
                        .avg(parseDoubleOrDefault(cells.get(11).text()))
                        .sr(parseDoubleOrDefault(cells.get(12).text()))
                        .catches(parseDoubleOrDefault(cells.get(13).text()))
                        .stumpings(parseDoubleOrDefault(cells.get(14).text()))
                        .build();
            } else {
                temp = BowlingStats.builder()
                        .name(player.getName())
                        .ground(cells.get(0).text())
                        .mat(parseDoubleOrDefault(cells.get(1).text()))
                        .inns(parseDoubleOrDefault(cells.get(2).text()))
                        .runs(parseDoubleOrDefault(cells.get(5).text()))
                        .avg(parseDoubleOrDefault(cells.get(9).text()))
                        .sr(parseDoubleOrDefault(cells.get(10).text()))
                        .overs(parseDoubleOrDefault(cells.get(3)
                                .text()))
                        .maiden(parseDoubleOrDefault(cells.get(4)
                                .text()))
                        .wickets(parseDoubleOrDefault(cells.get(6)
                                .text()))
                        .fourWickets(parseDoubleOrDefault(cells.get(7)
                                .text()))
                        .best(cells.get(8)
                                .text())
                        .economy(parseDoubleOrDefault(cells.get(11)
                                .text()))
                        .build();
            }

            return temp;
        }

        return temp;
    }

    public static List<BattingStats> getPlayerStat(Player player, String playingStyle) {
        String html = restTemplate.getForObject(String.format(url, player.getId(), playingStyle), String.class);

        if (StringUtils.isBlank(html)) {
            return Collections.emptyList();
        }

        Document doc = Jsoup.parse(html);

        if (doc == null) {
            return Collections.emptyList();
        }

        Element element = doc.getElementById(playingStyle);

        if (element == null) {
            return Collections.emptyList();
        }

        Element table = element.selectFirst("table");

        if (table == null) {
            return Collections.emptyList();
        }

        Elements rows = table.select("tr");

        if (rows == null) {
            return Collections.emptyList();
        }

        List<BattingStats> statsList = new ArrayList<>();

        for (int i = 1; i < rows.size() - 1; i++) {
            Elements cells = rows.get(i).select("td");

            BattingStats stats = BattingStats.builder()
                    .name(player.getName())
                    .ground(cells.get(0).text())
                    .mat(parseDoubleOrDefault(cells.get(1).text()))
                    .inns(parseDoubleOrDefault(cells.get(2).text()))
                    .no(parseDoubleOrDefault(cells.get(3).text()))
                    .hundreds(parseDoubleOrDefault(cells.get(4).text()))
                    .fifties(parseDoubleOrDefault(cells.get(5).text()))
                    .ducks(parseDoubleOrDefault(cells.get(6).text()))
                    .fours(parseDoubleOrDefault(cells.get(7).text()))
                    .sixes(parseDoubleOrDefault(cells.get(8).text()))
                    .hs(parseDoubleOrDefault(cells.get(9).text()))
                    .runs(parseDoubleOrDefault(cells.get(10).text()))
                    .avg(parseDoubleOrDefault(cells.get(11).text()))
                    .sr(parseDoubleOrDefault(cells.get(12).text()))
                    .catches(parseDoubleOrDefault(cells.get(13).text()))
                    .stumpings(parseDoubleOrDefault(cells.get(14).text()))
                    .build();

            statsList.add(stats);
        }

        return statsList;
    }

    private static double parseDoubleOrDefault(String text) {
        if (StringUtils.isEmpty(text)) {
            return 0.0;
        }

        StringBuilder builder = new StringBuilder("");

        for (char c : text.trim().toCharArray()) {
            if (((int) c >= 48 && (int) c <= 57) || c == '.') {
                builder.append(c);
            }
        }
        return Double.parseDouble(builder.toString());
    }

}
