package ng.kedco.gridix.db.model;

import java.util.Date;


public class Powerline extends NetworkEntity
{
    private String name;
    private int poleCount;
    private int lineLength;
    private boolean isPublic;
    private Voltage voltage;
    private PowerlineType type;
    private int sourceStationId;
    private Date dateCommissioned;


    public Powerline(int extId, String code, String altCode, String name, PowerlineType type,
                     Voltage voltage, int sourceStationId, boolean isPublic, int poleCount,
                     int lineLength, Date dateCommissioned, boolean deleted, Date lastSynced,
                     Date dateCreated, Date lastUpdated)
    {
        super(extId, code, altCode, deleted, lastSynced, dateCreated, lastUpdated);
        this.name = name;
        this.type = type;
        this.voltage = voltage;
        this.isPublic = isPublic;
        this.poleCount = poleCount;
        this.lineLength = lineLength;
        this.sourceStationId = sourceStationId;
        this.dateCommissioned = dateCommissioned;
    }

    public String name()
    {
        return this.name;
    }

    public PowerlineType type()
    {
        return this.type;
    }

    public Voltage voltage()
    {
        return this.voltage;
    }

    public boolean isPublic()
    {
        return this.isPublic;
    }

    public int poleCount()
    {
        return this.poleCount;
    }
    
    public int lineLength()
    {
        return this.lineLength;
    }

    public int sourceStationId()
    {
        return this.sourceStationId;
    }

    public Date dateCommissioned()
    {
        return this.dateCommissioned;
    }

}