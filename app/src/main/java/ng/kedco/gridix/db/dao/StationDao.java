package ng.kedco.gridix.db.dao;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ng.kedco.gridix.db.model.NetworkEntity;
import ng.kedco.gridix.db.model.Station;
import ng.kedco.gridix.db.model.StationType;
import ng.kedco.gridix.db.model.Voltage;
import ng.kedco.gridix.db.schema.EntitySchema;
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

    public Station fetchById(int id)
    {
        final String selection = StationSchema.COL_ID + " = ?";
        final String selectionArgs[] = {
            String.valueOf(id)
        };

        Station station = getStation(selection, selectionArgs);
        return station;
    }

    public Station fetchByCode(String code)
    {
        final String selection = StationSchema.COL_CODE + " = ?";
        final String selectionArgs[]  = { code };

        Station station = getStation(selection, selectionArgs);
        return station;
    }

    public Station fetchByAltCode(String altCode)
    {
        final String selection = StationSchema.COL_ALTCODE + " = ?";
        final String selectionArgs[]  = { altCode };

        Station station = getStation(selection, selectionArgs);
        return station;
    }

    public List<Station> fetchByType(StationType type)
    {
        final String selection = StationSchema.COL_TYPE + " = ?";
        final String selectionArgs[]  = {
            String.valueOf(type.value())
        };

        List<Station> stations = getStations(selection, selectionArgs);
        return stations;
    }

    public List<Station> fetchStations()
    {
        List<Station> stations = getStations(null, null);
        return stations;
    }

    private List<Station> getStations(String selection, String[] selectionArgs)
    {
        List<Station> stations = new ArrayList<>();
        cursor = super.query(StationSchema.TABLE_NAME, StationSchema.COLUMNS,
                             selection, selectionArgs, StationSchema.COL_ID);

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
        values.put(StationSchema.COL_TYPE, station.type().value());
        values.put(StationSchema.COL_SOURCE_POWERLINEID, station.sourcePowerlineId());
        values.put(StationSchema.COL_VOLTAGE_RATIO, station.voltageRatio().value());
        values.put(StationSchema.COL_ADDR_STATEID, station.addrStateId());
        values.put(StationSchema.COL_POSTAL_CODE, station.postalCode());
        values.put(StationSchema.COL_ADDR_STREET, station.addrStreet());
        values.put(StationSchema.COL_ADDR_TOWN, station.addrTown());
        values.put(StationSchema.COL_ADDR_RAW, station.addrRaw());
        values.put(StationSchema.COL_ISPUBLIC, station.isPublic());
        values.put(StationSchema.COL_DATE_COMMISSION,
                   toISODateString(station.dateCommissioned()));

        fillValues(values, (NetworkEntity)station);
        return values;
    }

    protected Station toEntity(Cursor cursor)
    {
        Station station = null;
        if (cursor != null) {
            station = new Station(
                cursor.getInt(cursor.getColumnIndex(EntitySchema.COL_EXTID)),
                cursor.getString(cursor.getColumnIndex(StationSchema.COL_CODE)),
                cursor.getString(cursor.getColumnIndex(StationSchema.COL_ALTCODE)),
                cursor.getString(cursor.getColumnIndex(StationSchema.COL_NAME)),
                StationType.fromInt(cursor.getInt(cursor.getColumnIndex(StationSchema.COL_TYPE))),
                Voltage.Ratio.fromInt(cursor.getInt(cursor.getColumnIndex(StationSchema.COL_VOLTAGE_RATIO))),
                cursor.getInt(cursor.getColumnIndex(StationSchema.COL_SOURCE_POWERLINEID)),
                cursor.getInt(cursor.getColumnIndex(StationSchema.COL_ISPUBLIC)) > 0
            );
        }
        return station;
    }
}
