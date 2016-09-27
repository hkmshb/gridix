package ng.kedco.gridix.models;

import java.util.Date;

/**
 * Created by shaibujnr on 9/26/16.
 */
public abstract class TimeStampedEntity extends BaseEntity{
    private String dateCreated;
    private String lastUpdated;

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
