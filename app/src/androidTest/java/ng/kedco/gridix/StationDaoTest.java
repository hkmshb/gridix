package ng.kedco.gridix;

import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.List;

import ng.kedco.gridix.db.Db;
import ng.kedco.gridix.db.dao.IStationDao;
import ng.kedco.gridix.db.dao.StationDao;
import ng.kedco.gridix.db.model.Station;
import ng.kedco.gridix.db.model.StationType;
import ng.kedco.gridix.db.model.Voltage;
import ng.kedco.gridix.db.schema.StationSchema;


public class StationDaoTest extends BaseDaoTest
{
    private IStationDao dao = null;


    public void setUp()
    {
        super.setUp();
        this.dao = db.getStationDao();
        this.db.getWritableDB().execSQL(
            "DELETE FROM " + StationSchema.TABLE_NAME + ";");
    }

    public void testAddStation()
    {
        assertCount(StationSchema.TABLE_NAME, 0);
        assertTrue(addStation("0901", "S0901", "Kumbotso TS"));
        assertCount(StationSchema.TABLE_NAME, 1);
    }

    public void testAddMultipleStations()
    {
        assertCount(StationSchema.TABLE_NAME, 0);
        assertTrue(addStation("0901", "S0901", "Kumbotso TS"));
        assertTrue(addStation("0902", "S0902", "Dan'Agundi TS"));
        assertTrue(addStation("0903", "S0903", "Dakata TS"));
        assertCount(StationSchema.TABLE_NAME, 3);
    }

    public void testFetchStationByCode()
    {
        assertCount(StationSchema.TABLE_NAME, 0);
        assertTrue(addStation("0901", "S0901", "Kumbotso TS"));

        Station station = dao.fetchByCode("0901");
        assertNotNull(station);

        assertEquals("0901", station.code());
        assertEquals("S0901", station.altCode());
        assertEquals("Kumbotso TS", station.name());
    }

    public void testFetchStationByAltCode()
    {
        assertCount(StationSchema.TABLE_NAME, 0);
        assertTrue(addStation("0901", "S0901", "Kumbotso TS"));

        Station station = dao.fetchByAltCode("S0901");
        assertNotNull(station);

        assertEquals("0901", station.code());
        assertEquals("S0901", station.altCode());
        assertEquals("Kumbotso TS", station.name());
    }

    public void testFetchStations()
    {
        assertCount(StationSchema.TABLE_NAME, 0);
        assertTrue(addStation("0901", "S0901", "Kumbotso TS"));
        assertTrue(addStation("0902", "S0902", "Dan'Agundi TS"));
        assertTrue(addStation("0903", "S0903", "Dakata TS"));

        List<Station> stations = dao.fetchStations();
        assertNotNull(stations);
        assertEquals(3, stations.size());
    }

    public void testFetchStationsByType()
    {
        assertCount(StationSchema.TABLE_NAME, 0);
        assertTrue(addStation("0901", "S0901", "Kumbotso TS"));
        assertTrue(addStation("0902", "S0902", "Dan'Agundi TS"));
        assertTrue(addStation("0903", "S0903", "Dakata TS"));
        assertTrue(addStation("0904", "S0904", "Hadejia IS", StationType.INJECTION));

        assertCount(StationSchema.TABLE_NAME, 4);
        List<Station> stations = dao.fetchByType(StationType.INJECTION);
        assertNotNull(stations);
        assertEquals(1, stations.size());

        stations = dao.fetchByType(StationType.TRANSMISSION);
        assertNotNull(stations);
        assertEquals(3, stations.size());
    }

    private boolean addStation(String code, String altCode, String name)
    {
        return addStation(code, altCode, name, StationType.TRANSMISSION);
    }

    private boolean addStation(String code, String altCode, String name, StationType type)
    {
        Voltage.Ratio voltage_ratio =Voltage.Ratio.HVOLTL_MVOLTH;
        return dao.addStation(new Station(
            Integer.parseInt(code), code, altCode, name,
            type, voltage_ratio, 0, true));
    }
}
