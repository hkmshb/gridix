package ng.kedco.gridix.models;

import java.util.Date;

/**
 * Created by shaibujnr on 8/27/16.
 */
public class PowerLine extends NetworkEntity {
    private String name;
    private int voltage;
    private String type;
    private String sourceStation;
    private Date dateCommissioned;

    public PowerLine(String name, int voltage, String type, String sourceStation, Date dateCommissioned) {
        this.name = name;
        this.voltage = voltage;
        this.type = type;
        this.sourceStation = sourceStation;
        this.dateCommissioned = dateCommissioned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceStation() {
        return sourceStation;
    }

    public void setSourceStation(String sourceStation) {
        this.sourceStation = sourceStation;
    }

    public Date getDateCommissioned() {
        return dateCommissioned;
    }

    public void setDateCommissioned(Date dateCommissioned) {
        this.dateCommissioned = dateCommissioned;
    }
}
