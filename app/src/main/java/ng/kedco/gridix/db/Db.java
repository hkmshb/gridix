package ng.kedco.gridix.db;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import ng.kedco.gridix.db.dao.IPowerlineDao;
import ng.kedco.gridix.db.dao.IStationDao;
import ng.kedco.gridix.db.dao.PowerlineDao;
import ng.kedco.gridix.db.dao.StationDao;
import ng.kedco.gridix.db.schema.PowerlineSchema;
import ng.kedco.gridix.db.schema.StationSchema;


public class Db
{
    public static final String DB_NAME = "gridix.db3";
    public static final int DB_VERSION = 1;

    private IPowerlineDao powerlineDao = null;
    private IStationDao stationDao = null;

    private DbHelper helper = null;
    private Context context = null;

    public Db(Context context)
    {
        this.context = context;
    }

    public IStationDao getStationDao()
    {
        if (stationDao == null)
            stationDao = new StationDao(getWritableDB());
        return stationDao;
    }

    public IPowerlineDao getPowerlineDao()
    {
        if (powerlineDao == null)
            powerlineDao = new PowerlineDao(getWritableDB());
        return powerlineDao;
    }

    public SQLiteDatabase getWritableDB()
    {
        if (helper == null)
            helper = new DbHelper(this.context);
        return helper.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDB()
    {
        if (helper == null)
            helper = new DbHelper(this.context);
        return helper.getReadableDatabase();
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
            PowerlineSchema.onCreate(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            StationSchema.onUpgrade(db, oldVersion, newVersion);
            PowerlineSchema.onUpgrade(db, oldVersion, newVersion);
            onCreate(db);
        }
    }
}
