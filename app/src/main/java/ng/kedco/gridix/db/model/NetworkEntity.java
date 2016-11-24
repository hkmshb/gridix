package ng.kedco.gridix.db.model;

import java.util.Date;


public abstract class NetworkEntity extends Entity
{
    private String code;
    private String altCode;

    protected NetworkEntity(int id, int extId, String code, String altCode,
                            boolean deleted, Date lastUpdated)
    {
        super(id, extId, deleted, lastUpdated);
        this.altCode = altCode;
        this.code = code;
    }

    public String code()
    {
        return this.code;
    }

    public String altCode()
    {
        return this.altCode;
    }
}
