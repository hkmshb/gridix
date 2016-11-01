package ng.kedco.gridix.db.model;


import java.util.Date;

public class Station extends NetworkEntity
{
    private String name;
    private boolean isPublic;
    private Date dateCommissioned;

    public Station(String code, String altCode, String name)
    {
        this(0, code, altCode, name, true, null, false, null);
    }

    public Station(int id, String code, String altCode, String name, boolean isPublic,
                   Date dateCommissioned, boolean deleted, Date lastUpdated)
    {
        super(id, code, altCode, deleted, lastUpdated);
        this.dateCommissioned = dateCommissioned;
        this.isPublic = isPublic;
        this.name = name;
    }

    public String name()
    {
        return this.name;
    }

    public boolean isPublic()
    {
        return this.isPublic;
    }

    public Date getDateCommissioned()
    {
        return this.dateCommissioned;
    }
}
