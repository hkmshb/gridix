package ng.kedco.gridix.models;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shaibujnr on 8/27/16.
 */
public abstract class Station extends NetworkEntity implements Serializable{
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
        return this.voltageRatio;
    }

    public void setVoltageRatio(VoltageRatio voltageRatio) {
        this.voltageRatio = voltageRatio;
    }

    public int getSourcePowerLineId() {
        return this.sourcePowerLineId;
    }

    public void setSourcePowerLineId(int sourcePowerLineId) {
        this.sourcePowerLineId = sourcePowerLineId;
    }

    public String getAddressStreet() {
        return this.addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressTown() {
        return this.addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getAddressState() {
        return this.addressStateId;
    }

    public void setAddressState(int addressState) {
        this.addressStateId = addressState;
    }

    public String getAddressRaw() {
        return this.addressRaw;
    }

    public void setAddressRaw(String addressRaw) {
        this.addressRaw = addressRaw;
    }

    public String getContactDetails() {
        return this.contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public void setPublic(boolean aPublic) {
        this.isPublic = aPublic;
    }


    public String getDateCommissioned() {
        return this.dateCommissioned;
    }


    public void setDateCommissioned(String dateCommissioned) {
        this.dateCommissioned = dateCommissioned;
    }
    public static StationType getStationTypefromClass(Class typ){
        if(typ == TransmissionStation.class){
            return StationType.TRANSMISSION;
        }
        else if(typ == InjectionStation.class){
            return StationType.INJECTION;
        }
        else if(typ == DistributionSubstation.class){
            return StationType.DISTRIBUTION;
        }
       return null;
    }
    public static StationType getStationTypefromInt(int type){
        switch (type){
            case 1:
                return StationType.TRANSMISSION;
            case 2:
                return StationType.INJECTION;
            case 3:
                return StationType.DISTRIBUTION;
        }
        return null;
    }
    public static int getIntFromStationType(StationType st){
        switch (st){
            case TRANSMISSION:
                return 1;
            case INJECTION:
                return  2;
            case DISTRIBUTION:
                return 3;

        }
        return 0;
    }
    public static Station getStationfromType(int typ){
        switch (typ){
            case 1:
                return new TransmissionStation();
            case 2:
                return new InjectionStation();
            case 3:
                return new DistributionSubstation();
        }
        return null;
    }

    public enum VoltageRatio{
        MVOLTL_LVOLT, //1
        MVOLTH_LVOLT,  //2
        MVOLTH_MVOLTL,  //3
        HVOLTL_MVOLTL,  //4
        HVOLTL_MVOLTH,  //5
        HVOLTH_HVOLTL  //6
        ;

        @Override
        public String toString() {
            String result = super.toString();
            switch(result){
                case "MVOLTL_LVOLT":
                    return "11kv/415v";
                case "MVOLTH_LVOLT":
                    return "33kv/415v";
                case "MVOLTH_MVOLTL":
                    return "33kv/11kv";
                case "HVOLTL_MVOLTL":
                    return "132kv/11kv";
                case "HVOLTL_MVOLTH":
                    return "132kv/33kv";
                case "HVOLTH_HVOLTL":
                    return "330kv/132kv";
            }
            return result;
        }
    }

    public enum StationType{
        TRANSMISSION,
        INJECTION,
        DISTRIBUTION
    }

}
