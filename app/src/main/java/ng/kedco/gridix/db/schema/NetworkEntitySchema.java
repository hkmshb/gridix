package ng.kedco.gridix.db.schema;



public abstract class NetworkEntitySchema extends EntitySchema
{
    public static final String COL_CODE = "code";
    public static final String COL_ALTCODE = "alt_code";
    public static final String COL_ISPUBLIC = "is_public";
    public static final String COL_DATE_COMMISSION = "date_commissioned";

    public static String getDml()
    {
        return String.format(
            " , %s     VARCHAR(20) NOT NULL UNIQUE" +
            " , %s     VARCHAR(20)" +
            " , %s     BOOLEAN NOT NULL DEFAULT(1)" +
            " , %s     DATE NULL" +
            EntitySchema.getDml(),
            NetworkEntitySchema.COL_CODE,
            NetworkEntitySchema.COL_ALTCODE,
            NetworkEntitySchema.COL_ISPUBLIC,
            NetworkEntitySchema.COL_DATE_COMMISSION
        );
    }
}
