package ng.kedco.gridix;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import ng.kedco.gridix.db.dao.Db;


public class DbTest extends AndroidTestCase
{
    public void testDropDB()
    {
        assertTrue(mContext.deleteDatabase(Db.DB_NAME));
        Log.w("DbTest", "testDropDB Passes");
    }

    public void testCreateDB()
    {
        SQLiteDatabase db = new Db(mContext).getWritableDatabase();
        assertTrue(db.isOpen());
        db.close();
        Log.w("DbTest", "testCreateDB Passes");
    }

}
