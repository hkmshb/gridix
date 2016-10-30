package ng.kedco.gridix.db.dao;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Date;

import ng.kedco.gridix.db.schema.NetworkEntitySchema;
import ng.kedco.gridix.db.schema.EntitySchema;


public abstract class BaseDao
{
    // fields:
    public SQLiteDatabase db;
    protected static final SimpleDateFormat datefmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public BaseDao(SQLiteDatabase db)
    {
        this.db = db;
    }

    protected abstract <T> T cursorToEntity(Cursor cursor);

    protected HashMap<String, Integer> getColumnIndexes(Cursor cursor)
    {
        HashMap<String, Integer> values = new HashMap<>();
        values.put("extId", cursor.getColumnIndexOrThrow(EntitySchema.COLUMN_EXTID));
        values.put("code", cursor.getColumnIndexOrThrow(NetworkEntitySchema.COLUMN_CODE));
        values.put("altCode", cursor.getColumnIndexOrThrow(NetworkEntitySchema.COLUMN_ALTCODE));
        values.put("isPublic", cursor.getColumnIndexOrThrow(NetworkEntitySchema.COLUMN_ISPUBLIC));
        values.put("lastSynced", cursor.getColumnIndexOrThrow(NetworkEntitySchema.COLUMN_LASTSYNCED));
        values.put("dateCommissioned", cursor.getColumnIndexOrThrow(NetworkEntitySchema.COLUMN_DATECOMMISSIONED));
        values.put("deleted", cursor.getColumnIndexOrThrow(NetworkEntitySchema.COLUMN_DELETED));
        values.put("dateCreated", cursor.getColumnIndexOrThrow(NetworkEntitySchema.COLUMN_DATECREATED));
        values.put("lastUpdated", cursor.getColumnIndexOrThrow(NetworkEntitySchema.COLUMN_LASTUPDATED));
        values.put("lastSynced", cursor.getColumnIndexOrThrow(NetworkEntitySchema.COLUMN_LASTSYNCED));
        return values;
    }

    protected Date toDate(String dateString)
    {
        if (dateString != null) {
            try {
                return datefmt.parse(dateString);
            } catch (ParseException e) {
                // do nothing
            }
        }
        return null;
    }

    protected String toISODateString(Date date)
    {
        if (date == null)
            return null;
        return datefmt.format(date);
    }

    public Cursor query(String tableName, String[] columns, String selection,
                        String[] selectionArgs, String sortOrder)
    {
        return db.query(tableName, columns, selection, selectionArgs,
                        null, null, sortOrder);
    }

    public Cursor query(String tableName, String[] columns, String selection,
                        String[] selectionArgs, String sortOrder, String limit)
    {
        return db.query(tableName, columns, selection, selectionArgs,
                        null, null, sortOrder, limit);
    }

    public Cursor query(String tableName, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy, String limit)
    {
        return db.query(tableName, columns, selection, selectionArgs,
                        groupBy, having, orderBy, limit);
    }

    public long insert(String tableName, ContentValues values)
    {
        return db.insert(tableName, null, values);
    }

    public int delete(String tableName, String selection, String[] selectionArgs)
    {
        return db.delete(tableName, selection, selectionArgs);
    }

    public int update(String tableName, ContentValues values, String selection,
                      String[] selectionArgs)
    {
        return db.update(tableName, values, selection, selectionArgs);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs)
    {
        return db.rawQuery(sql, selectionArgs);
    }
}
