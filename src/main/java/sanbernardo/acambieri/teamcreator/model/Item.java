package sanbernardo.acambieri.teamcreator.model;

public interface Item extends Cloneable{

    public Double getScore();
    public String getDescription();
    public Item clone();
}
