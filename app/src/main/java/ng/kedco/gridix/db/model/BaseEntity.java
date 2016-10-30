package ng.kedco.gridix.db.model;

import java.util.Date;



public abstract class BaseEntity
{
    private int id;
    private int extId;
    private boolean deleted;
    private Date lastSynced;

    protected BaseEntity(int extId, boolean deleted, Date lastSynced)
    {
        this.extId = extId;
        this.deleted = deleted;
        this.lastSynced = lastSynced;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int externalId()
    {
        return this.extId;
    }

    public boolean deleted()
    {
        return this.deleted;
    }

    public Date lastSynced()
    {
        return this.lastSynced;
    }
}
