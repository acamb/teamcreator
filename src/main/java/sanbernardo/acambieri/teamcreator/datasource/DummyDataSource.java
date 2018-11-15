package sanbernardo.acambieri.teamcreator.datasource;

import sanbernardo.acambieri.teamcreator.model.Partecipant;
import sanbernardo.acambieri.teamcreator.model.Config;

import java.util.ArrayList;
import java.util.List;

public class DummyDataSource implements DataSource {

    private Config config;

    public DummyDataSource(){
        List<Partecipant> dummyList = new ArrayList<>();
        for(int i = 0;i<21;i++) {
            dummyList.add(new Partecipant()
                    .setName("pippo" + i)
                    .setSurname("pluto" + i)
                    .setScore( 14+ Math.random() * 16));
        }
        config = new Config(3,dummyList,100D,0.02D);
    }

    @Override
    public Config getConfig() {
        return config;
    }
}
