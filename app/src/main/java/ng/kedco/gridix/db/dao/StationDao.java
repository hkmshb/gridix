package ng.kedco.gridix.db.dao;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ng.kedco.gridix.db.model.NetworkEntity;
import ng.kedco.gridix.db.model.Station;
import ng.kedco.gridix.db.schema.StationSchema;


public class StationDao extends BaseDao implements IStationDao
{
    private Cursor cursor;

    public StationDao(SQLiteDatabase db)
    {
        super(db);
    }

    public boolean addStation(Station station)
    {
        ContentValues values = makeValues(station);
        return insert(StationSchema.TABLE_NAME, values) > 0;
    }

    public Station fetchStationById(int id)
    {
        final String selection = StationSchema.COL_ID + " = ?";
        final String selectionArgs[] = {
            String.valueOf(id)
        };

        Station station = getStation(selection, selectionArgs);
        return station;
    }

    public Station fetchStationByCode(String code)
    {
        final String selection = StationSchema.COL_CODE + " = ?";
        final String selectionArgs[]  = { code };

        Station station = getStation(selection, selectionArgs);
        return station;
    }

    public Station fetchStationByAltCode(String altCode)
    {
        final String selection = StationSchema.COL_ALTCODE + " = ?";
        final String selectionArgs[]  = { altCode };

        Station station = getStation(selection, selectionArgs);
        return station;
    }

    public List<Station> fetchStations()
    {
        List<Station> stations = new ArrayList<>();
        cursor = super.query(StationSchema.TABLE_NAME, StationSchema.COLUMNS,
                             null, null, StationSchema.COL_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Station station = toEntity(cursor);
                stations.add(station);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return stations;
    }

    private Station getStation(String selection, String[] selectionArgs)
    {
        Station station = null;
        cursor = super.query(StationSchema.TABLE_NAME, StationSchema.COLUMNS,
                             selection, selectionArgs, StationSchema.COL_CODE);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                station = toEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return station;
    }

    protected ContentValues makeValues(Station station)
    {
        ContentValues values = new ContentValues();
        values.put(StationSchema.COL_NAME, station.name());
        values.put(StationSchema.COL_ISPUBLIC, station.isPublic());
        values.put(StationSchema.COL_DATE_COMMISSION, toISODateString(station.getDateCommissioned()));
        fillValues(values, (NetworkEntity)station);
        return values;
    }

    protected Station toEntity(Cursor cursor)
    {
        Station station = null;
        if (cursor != null) {
            station = new Station(
                cursor.getString(cursor.getColumnIndex(StationSchema.COL_CODE)),
                cursor.getString(cursor.getColumnIndex(StationSchema.COL_ALTCODE)),
                cursor.getString(cursor.getColumnIndex(StationSchema.COL_NAME)));
        }
        return station;
    }
}
