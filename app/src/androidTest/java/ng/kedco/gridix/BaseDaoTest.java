package ng.kedco.gridix;

import android.database.Cursor;
import android.test.AndroidTestCase;

import ng.kedco.gridix.db.Db;


public abstract class BaseDaoTest extends AndroidTestCase
{
    protected Db db = null;

    public void setUp()
    {
        this.db = new Db(mContext);
    }

    protected void assertCount(String tableName, int expectedCount)
    {
        String query = String.format("SELECT COUNT(*) FROM %s", tableName);
        Cursor cursor = db.getReadableDB().rawQuery(query, null);
        cursor.moveToFirst();

        int actualCount = cursor.getInt(0);
        cursor.close();

        assertEquals(expectedCount, actualCount);
    }
}
