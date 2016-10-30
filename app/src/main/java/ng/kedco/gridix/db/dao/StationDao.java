package ng.kedco.gridix.db.dao;

import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ng.kedco.gridix.db.schema.EntitySchema;
import ng.kedco.gridix.db.schema.NetworkEntitySchema;
import ng.kedco.gridix.db.schema.StationSchema;
import ng.kedco.gridix.db.model.Station;
import ng.kedco.gridix.db.model.StationType;
import ng.kedco.gridix.db.model.Voltage;


public class StationDao extends BaseDao
    implements IStationDao
{
    private Cursor cursor;
    private ContentValues initValues;

    public StationDao(SQLiteDatabase db)
    {
        super(db);
    }

    public boolean addStation(Station station)
    {
        ContentValues values = getContentValues(station);
        try {
            return super.insert(StationSchema.TABLE_NAME, values) > 0;
        } catch (SQLiteConstraintException ex) {
            Log.w("StationDao", ex.getMessage());
            return false;
        }
    }

    public boolean addStations(List<Station> stations)
    {
        return false;
    }

    public boolean deleteStation(Station stationId)
    {
        return false;
    }

    public boolean deleteStations(List<Station> stations)
    {
        return false;
    }


    public Station fetchStationById(int stationId)
    {
        final String selectionArgs[] = { String.valueOf(stationId) };
        final String selection = StationSchema.COLUMN_ID + " = ?";
        cursor = super.query(StationSchema.TABLE_NAME, StationSchema.COLUMNS,
                             selection, selectionArgs, EntitySchema.COLUMN_ID);

        Station station = null;
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                station = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return station;
    }

    public Station fetchStationByExtId(int extStationId)
    {
        final String selectionArgs[] = { String.valueOf(extStationId) };
        final String selection = StationSchema.COLUMN_EXTID + " = ?";
        cursor = super.query(StationSchema.TABLE_NAME, StationSchema.COLUMNS,
                             selection, selectionArgs, StationSchema.COLUMN_EXTID);

        Station station = null;
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                station = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return station;
    }

    public List<Station> fetchStationsByType(StationType type)
    {
        final String selectionArgs[] = { String.valueOf(type.value()) };
        final String selection = StationSchema.COLUMN_TYPE + " = ?";
        List<Station> stations = new ArrayList<>();

        cursor = super.query(StationSchema.TABLE_NAME, StationSchema.COLUMNS,
                             selection, selectionArgs, StationSchema.COLUMN_TYPE);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Station station = cursorToEntity(cursor);
                stations.add(station);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return stations;
    }

    public List<Station> fetchStations()
    {
        List<Station> stations = new ArrayList<>();
        cursor = super.query(StationSchema.TABLE_NAME, StationSchema.COLUMNS,
                             null, null, EntitySchema.COLUMN_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Station station = cursorToEntity(cursor);
                stations.add(station);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return stations;
    }

    protected Station cursorToEntity(Cursor cursor)
    {
        Station station = null;
        if (cursor != null) {
            HashMap<String, Integer> values = this.getColumnIndexes(cursor);
            values.put("name", cursor.getColumnIndexOrThrow(StationSchema.COLUMN_NAME));
            values.put("type", cursor.getColumnIndexOrThrow(StationSchema.COLUMN_TYPE));
            values.put("voltageRatio", cursor.getColumnIndexOrThrow(StationSchema.COLUMN_VOLTAGERATIO));
            values.put("sourcePowerlineId", cursor.getColumnIndexOrThrow(StationSchema.COLUMN_SOURCEPOWERID));
            values.put("addrStreet", cursor.getColumnIndexOrThrow(StationSchema.COLUMN_ADDRSTREET));
            values.put("addrTown", cursor.getColumnIndexOrThrow(StationSchema.COLUMN_ADDRTOWN));
            values.put("addrStateId", cursor.getColumnIndexOrThrow(StationSchema.COLUMN_ADDRSTATEID));
            values.put("postalCode", cursor.getColumnIndexOrThrow(StationSchema.COLUMN_POSTALCODE));
            values.put("addrRaw", cursor.getColumnIndexOrThrow(StationSchema.COLUMN_ADDRRAW));

            station = new Station(
                cursor.getInt(values.get("extId")),
                cursor.getString(values.get("code")),
                cursor.getString(values.get("altCode")),
                cursor.getString(values.get("name")),
                StationType.fromInt(cursor.getInt(values.get("type"))),
                Voltage.Ratio.fromInt(cursor.getInt(values.get("voltageRatio"))),
                cursor.getInt(values.get("isPublic")) > 0,
                cursor.getInt(values.get("sourcePowerlineId")),
                cursor.getString(values.get("addrStreet")),
                cursor.getString(values.get("addrTown")),
                cursor.getInt(values.get("addrStateId")),
                cursor.getString(values.get("postalCode")),
                cursor.getString(values.get("addrRaw")),
                this.toDate(cursor.getString(values.get("dateCommissioned"))),
                cursor.getInt(values.get("deleted")) > 0,
                this.toDate(cursor.getString(values.get("lastSynced"))),
                this.toDate(cursor.getString(values.get("dateCreated"))),
                this.toDate(cursor.getString(values.get("lastUpdated")))
            );
        }
        return station;
    }

    private ContentValues getContentValues(Station station)
    {
        ContentValues values = new ContentValues();
        values.put(EntitySchema.COLUMN_ID, station.getId());
        values.put(EntitySchema.COLUMN_EXTID, station.externalId());
        values.put(EntitySchema.COLUMN_DELETED, station.deleted());
        values.put(EntitySchema.COLUMN_DATECREATED, toISODateString(station.dateCreated()));
        values.put(EntitySchema.COLUMN_LASTUPDATED, toISODateString(station.lastUpdated()));
        values.put(NetworkEntitySchema.COLUMN_CODE, station.code());
        values.put(NetworkEntitySchema.COLUMN_ALTCODE, station.alternateCode());
        values.put(NetworkEntitySchema.COLUMN_ISPUBLIC, station.isPublic());
        values.put(NetworkEntitySchema.COLUMN_LASTSYNCED, toISODateString(station.lastSynced()));
        values.put(StationSchema.COLUMN_NAME, station.name());
        values.put(StationSchema.COLUMN_TYPE, station.type().value());
        values.put(StationSchema.COLUMN_VOLTAGERATIO, station.voltageRatio().value());
        values.put(StationSchema.COLUMN_SOURCEPOWERID, station.sourcePowerlineId());
        values.put(StationSchema.COLUMN_ADDRSTREET, station.addressStreet());
        values.put(StationSchema.COLUMN_ADDRTOWN, station.addressTown());
        values.put(StationSchema.COLUMN_POSTALCODE, station.postalCode());
        values.put(StationSchema.COLUMN_ADDRSTATEID, station.addressStateId());
        values.put(StationSchema.COLUMN_ADDRRAW, station.addressRaw());
        return values;
    }
}
