package ng.kedco.gridix.db.dao;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import ng.kedco.gridix.db.schema.StationSchema;


public class Db
{
    public static final String DB_NAME = "gridix.db3";
    public static final int DB_VERSION = 1;

    private SQLiteDatabase database = null;
    private IStationDao stationDao = null;
    private Context context = null;

    public Db(Context context)
    {
        this.context = context;
    }

    public IStationDao getStationDao()
    {
        if (stationDao == null) {
            stationDao = new StationDao(this.getWritableDatabase());
        }
        return stationDao;
    }

    public SQLiteDatabase getWritableDatabase()
    {
        if (database == null)
            database = new DbHelper(this.context).getWritableDatabase();
        return database;
    }

    private class DbHelper extends SQLiteOpenHelper
    {
        public DbHelper(Context context)
        {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            StationSchema.onCreate(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            // do nothing...
        }
    }
}