package ng.kedco.gridix.db.dao;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ng.kedco.gridix.db.model.Entity;
import ng.kedco.gridix.db.model.NetworkEntity;
import ng.kedco.gridix.db.schema.EntitySchema;
import ng.kedco.gridix.db.schema.NetworkEntitySchema;

public abstract class BaseDao
{
    protected SQLiteDatabase db;
    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public BaseDao(SQLiteDatabase db)
    {
        if (db == null)
            throw new IllegalArgumentException("db");
        this.db = db;
    }

    public long insert(String tableName, ContentValues values)
    {
        return db.insert(tableName, null, values);
    }

    public Cursor query(String tableName, String[] columns, String selection,
                        String[] selectionArgs, String orderBy)
    {
        return db.query(tableName, columns, selection, selectionArgs,
                        null, null, orderBy);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs)
    {
        return db.rawQuery(sql, selectionArgs);
    }

    protected void fillValues(ContentValues values, Entity entity)
    {
        values.put(EntitySchema.COL_EXTID, entity.externalId());
        values.put(EntitySchema.COL_DELETED, entity.deleted());
        values.put(EntitySchema.COL_LAST_UPDATED, toISODateString(entity.lastUpdated()));
    }

    protected void fillValues(ContentValues values, NetworkEntity entity)
    {
        values.put(NetworkEntitySchema.COL_CODE, entity.code());
        values.put(NetworkEntitySchema.COL_ALTCODE, entity.altCode());
        fillValues(values, (Entity)entity);
    }

    protected Date fromISODateString(String dateString)
    {
        if (dateString != null) {
            try {
                return dateFormat.parse(dateString);
            } catch (ParseException ex) {
                // do nothing
            }
        }
        return null;
    }

    protected String toISODateString(Date value)
    {
        if (value == null)
            return null;
        return dateFormat.format(value);
    }
}
