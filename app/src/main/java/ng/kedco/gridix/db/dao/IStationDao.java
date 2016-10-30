package ng.kedco.gridix.db.dao;

import java.util.List;
import ng.kedco.gridix.db.model.Station;
import ng.kedco.gridix.db.model.StationType;


public interface IStationDao
{
    boolean addStation(Station station);

    boolean addStations(List<Station> stations);

    boolean deleteStation(Station stationId);

    boolean deleteStations(List<Station> stations);

    List<Station> fetchStations();

    List<Station> fetchStationsByType(StationType type);

    Station fetchStationById(int stationId);

    Station fetchStationByExtId(int extId);
}
