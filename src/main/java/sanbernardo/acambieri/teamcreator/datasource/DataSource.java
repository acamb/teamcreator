package sanbernardo.acambieri.teamcreator.datasource;

import sanbernardo.acambieri.teamcreator.model.Config;
import sanbernardo.acambieri.teamcreator.model.Item;

public interface DataSource<T extends Item> {

    public Config<T> getConfig();
}
