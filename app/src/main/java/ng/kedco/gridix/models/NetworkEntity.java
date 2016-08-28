package ng.kedco.gridix.models;

/**
 * Created by shaibujnr on 8/27/16.
 */
public abstract class NetworkEntity extends Entity {
    private String altCode;

    public String getAltCode() {
        return altCode;
    }

    public void setAltCode(String altCode) {
        this.altCode = altCode;
    }
}
