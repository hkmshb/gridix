package ng.kedco.gridix.db.model;

import java.util.Date;


public abstract class Entity
{
    private int id;
    private boolean deleted;
    private Date lastUpdated;

    protected Entity(int id, boolean deleted, Date lastUpdated)
    {
        this.id = id;
        this.deleted = deleted;
        this.lastUpdated = lastUpdated;
    }

    public int id()
    {
        return this.id;
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
