package ng.kedco.gridix.models;

/**
 * Created by shaibujnr on 10/19/16.
 */

public abstract class NetworkEntity extends TimeStampedEntity {
    private String code;
    private String altCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAltCode() {
        return altCode;
    }

    public void setAltCode(String altCode) {
        this.altCode = altCode;
    }
}
