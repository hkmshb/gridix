package ng.kedco.gridix.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shaibujnr on 10/19/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gridix_store.db";
    public static final String STATIONS_TABLE_NAME = "stations_table";
    public static final String SNAME = "station_name";
    public static final String SVOLTAGE_RATIO = "station_voltage_ratio";
    public static final String SCODE = "station_code";
    public static final String SALT_CODE = "station_alt_code";
    public static final String POWERLINES_TABLE_NAME = "powerlines_table";
    public static final String PNAME = "powerline_name";
    public static final String PVOLTAGE = "powerline_voltage";
    public static final String PCODE = "powerline_code";
    public static final String PALT_CODE = "powerline_alt_code";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
