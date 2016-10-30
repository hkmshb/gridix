package ng.kedco.gridix.db.schema;


public abstract class EntitySchema
{
    // fields:
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EXTID = "ext_id";
    public static final String COLUMN_DELETED = "deleted";
    public static final String COLUMN_DATECREATED = "date_created";
    public static final String COLUMN_LASTUPDATED = "last_updated";

    public static String getPartialDml()
    {
        return String.format(
            " , %s     INTEGER NOT NULL" +
            " , %s     BOOLEAN NOT NULL DEFAULT(0)" +
            " , %s     DATE NOT NULL DEFAULT(DATE('now'))" +
            " , %s     DATE NULL",
            EntitySchema.COLUMN_EXTID,
            EntitySchema.COLUMN_DELETED,
            EntitySchema.COLUMN_DATECREATED,
            EntitySchema.COLUMN_LASTUPDATED
        );
    }
}
