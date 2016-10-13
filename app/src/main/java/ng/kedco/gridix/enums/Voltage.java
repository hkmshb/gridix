package ng.kedco.gridix.enums;

/**
 * Created by shaibujnr on 10/6/16.
 */
public enum Voltage {
    LVOLT,
    MVOLTL,
    MVOLTH,
    HVOLTL,
    HVOLTH;

    public static Voltage getVoltageFromInt(int typ){
        switch(typ){
            case 1:
                return Voltage.LVOLT;
            case 2:
                return Voltage.MVOLTL;
            case 3:
                return  Voltage.MVOLTH;
            case 4:
                return Voltage.HVOLTL;
            case 5:
                return  Voltage.HVOLTH;
        }
        return null;
    }

    public static int getIntFromVoltage(Voltage vl){
        switch (vl){
            case LVOLT:
                return 1;
            case MVOLTL:
                return 2;
            case MVOLTH:
                return 3;
            case HVOLTL:
                return 4;
            case HVOLTH:
                return 5;
        }
        return 0;
    }
}
