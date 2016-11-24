package ng.kedco.gridix.db.schema;



public abstract class EntitySchema
{
    public static final String COL_ID = "_id";
    public static final String COL_EXTID = "ext_id";
    public static final String COL_DELETED = "deleted";
    public static final String COL_LAST_UPDATED = "last_updated";


    public static String getDml()
    {
        return String.format(
            " , %s     BOOLEAN NOT NULL DEFAULT(0)" +
            " , %s     DATE NULL",
            EntitySchema.COL_DELETED,
            EntitySchema.COL_LAST_UPDATED
        );
    }
}
