package com.app.play11.model.dto;

import java.util.Comparator;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BattingStats extends PlayerStats {
    double mat, inns, no, hundreds, fifties, ducks, fours, sixes, hs, catches, stumpings;

    public String toCSV() {
        return super.getName() + "," +
                super.getGround() + "," +
                mat + "," +
                inns + "," +
                no + "," +
                hundreds + "," +
                fifties + "," +
                ducks + "," +
                fours + "," +
                sixes + "," +
                hs + "," +
                super.getRuns() + "," +
                catches + "," +
                stumpings + "," +
                super.getAvg() + "," +
                super.getSr();
    }

    // Comparator for runs (highest first)
    public static Comparator<BattingStats> RunsComparator = (p1, p2) -> Double.compare(p2.getRuns(), p1.getRuns());

    // Comparator for avg (highest first)
    public static Comparator<BattingStats> AvgComparator = (p1, p2) -> Double.compare(p2.getAvg(), p1.getAvg());
}