package sanbernardo.acambieri.teamcreator.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sanbernardo.acambieri.teamcreator.Utils;
import sanbernardo.acambieri.teamcreator.model.Partecipant;
import sanbernardo.acambieri.teamcreator.model.Config;
import sanbernardo.acambieri.teamcreator.model.Item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileDataSource implements DataSource{

    private Config config;
    Logger log;

    public FileDataSource(String file){
        log= LoggerFactory.getLogger(getClass());
        //List<Item> partecipants = Files.lines(Paths.get(file)).map(FileDataSource::parseLine).collect(Collectors.toList());
        config = new Config(3,new ArrayList<Item>(),100D,0.02D);
        try {
            Files.lines(Paths.get(file)).filter(s -> !s.contains("#")).forEach(line -> {
                try {
                    String tokens[] = line.split("=");
                    switch (tokens[0]) {
                        case "temperature":
                            config.setTemperature(Double.parseDouble(tokens[1]));
                            break;
                        case "rate":
                            config.setRate(Double.parseDouble(tokens[1]));
                            break;
                        case "teamSize":
                            config.setTeamSize(Integer.parseInt(tokens[1]));
                            break;
                        default:
                            config.getPartecipants().add(parseItem(tokens[0]));
                    }
                } catch(NumberFormatException | ParseItemException ex ){
                    log.error("Error parsing file in line [ " + line + "] : " + Utils.exceptionToString(ex));
                    throw new RuntimeException(ex);
                }
            });
        }
        catch(IOException ex){
            log.error("Error reading file " + file + ": " + Utils.exceptionToString(ex));
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Config getConfig() {
        return config;
    }

    public static Item parseItem(String token) throws ParseItemException{
        String split[]=token.split(";");
        return new Partecipant().setName(split[0]).setSurname(split[1]).setScore(Double.parseDouble(split[2]));
    }
}
