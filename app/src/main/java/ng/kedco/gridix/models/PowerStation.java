package ng.kedco.gridix.models;

/**
 * Created by shaibujnr on 8/28/16.
 */
public abstract class PowerStation extends Station {
    private String radionNumber;
    private String mobileNumber;


    public String getRadionNumber() {
        return radionNumber;
    }

    public void setRadionNumber(String radionNumber) {
        this.radionNumber = radionNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
