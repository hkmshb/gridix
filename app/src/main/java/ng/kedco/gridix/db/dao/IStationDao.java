package ng.kedco.gridix.db.dao;

import java.util.List;
import ng.kedco.gridix.db.model.Station;
import ng.kedco.gridix.db.model.StationType;


public interface IStationDao
{
    boolean addStation(Station station);

    Station fetchById(int id);

    Station fetchByCode(String code);

    Station fetchByAltCode(String code);

    List<Station> fetchStations();

    List<Station> fetchByType(StationType type);
}
