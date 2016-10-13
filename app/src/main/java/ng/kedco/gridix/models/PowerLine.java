package ng.kedco.gridix.models;

import java.util.Date;

import ng.kedco.gridix.enums.Voltage;

/**
 * Created by shaibujnr on 8/27/16.
 */
public class PowerLine extends NetworkEntity {
    private String name;
    private Voltage voltage;
    private int lineLength;
    private int poleCount;
    private PowerLineType type;
    private int sourceStationId;
    private String dateCommissioned;

    public PowerLine() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Voltage getVoltage() {
        return voltage;
    }

    public void setVoltage(Voltage voltage) {
        this.voltage = voltage;
    }

    public PowerLineType getType() {
        return type;
    }

    public void setType(PowerLineType type) {
        this.type = type;
    }

    public int getSourceStationId() {
        return sourceStationId;
    }

    public void setSourceStationId(int sourceStationId) {
        this.sourceStationId = sourceStationId;
    }

    public String getDateCommissioned() {
        return dateCommissioned;
    }

    public void setDateCommissioned(String dateCommissioned) {
        this.dateCommissioned = dateCommissioned;
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

    public static PowerLineType getTypeFromInt(int typ){
        switch (typ){
            case 1:
                return PowerLineType.FEEDER;
            case 2:
                return PowerLineType.UPRISER;
        }
        return null;
    }
    public static int getIntFromType(PowerLineType plt){
        switch (plt){
            case FEEDER:
                return 1;
            case UPRISER:
                return  2;
        }
        return 0;
    }


    public enum PowerLineType{
        FEEDER,
        UPRISER
    }
}
