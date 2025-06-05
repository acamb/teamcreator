package sanbernardo.acambieri.teamcreator;

import sanbernardo.acambieri.teamcreator.datasource.DataSource;
import sanbernardo.acambieri.teamcreator.datasource.DummyDataSource;
import sanbernardo.acambieri.teamcreator.datasource.FileDataSource;
import sanbernardo.acambieri.teamcreator.model.Config;
import sanbernardo.acambieri.teamcreator.model.Group;
import sanbernardo.acambieri.teamcreator.model.Item;
import sanbernardo.acambieri.teamcreator.report.PrintOutputReport;
import sanbernardo.acambieri.teamcreator.report.Report;

import java.util.ArrayList;
import java.util.List;

public class TeamCreator {

    private static final int simulations = 1000000000;

    public static void main(String[] args){
        TeamCreator t = new TeamCreator();
        DataSource ds=null;
        Report report = new PrintOutputReport();
        if(args.length > 0 && args[0].equals("-h")){
            System.out.println("Usage: java -jar TeamCreator.jar [-h|-f] [path_to_file]");
            System.out.println("    -f [path_to_file] use file as datasource");
            System.out.println("    -h this help");
            System.out.println("If no options are specified a dummy dataset is used.");
            System.exit(0);
        }
        else if(args.length > 0 && args[0].equals("-f")) {
             ds = new FileDataSource(args[1]);
        }
        else{
            System.out.println("DATASOURCE NOT DEFINED, USING DUMMY DATA! ...");
            ds=new DummyDataSource();
        }
        t.runSimulation(ds,report);
    }

    public void runSimulation(DataSource ds,Report report){
        Config<Item> config = ds.getConfig();
        List<Group<Item>> solution = generateRandomSolution(config);
        List<Group<Item>> best;
        Double target = config.getPartecipants().stream().mapToDouble(Item::getScore).average().getAsDouble();
        Double currentDistance = calculateMaxDistance(solution,target);
        best = solution;
        for (int i=0;i<simulations;i++){
            if(config.getTemperature() > 0.001){
                List<Group<Item>> newSolution = mutate(solution);
                Double newDIstance = calculateMaxDistance(newSolution,target);
                if( currentDistance > newDIstance){
                    currentDistance=newDIstance;
                    solution=newSolution;
                    best=newSolution;
                }
                else if(acceptance(currentDistance,newDIstance,config.getTemperature())> Math.random()){
                    currentDistance=newDIstance;
                    solution=newSolution;
                }
                config.setTemperature(config.getTemperature()-(config.getTemperature()*config.getRate()));
            }
            else{
                break;
            }
        }
        report.generateReport(best,target,currentDistance);
    }

    private static Double acceptance(Double currentDistance,Double newDistance,Double temperature){
        if (newDistance < currentDistance){
            return 1.0;
        }
        return Math.exp((currentDistance - newDistance) / temperature);
    }

    private List<Group<Item>> mutate(List<Group<Item>> solution){
        List<Group<Item>> newSolution = new ArrayList<>();
        solution.forEach(t -> newSolution.add(t.clone()));
        Group<Item> group1 = newSolution.stream().max((a, b) -> (a.getAverageScore()-b.getAverageScore() ) > 0 ? 1 : -1).get();
        Group<Item> group2 = newSolution.stream().min((a, b) -> (a.getAverageScore()-b.getAverageScore() ) > 0 ? 1 : -1).get();
        Item i = group1.removeRandom();
        group1.add(group2.swapWithRandom(i));
        return newSolution;
    }

    private List<Group<Item>> generateRandomSolution(Config<Item> config){
        List<Group<Item>> solution = new ArrayList<>();
        List<Item> partecipants = new ArrayList<>(config.getPartecipants());
        while(!partecipants.isEmpty()){
            Group<Item> t = new Group<>();
            for(int i = 0;i<config.getTeamSize();i++){
                t.add(partecipants.remove((int) (Math.random() * (partecipants.size() - 1))));
            }
            solution.add(t);
        }
        return solution;
    }

    private Double calculateMaxDistance(List<Group<Item>> solution, Double target){
        return solution.stream().mapToDouble(Group::getAverageScore).max().getAsDouble() - solution.stream().mapToDouble(Group::getAverageScore).min().getAsDouble();
    }

}
