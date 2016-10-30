package ng.kedco.gridix.db.schema;


public abstract class NetworkEntitySchema extends EntitySchema
{
    // fields
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_ALTCODE = "alt_code";
    public static final String COLUMN_ISPUBLIC = "is_public";
    public static final String COLUMN_LASTSYNCED = "last_synced";
    public static final String COLUMN_DATECOMMISSIONED = "date_commissioned";

    public static String getPartialDml()
    {
        return String.format(
                " , %s     VARCHAR(20) NOT NULL UNIQUE" +
                " , %s     VARCHAR(20) UNIQUE" +
                " , %s     BOOLEAN NOT NULL DEFAULT(1)" +
                " , %s     DATE NULL" +
                " , %s     DATE NULL" +
                EntitySchema.getPartialDml(),
                NetworkEntitySchema.COLUMN_CODE,
                NetworkEntitySchema.COLUMN_ALTCODE,
                NetworkEntitySchema.COLUMN_ISPUBLIC,
                NetworkEntitySchema.COLUMN_LASTSYNCED,
                NetworkEntitySchema.COLUMN_DATECOMMISSIONED
        );
    }

}
