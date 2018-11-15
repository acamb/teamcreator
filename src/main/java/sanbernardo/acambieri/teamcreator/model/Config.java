package sanbernardo.acambieri.teamcreator.model;

import java.util.List;

public class Config<T extends Item> {

    private List<T> partecipants;
    private int teamSize;
    private int teams;
    private Double temperature;
    private Double rate;

    public Config(int teamSize,List<T> partecipants,Double temperature,Double rate){
        this.teamSize=teamSize;
        this.partecipants=partecipants;
        this.temperature=temperature;
        this.rate=rate;
        recalculate();
    }

    public Config(int teamSize,List<T> partecipants){
        this(teamSize,partecipants,100D,0.02D);
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public List<T> getPartecipants() {
        return partecipants;
    }

    public void setPartecipants(List<T> partecipants) {
        this.partecipants = partecipants;
        recalculate();

    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
        recalculate();
    }

    public int getTeams() {
        return teams;
    }

    private void recalculate(){
        if(partecipants.size()%teamSize != 0){
            throw new RuntimeException("Partecipants are not divisible by " + teamSize);
        }
        teams = partecipants.size()/teamSize;
    }
}
