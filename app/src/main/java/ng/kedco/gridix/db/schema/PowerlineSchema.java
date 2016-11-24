package ng.kedco.gridix.db.schema;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class PowerlineSchema extends NetworkEntitySchema
{
    public static final String TABLE_NAME = "Powerline";
    public static final String COL_NAME = "name";
    public static final String COL_TYPE = "type";
    public static final String COL_VOLTAGE = "voltage";
    public static final String COL_ISPUBLIC = "is_public";
    public static final String COL_POLE_COUNT = "pole_count";
    public static final String COL_LINE_LENGTH = "line_length";
    public static final String COL_SOURCE_STATIONID = "source_station_id";

    public static String[] COLUMNS = new String[] { COL_ID,
        COL_EXTID, COL_DELETED, COL_LAST_UPDATED, COL_CODE, COL_ALTCODE,
        COL_NAME, COL_TYPE, COL_VOLTAGE, COL_SOURCE_STATIONID, COL_ISPUBLIC,
        COL_POLE_COUNT, COL_LINE_LENGTH, COL_DATE_COMMISSION
    };

    public static void onCreate(SQLiteDatabase db)
    {
        String dml = String.format(
            "CREATE TABLE %s (" +
            "   %s     INTEGER PRIMARY KEY AUTOINCREMENT" +   // id
            " , %s     INTEGER NOT NULL UNIQUE" +             // ext_id
            " , %s     VARCHAR(100) NOT NULL" +               // name
            " , %s     INTEGER NOT NULL" +                    // type
            " , %s     INTEGER NOT NULL" +                    // voltage
            " , %s     INTEGER NULL" +                        // src_stn
            " , %s     INTEGER NULL" +                        // pole_count
            " , %s     INTEGER NULL" +                        // line_len
            NetworkEntitySchema.getDml() + ");",
            PowerlineSchema.TABLE_NAME,
            PowerlineSchema.COL_ID,
            PowerlineSchema.COL_EXTID,
            PowerlineSchema.COL_NAME,
            PowerlineSchema.COL_TYPE,
            PowerlineSchema.COL_VOLTAGE,
            PowerlineSchema.COL_SOURCE_STATIONID,
            PowerlineSchema.COL_POLE_COUNT,
            PowerlineSchema.COL_LINE_LENGTH
        );
        db.execSQL(dml);
        Log.w("Powerline", dml);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(String.format(
            "DROP TABLE IF EXISTS %s",
            TABLE_NAME
        ));
    }
}
