package ng.kedco.gridix.db.model;

import java.util.Date;


public class Station extends NetworkEntity
{
    private String name;
    private String addrRaw;
    private String addrTown;
    private int addrStateId;
    private String addrStreet;
    private boolean isPublic;
    private StationType type;
    private String postalCode;
    private Date dateCommissioned;
    private int sourcePowerlineId;
    private Voltage.Ratio voltageRatio;


    public Station(int extId, String code, String altCode, String name, StationType type,
                   Voltage.Ratio voltageRatio, boolean isPublic, int sourcePowerlineId,
                   String addrStreet, String addrTown, int addrStateId, String postalCode,
                   String addrRaw, Date dateCommissioned, boolean deleted, Date lastSynced,
                   Date dateCreated, Date lastUpdated)
    {
        super(extId, code, altCode, deleted, lastSynced, dateCreated, lastUpdated);
        this.name = name;
        this.type = type;
        this.addrRaw = addrRaw;
        this.addrTown = addrTown;
        this.addrStreet = addrStreet;
        this.postalCode = postalCode;
        this.addrStateId = addrStateId;
        this.isPublic = isPublic;
        this.voltageRatio = voltageRatio;
        this.dateCommissioned = dateCommissioned;
        this.sourcePowerlineId = sourcePowerlineId;
    }

    public String name()
    {
        return name;
    }

    public StationType type()
    {
        return type;
    }

    public Voltage.Ratio voltageRatio()
    {
        return voltageRatio;
    }

    public int sourcePowerlineId()
    {
        return sourcePowerlineId;
    }

    public String addressStreet()
    {
        return addrStreet;
    }

    public String addressTown()
    {
        return addrTown;
    }

    public int addressStateId()
    {
        return this.addrStateId;
    }

    public String addressRaw()
    {
        return this.addrRaw;
    }

    public String postalCode()
    {
        return this.postalCode;
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

