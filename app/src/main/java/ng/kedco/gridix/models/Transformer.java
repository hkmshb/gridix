package ng.kedco.gridix.models;

import java.security.InvalidParameterException;



/**
 * Created by shaibujnr on 8/28/16.
 * Transformer voltage ration can be set the follwing way;
 *     Transformer a = new Transformer(120,Transformer.VR330_132,"Power Transformer"); //creating the transformer object
 *
 *
 */
public class Transformer extends StationEquipment{
    public static final String VR330_132 = "330/132";
    public static final String VR132_33 = "132/33";
    public static final String VR132_11 = "132/11";
    public static final String VR33_11 = "33/11";

    private int capacity;
    private String voltageRatio;
    private String type;
    private String[] standardVoltageRatios = {VR330_132,VR132_33,VR132_11,VR33_11};


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

    public void setVoltageRatio(String voltRatio){
        this.voltageRatio = voltRatio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private boolean isValidVoltageRation(String vr){
        for(String voltR: standardVoltageRatios){
            if(vr.equals(voltR)){
                return true;
            }
        }
        return false;
    }
}
