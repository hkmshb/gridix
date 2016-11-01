package ng.kedco.gridix.db.dao;

import java.util.List;
import ng.kedco.gridix.db.model.Station;


public interface IStationDao
{
    boolean addStation(Station station);

    Station fetchStationById(int id);

    Station fetchStationByCode(String code);

    Station fetchStationByAltCode(String code);

    List<Station> fetchStations();
}
