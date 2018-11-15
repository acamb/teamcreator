package sanbernardo.acambieri.teamcreator.report;

import sanbernardo.acambieri.teamcreator.model.Group;
import sanbernardo.acambieri.teamcreator.model.Item;

import java.util.List;

public class PrintOutputReport implements Report {

    @Override
    public void generateReport(List<Group<Item>> list, Double target, Double distance) {
        System.out.println("Max distance: " + distance);
        System.out.println("Average total score: " + target);
        list.forEach(t -> {
            System.out.println("\nGroup");
            System.out.println("Average score: " + t.getAverageScore());
            System.out.println("Partecipants: ");
            t.getItems().forEach(i -> System.out.println("    " + i.getDescription() + ", ppf: " + i.getScore()));
        });
    }
}
