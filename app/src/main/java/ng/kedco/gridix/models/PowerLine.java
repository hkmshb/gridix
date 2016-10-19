package ng.kedco.gridix.models;

import ng.kedco.gridix.enums.Voltage;

/**
 * Created by shaibujnr on 10/19/16.
 */

public class PowerLine extends NetworkEntity {
    private String name;
    private int sourceStationId;
    private int lineLength;
    private int poleCount;
    private Voltage voltage;

    public PowerLine(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSourceStationId() {
        return sourceStationId;
    }

    public void setSourceStationId(int sourceStationId) {
        this.sourceStationId = sourceStationId;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public int getPoleCount() {
        return poleCount;
    }

    public void setPoleCount(int poleCount) {
        this.poleCount = poleCount;
    }

    public Voltage getVoltage() {
        return voltage;
    }

    public void setVoltage(Voltage voltage) {
        this.voltage = voltage;
    }

    public enum PowerLineType{
        FEEDER,
        UPRISER
    }

    public static int convertTypeToInt(PowerLineType powerLineType){
        switch(powerLineType){
            case FEEDER:
                return 1;
            case UPRISER:
                return 2;
        }
        return 0;
    }

    public static PowerLineType convertIntToType(int val){
        switch(val){
            case 1:
                return PowerLineType.FEEDER;
            case 2:
                return  PowerLineType.UPRISER;
        }
        return null;
    }
}
