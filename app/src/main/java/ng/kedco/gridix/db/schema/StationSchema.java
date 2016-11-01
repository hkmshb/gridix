package ng.kedco.gridix.db.schema;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class StationSchema extends NetworkEntitySchema
{
    public static final String TABLE_NAME = "Station";
    public static final String COL_NAME = "name";

    public static String[] COLUMNS = new String[] {
        COL_ID, COL_DELETED, COL_LAST_UPDATED, COL_CODE, COL_ALTCODE,
        COL_ISPUBLIC, COL_DATE_COMMISSION, COL_NAME
    };

    public static void onCreate(SQLiteDatabase db)
    {
        String dml = String.format(
            "CREATE TABLE %s (" +
            "   %s     INTEGER PRIMARY KEY AUTOINCREMENT" +
            " , %s     VARCHAR(100) NOT NULL" +
            NetworkEntitySchema.getDml() + ");",
            StationSchema.TABLE_NAME,
            EntitySchema.COL_ID,
            StationSchema.COL_NAME
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

// _id
// name
// code
// alt_code
// is_public
// date_commissioned
// deleted
// last_updated


