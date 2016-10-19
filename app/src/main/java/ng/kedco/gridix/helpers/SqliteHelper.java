package ng.kedco.gridix.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ng.kedco.gridix.models.PowerLine;
import ng.kedco.gridix.models.Station;

/**
 * Created by shaibujnr on 10/18/16.
 */
public class SqliteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "local_store.db";
    private static final int DATABASE_VERSION = 1;
    private static final String STATIONS_TABLE_NAME = "stations_table";
    private static final String POWERLINES_TABLE_NAME = "powerlines_table";
    private static final String SUID = "_id";
    private static final String SNAME = "station_name";
    private static final String SVOLTAGE_RATIO = "station_voltage_ratio";
    private static final String SCODE = "station_code";
    private static final String SALTCODE = "station_alt_code";
    private static final String SLAST_SYNC = "station_last_sync";

    private static final String PUID = "_id";
    private static final String PNAME = "powerline_name";
    private static final String PVOLTAGE = "powerline_voltage";
    private static final String PCODE = "powerline_code";
    private static final String PALTCODE = "powerline_alt_code";
    private static final String PLAST_SYNC = "powerline_last_sync";

    private SQLiteDatabase sdb;

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.sdb = sqLiteDatabase;
        sdb.execSQL("CREATE TABLE "+STATIONS_TABLE_NAME+" ("+SUID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+SNAME+","+SVOLTAGE_RATIO+","+SCODE+","
                +SALTCODE+");");
        sdb.execSQL("CREATE TABLE "+POWERLINES_TABLE_NAME+" ("+PUID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+PNAME+","+PVOLTAGE+","+PCODE+","+PALTCODE+");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sdb.execSQL("DROP TABLE IF EXISTS " + STATIONS_TABLE_NAME);
        sdb.execSQL("DROP TABLE IF EXISTS " + POWERLINES_TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void insertStation(Station station){
        ContentValues cv = new ContentValues();
        cv.put(SNAME,station.getName());
        cv.put(SVOLTAGE_RATIO,station.getVoltageRatio().toString());
        cv.put(SCODE,station.getCode());
        cv.put(SALTCODE,station.getAltCode());
        sdb.insert(STATIONS_TABLE_NAME,SNAME,cv);


    }

    public void insertPowerLine(PowerLine powerLine){
        ContentValues cv = new ContentValues();
        cv.put(PNAME,powerLine.getName());
        cv.put(PVOLTAGE,powerLine.getVoltage().toString());
        cv.put(PCODE,powerLine.getCode());
        cv.put(PALTCODE,powerLine.getAltCode());
        sdb.insert(POWERLINES_TABLE_NAME,PNAME,cv);
    }

}
