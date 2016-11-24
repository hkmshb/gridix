package ng.kedco.gridix.db.model;


import java.util.Date;

public class Powerline extends NetworkEntity
{
    private String name;
    private int poleCount;
    private int lineLength;
    private Voltage voltage;
    private boolean isPublic;
    private PowerlineType type;
    private int sourceStationId;
    private Date dateCommissioned;

    public Powerline(int extId, String code, String altCode, String name,
                     PowerlineType type, Voltage voltage, int sourceStationId,
                     boolean isPublic)
    {
        this(0, extId, code, altCode, name, type, voltage, sourceStationId,
            isPublic, 0, 0, null, false, null);
    }

    public Powerline(int id, int extId, String code, String altCode, String name,
                     PowerlineType type, Voltage voltage, int sourceStationId,
                     boolean isPublic, int poleCount, int lineLength,
                     Date dateCommissioned, boolean deleted, Date lastUpdated)
    {
        super(id, extId, code, altCode, deleted, lastUpdated);
        this.dateCommissioned = dateCommissioned;
        this.sourceStationId = sourceStationId;
        this.lineLength = lineLength;
        this.poleCount = poleCount;
        this.isPublic = isPublic;
        this.voltage = voltage;
        this.type = type;
        this.name = name;
    }

    public String name()
    {
        return this.name;
    }

    public PowerlineType type()
    {
        return this.type;
    }

    public int poleCount()
    {
        return this.poleCount;
    }

    public int lineLength()
    {
        return this.lineLength;
    }

    public Voltage voltage()
    {
        return this.voltage;
    }

    public boolean isPublic()
    {
        return this.isPublic;
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
