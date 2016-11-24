package ng.kedco.gridix.db.dao;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

import ng.kedco.gridix.db.model.Voltage;
import ng.kedco.gridix.db.model.Powerline;
import ng.kedco.gridix.db.model.NetworkEntity;
import ng.kedco.gridix.db.model.PowerlineType;
import ng.kedco.gridix.db.schema.EntitySchema;
import ng.kedco.gridix.db.schema.PowerlineSchema;


public class PowerlineDao extends BaseDao implements IPowerlineDao
{
    private Cursor cursor;

    public PowerlineDao(SQLiteDatabase db)
    {
        super(db);
    }

    public boolean addPowerline(Powerline powerline)
    {
        ContentValues values = makeValues(powerline);
        return insert(PowerlineSchema.TABLE_NAME, values) > 0;
    }

    public Powerline fetchById(int id)
    {
        final String selection = PowerlineSchema.COL_ID + " = ?";
        final String selectionArgs[] = {
            String.valueOf(id)
        };
        Powerline powerline = getPowerline(selection, selectionArgs);
        return powerline;
    }

    public Powerline fetchByCode(String code)
    {
        final String selection = PowerlineSchema.COL_CODE + " = ?";
        final String selectionArgs[] = { code };

        Powerline powerline = getPowerline(selection, selectionArgs);
        return powerline;
    }

    public Powerline fetchByAltCode(String altCode)
    {
        final String selection = PowerlineSchema.COL_ALTCODE + " = ?";
        final String selectionArgs[] = { altCode };

        Powerline powerline = getPowerline(selection, selectionArgs);
        return powerline;
    }

    public List<Powerline> fetchByType(PowerlineType type)
    {
        final String selection = PowerlineSchema.COL_TYPE + " = ?";
        final String selectionArgs[] ={
            String.valueOf(type.value())
        };

        List<Powerline> powerlines = getPowerlines(selection, selectionArgs);
        return powerlines;
    }

    public List<Powerline> fetchPowerlines()
    {
        List<Powerline> powerlines = getPowerlines(null, null);
        return powerlines;
    }

    private List<Powerline> getPowerlines(String selection, String[] selectionArgs)
    {
        List<Powerline> powerlines = new ArrayList<>();
        cursor = super.query(PowerlineSchema.TABLE_NAME, PowerlineSchema.COLUMNS,
                             selection, selectionArgs, PowerlineSchema.COL_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Powerline powerline = toEntity(cursor);
                powerlines.add(powerline);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return powerlines;
    }


    private Powerline getPowerline(String selection, String[] selectionArgs)
    {
        Powerline powerline = null;
        cursor = super.query(PowerlineSchema.TABLE_NAME, PowerlineSchema.COLUMNS,
                             selection, selectionArgs, PowerlineSchema.COL_CODE);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                powerline = toEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return powerline;
    }

    protected ContentValues makeValues(Powerline powerline)
    {
        ContentValues values = new ContentValues();
        values.put(PowerlineSchema.COL_NAME, powerline.name());
        values.put(PowerlineSchema.COL_TYPE, powerline.type().value());
        values.put(PowerlineSchema.COL_SOURCE_STATIONID, powerline.sourceStationId());
        values.put(PowerlineSchema.COL_VOLTAGE, powerline.voltage().value());
        values.put(PowerlineSchema.COL_POLE_COUNT, powerline.poleCount());
        values.put(PowerlineSchema.COL_LINE_LENGTH, powerline.lineLength());
        values.put(PowerlineSchema.COL_ISPUBLIC, powerline.isPublic());
        values.put(PowerlineSchema.COL_DATE_COMMISSION,
            toISODateString(powerline.dateCommissioned()));

        fillValues(values, (NetworkEntity)powerline);
        return values;
    }

    protected Powerline toEntity(Cursor cursor)
    {
        Powerline powerline = null;
        if (cursor != null) {
            powerline = new Powerline(
                cursor.getInt(cursor.getColumnIndex(EntitySchema.COL_EXTID)),
                cursor.getString(cursor.getColumnIndex(PowerlineSchema.COL_CODE)),
                cursor.getString(cursor.getColumnIndex(PowerlineSchema.COL_ALTCODE)),
                cursor.getString(cursor.getColumnIndex(PowerlineSchema.COL_NAME)),
                PowerlineType.fromInt(cursor.getInt(cursor.getColumnIndex(PowerlineSchema.COL_TYPE))),
                Voltage.fromInt(cursor.getInt(cursor.getColumnIndex(PowerlineSchema.COL_VOLTAGE))),
                cursor.getInt(cursor.getColumnIndex(PowerlineSchema.COL_SOURCE_STATIONID)),
                cursor.getInt(cursor.getColumnIndex(PowerlineSchema.COL_ISPUBLIC)) > 0
            );
        }
        return powerline;
    }
}
