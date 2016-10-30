package ng.kedco.gridix.db.schema;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class StationSchema extends NetworkEntitySchema
{
    // fields:
    public static final String TABLE_NAME = "Station";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_ADDRRAW = "addr_raw";
    public static final String COLUMN_ADDRSTREET = "addr_street";
    public static final String COLUMN_ADDRTOWN = "addr_town";
    public static final String COLUMN_POSTALCODE = "postal_code";
    public static final String COLUMN_ADDRSTATEID = "addr_state_id";
    public static final String COLUMN_SOURCEPOWERID = "source_power_id";
    public static final String COLUMN_VOLTAGERATIO = "voltage_ratio";

    public static final String[] COLUMNS = new String[] {
        COLUMN_ID, COLUMN_EXTID, COLUMN_CODE, COLUMN_ALTCODE, COLUMN_NAME,
        COLUMN_TYPE, COLUMN_VOLTAGERATIO, COLUMN_ISPUBLIC, COLUMN_SOURCEPOWERID,
        COLUMN_ADDRSTREET, COLUMN_ADDRTOWN, COLUMN_ADDRSTATEID, COLUMN_POSTALCODE,
        COLUMN_ADDRRAW, COLUMN_DATECOMMISSIONED, COLUMN_LASTSYNCED, COLUMN_DELETED,
        COLUMN_DATECREATED, COLUMN_LASTUPDATED
    };

    public static void onCreate(SQLiteDatabase db)
    {
        String dml = String.format(
            "CREATE TABLE %s (" +
            "   %s     INTEGER PRIMARY KEY AUTOINCREMENT" +
            " , %s     VARCHAR(100) NOT NULL UNIQUE" +
            " , %s     INTEGER NOT NULL" +
            " , %s     INTEGER NOT NULL" +
            " , %s     VARCHAR(100) NULL" +
            " , %s     VARCHAR(20) NULL" +
            " , %s     INTEGER NULL" +
            " , %s     VARCHAR(10) NULL" +
            " , %s     VARCHAR(200) NULL" +
            " , %s     INTEGER NULL" +
            NetworkEntitySchema.getPartialDml() + ");",
            StationSchema.TABLE_NAME,
            StationSchema.COLUMN_ID,
            StationSchema.COLUMN_NAME,
            StationSchema.COLUMN_TYPE,
            StationSchema.COLUMN_VOLTAGERATIO,
            StationSchema.COLUMN_ADDRSTREET,
            StationSchema.COLUMN_ADDRTOWN,
            StationSchema.COLUMN_ADDRSTATEID,
            StationSchema.COLUMN_POSTALCODE,
            StationSchema.COLUMN_ADDRRAW,
            StationSchema.COLUMN_SOURCEPOWERID
        );

        Log.w("Station Schema", dml);
        db.execSQL(dml);
    }
}
