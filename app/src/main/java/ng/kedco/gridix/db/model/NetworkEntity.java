package ng.kedco.gridix.db.model;

import java.util.Date;


public abstract class NetworkEntity extends TimestampedEntity
{
    private String code;
    private String altCode;

    protected NetworkEntity(int extId, String code, String altCode, boolean deleted,
                            Date lastSynced, Date dateCreated, Date lastUpdated)
    {
        super(extId, deleted, lastSynced, dateCreated, lastUpdated);
        this.altCode = altCode;
        this.code = code;
    }

    public String code()
    {
        return this.code;
    }

    public String alternateCode()
    {
        return this.altCode;
    }
}
