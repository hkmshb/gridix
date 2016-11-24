package ng.kedco.gridix.db.model;


import java.util.Date;

public class Station extends NetworkEntity
{
    private String name;
    private String addrRaw;
    private String addrTown;
    private int addrStateId;
    private String addrStreet;
    private String postalCode;
    private boolean isPublic;
    private StationType type;
    private int sourcePowerlineId;
    private Date dateCommissioned;
    private Voltage.Ratio voltageRatio;

    public Station(int extId, String code, String altCode, String name,
                   StationType type, Voltage.Ratio voltageRatio,
                   int sourcePowerlineId, boolean isPublic)
    {
        this(0, extId, code, altCode, name, type, voltageRatio, sourcePowerlineId,
             isPublic, null, null, null, 0, null, null, false, null);
    }

    public Station(int id, int extId, String code, String altCode, String name,
                   StationType type, Voltage.Ratio voltageRatio, int sourcePowerlineId,
                   boolean isPublic, String addrStreet, String addrTown, String postalCode,
                   int addrStateId, String addrRaw, Date dateCommissioned, boolean deleted,
                   Date lastUpdated)
    {
        super(id, extId, code, altCode, deleted, lastUpdated);
        this.sourcePowerlineId = sourcePowerlineId;
        this.dateCommissioned = dateCommissioned;
        this.voltageRatio = voltageRatio;
        this.addrStateId = addrStateId;
        this.addrStreet = addrStreet;
        this.postalCode = postalCode;
        this.addrTown = addrTown;
        this.isPublic = isPublic;
        this.addrRaw = addrRaw;
        this.type = type;
        this.name = name;
    }

    public String name()
    {
        return this.name;
    }

    public StationType type()
    {
        return this.type;
    }

    public Voltage.Ratio voltageRatio()
    {
        return this.voltageRatio;
    }

    public int sourcePowerlineId()
    {
        return this.sourcePowerlineId;
    }

    public String addrStreet()
    {
        return this.addrStreet;
    }

    public String addrTown()
    {
        return this.addrTown;
    }

    public String postalCode()
    {
        return this.postalCode;
    }

    public int addrStateId()
    {
        return this.addrStateId;
    }

    public String addrRaw()
    {
        return this.addrRaw;
    }

    public boolean isPublic()
    {
        return this.isPublic;
    }

    public Date dateCommissioned()
    {
        return this.dateCommissioned;
    }
}
