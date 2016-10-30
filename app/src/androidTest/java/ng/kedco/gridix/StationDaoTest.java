package ng.kedco.gridix;

import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.Date;

import ng.kedco.gridix.db.dao.Db;
import ng.kedco.gridix.db.model.Voltage;
import ng.kedco.gridix.db.model.Station;
import ng.kedco.gridix.db.dao.IStationDao;
import ng.kedco.gridix.db.model.StationType;
import ng.kedco.gridix.db.schema.StationSchema;


public class StationDaoTest extends AndroidTestCase
{
    private Db db = null;

    public void setUp()
    {
        this.db = new Db(mContext);
        this.db.getWritableDatabase().execSQL("DELETE FROM " + StationSchema.TABLE_NAME + ";");
    }

    public void testStationCreationInDB()
    {
        // assertCountEqual(0);
        addStation();
        assertCountEqual(1);
    }

    public void testFetchStationFromDB()
    {
        addStation();
        assertCountEqual(1);

        IStationDao dao = db.getStationDao();
        Station station = dao.fetchStationByExtId(1);
        assertNotNull(station);
    }

    @SuppressWarnings("ConstantConditions")
    private void addStation()
    {
        Date today = new Date();
        IStationDao dao = db.getStationDao();
        dao.addStation(new Station(
            1, "0901", "S309", "Kumbotso TS", StationType.TRANSMISSION,
            Voltage.Ratio.HVOLTH_HVOLTL, true, 0, null, null, 0,
            null, null, today, false, null, today, today));
    }

    private void assertCountEqual(int value)
    {
        Cursor cursor = db.getWritableDatabase().rawQuery(
            "SELECT COUNT(*) FROM " + StationSchema.TABLE_NAME + ";",
            new String[] { });
        assertTrue(cursor.getCount() == value);
    }
}
