package ng.kedco.gridix.models;

/**
 * Created by shaibujnr on 8/28/16.
 */
public class Transformer extends StationEquipment{
    private int capacity;
    private String voltageRatio;
    private String type;

    public Transformer(int caps,String vr,String typ){
        this.capacity = caps;
        this.voltageRatio = vr;
        this.type = typ;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getVoltageRatio() {
        return voltageRatio;
    }

    public void setVoltageRatio(String voltageRatio) {
        this.voltageRatio = voltageRatio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
