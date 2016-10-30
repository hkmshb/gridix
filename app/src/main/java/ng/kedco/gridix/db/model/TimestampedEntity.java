package ng.kedco.gridix.db.model;
import java.util.Date;



public abstract class TimestampedEntity extends BaseEntity
{
    private Date dateCreated;
    private Date lastUpdated;

    protected TimestampedEntity(int extId, boolean deleted, Date lastSynced,
                                Date dateCreated, Date lastUpdated)
    {
        super(extId, deleted, lastSynced);
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    public Date dateCreated()
    {
        return this.dateCreated;
    }

    public Date lastUpdated()
    {
        return this.lastUpdated;
    }
}
