package ng.kedco.gridix.models;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.Date;

/**
 * Created by shaibujnr on 8/27/16.
 */
public abstract class Station extends NetworkEntity{
    private String name;
    private VoltageRatio voltageRatio;
    private int sourcePowerLineId;
    private String addressStreet;
    private String addressTown;
    private String postalCode;
    private int addressStateId;
    private String addressRaw;
    private String contactDetails;
    private boolean isPublic;
    private String dateCommissioned;



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public VoltageRatio getVoltageRatio() {
        return voltageRatio;
    }

    public void setVoltageRatio(VoltageRatio voltageRatio) {
        this.voltageRatio = voltageRatio;
    }

    public int getSourcePowerLineId() {
        return sourcePowerLineId;
    }

    public void setSourcePowerLineId(int sourcePowerLineId) {
        this.sourcePowerLineId = sourcePowerLineId;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        addressStreet = addressStreet;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        addressTown = addressTown;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        postalCode = postalCode;
    }

    public int getAddressState() {
        return addressStateId;
    }

    public void setAddressState(int addressState) {
        addressStateId = addressState;
    }

    public String getAddressRaw() {
        return addressRaw;
    }

    public void setAddressRaw(String addressRaw) {
        addressRaw = addressRaw;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        contactDetails = contactDetails;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }


    public String getDateCommissioned() {
        return dateCommissioned;
    }


    public void setDateCommissioned(String dateCommissioned) {
        dateCommissioned = dateCommissioned;
    }

    public enum VoltageRatio{
        MVOLTL_LVOLT, //1
        MVOLTH_LVOLT,  //2
        MVOLTH_MVOLTL,  //3
        HVOLTL_MVOLTL,  //4
        HVOLTL_MVOLTH,  //5
        HVOLTH_HVOLTL  //6
    }

}
