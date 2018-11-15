package sanbernardo.acambieri.teamcreator.report;

import sanbernardo.acambieri.teamcreator.model.Group;
import sanbernardo.acambieri.teamcreator.model.Item;

import java.util.List;

public class PrintOutputItalianReport implements Report {

    @Override
    public void generateReport(List<Group<Item>> list, Double target, Double distance) {
        System.out.println("Distanza massima attuale: " + distance);
        System.out.println("Punteggio medio totale: " + target);
        list.forEach(t -> {
            System.out.println("\nSquadra");
            System.out.println("Punteggio medio: " + t.getAverageScore());
            System.out.println("Partecipanti: ");
            t.getItems().forEach(i -> System.out.println("    " + i.getDescription() + ", ppf: " + i.getScore()));
        });
    }
}
