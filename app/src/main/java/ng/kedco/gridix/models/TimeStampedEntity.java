package ng.kedco.gridix.models;

/**
 * Created by shaibujnr on 10/19/16.
 */

public abstract class TimeStampedEntity extends BaseEntity {
    private String DateCreated;
    private String LastUpdated;

    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String dateCreated) {
        DateCreated = dateCreated;
    }

    public String getLastUpdated() {
        return LastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        LastUpdated = lastUpdated;
    }
}
