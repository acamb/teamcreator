package sanbernardo.acambieri.teamcreator.report;

import sanbernardo.acambieri.teamcreator.model.Group;
import sanbernardo.acambieri.teamcreator.model.Item;

import java.util.List;

public interface Report {

    public void generateReport(List<Group<Item>> list, Double target, Double distance);

}
