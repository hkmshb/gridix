package ng.kedco.gridix.models;

import ng.kedco.gridix.enums.VoltageRatio;

/**
 * Created by shaibujnr on 10/19/16.
 */

public class Station extends NetworkEntity {
    private String name;
    private VoltageRatio voltageRatio;
    private int sourcePowerLineId;
    private String addressStreet;
    private String addressTown;
    private String addressState;
    private String addressRaw;
    private String postalCode;
    private boolean isPublic;
    private String contactDetails;
    private StationType type;

    public Station(){

    }

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
        this.addressStreet = addressStreet;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressRaw() {
        return addressRaw;
    }

    public void setAddressRaw(String addressRaw) {
        this.addressRaw = addressRaw;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public StationType getType() {
        return type;
    }

    public void setType(StationType type) {
        this.type = type;
    }

    public static int convertTypeToInt(StationType stationType){
        switch (stationType){
            case TRANSMISSION:
                return 1;
            case INJECTION_SUBSTATION:
                return 2;
            case DISTRIBUTION:
                return 3;
        }
        return 0;
    }

    public static StationType convertIntToType(int val){
        switch(val){
            case 1:
                return StationType.TRANSMISSION;
            case 2:
                return StationType.INJECTION_SUBSTATION;
            case 3:
                return StationType.DISTRIBUTION;
        }
        return null;
    }

    public enum StationType{
        TRANSMISSION,
        INJECTION_SUBSTATION,
        DISTRIBUTION
    }



}

