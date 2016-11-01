package ng.kedco.gridix;

import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.List;

import ng.kedco.gridix.db.Db;
import ng.kedco.gridix.db.dao.IStationDao;
import ng.kedco.gridix.db.dao.StationDao;
import ng.kedco.gridix.db.model.Station;
import ng.kedco.gridix.db.schema.StationSchema;


public class StationDaoTest extends AndroidTestCase
{
    private IStationDao dao = null;
    private Db db = null;

    public void setUp()
    {
        this.db = new Db(mContext);
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

        Station station = dao.fetchStationByCode("0901");
        assertNotNull(station);

        assertEquals("0901", station.code());
        assertEquals("S0901", station.altCode());
        assertEquals("Kumbotso TS", station.name());
    }

    public void testFetchStationByAltCode()
    {
        assertCount(StationSchema.TABLE_NAME, 0);
        assertTrue(addStation("0901", "S0901", "Kumbotso TS"));

        Station station = dao.fetchStationByAltCode("S0901");
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

    private boolean addStation(String code, String altCode, String name)
    {
        return dao.addStation(new Station(code, altCode, name));
    }

    private void assertCount(String tableName, int expectedCount)
    {
        String query = String.format("SELECT COUNT(*) FROM %s", tableName);
        Cursor cursor = db.getReadableDB().rawQuery(query, null);
        cursor.moveToFirst();

        int actualCount = cursor.getInt(0);
        cursor.close();

        assertEquals(expectedCount, actualCount);
    }
}
