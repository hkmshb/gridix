package ng.kedco.gridix.db.model;

import java.util.Date;


public abstract class Entity
{
    private int id;
    private int extId;
    private boolean deleted;
    private Date lastUpdated;

    protected Entity(int id, int extId, boolean deleted, Date lastUpdated)
    {
        this.id = id;
        this.extId = extId;
        this.deleted = deleted;
        this.lastUpdated = lastUpdated;
    }

    public int id()
    {
        return this.id;
    }

    public int externalId()
    {
        return this.extId;
    }

    public boolean deleted()
    {
        return this.deleted;
    }

    public Date lastUpdated()
    {
        return this.lastUpdated;
    }
}
