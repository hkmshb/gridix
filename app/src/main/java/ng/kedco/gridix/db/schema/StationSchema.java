package ng.kedco.gridix.db.schema;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class StationSchema extends NetworkEntitySchema
{
    public static final String TABLE_NAME = "Station";
    public static final String COL_NAME = "name";
    public static final String COL_TYPE = "type";
    public static final String COL_ADDR_RAW = "addr_raw";
    public static final String COL_ADDR_TOWN = "addr_town";
    public static final String COL_ADDR_STREET = "addr_street";
    public static final String COL_POSTAL_CODE = "postal_code";
    public static final String COL_ADDR_STATEID = "addr_state_id";
    public static final String COL_VOLTAGE_RATIO = "voltage_ratio";
    public static final String COL_SOURCE_POWERLINEID = "source_powerline_id";

    public static String[] COLUMNS = new String[] { COL_ID,
        COL_EXTID, COL_DELETED, COL_LAST_UPDATED, COL_CODE, COL_ALTCODE,
        COL_NAME, COL_TYPE, COL_VOLTAGE_RATIO, COL_SOURCE_POWERLINEID,
        COL_ADDR_STREET, COL_ADDR_TOWN, COL_ADDR_STATEID, COL_ADDR_RAW,
        COL_ISPUBLIC, COL_DATE_COMMISSION
    };

    public static void onCreate(SQLiteDatabase db)
    {
        String dml = String.format(
            "CREATE TABLE %s (" +
            "   %s     INTEGER PRIMARY KEY AUTOINCREMENT" + // id
            " , %s     INTEGER NOT NULL UNIQUE" +           // ext_id
            " , %s     VARCHAR(100) NOT NULL" +             // name
            " , %s     INTEGER NOT NULL" +                  // type
            " , %s     INTEGER NOT NULL" +                  // vr
            " , %s     INTEGER NULL" +                      // src_pwl
            " , %s     VARCHAR(100) NULL" +                 // addr_str
            " , %s     VARCHAR(30) NULL" +                  // addr_town
            " , %s     VARCHAR(20) NULL" +                  // postal
            " , %s     INTEGER NULL" +                      // addr_st_id
            " , %s     VARCHAR(200) NULL" +                 // addr_raw
            NetworkEntitySchema.getDml() + ");",
            StationSchema.TABLE_NAME,
            EntitySchema.COL_ID,
            EntitySchema.COL_EXTID,
            StationSchema.COL_NAME,
            StationSchema.COL_TYPE,
            StationSchema.COL_VOLTAGE_RATIO,
            StationSchema.COL_SOURCE_POWERLINEID,
            StationSchema.COL_ADDR_STREET,
            StationSchema.COL_ADDR_TOWN,
            StationSchema.COL_POSTAL_CODE,
            StationSchema.COL_ADDR_STATEID,
            StationSchema.COL_ADDR_RAW
        );
        db.execSQL(dml);
        Log.w("Station", dml);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(String.format(
            "DROP TABLE IF EXISTS %s",
            TABLE_NAME
        ));
    }
}


