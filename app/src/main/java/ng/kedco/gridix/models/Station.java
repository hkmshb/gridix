package ng.kedco.gridix.models;

import java.util.Date;

/**
 * Created by shaibujnr on 8/27/16.
 */
public abstract class Station extends NetworkEntity{
    private String name;
    private String location;
    private String voltageRatio;
    private String gpsCord;
    private String geomentricBoundary;
    private String sourceFeeder;
    private Date dateCommissioned;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVoltageRatio() {
        return voltageRatio;
    }

    public void setVoltageRatio(String voltageRatio) {
        this.voltageRatio = voltageRatio;
    }

    public String getGpsCord() {
        return gpsCord;
    }

    public void setGpsCord(String gpsCord) {
        this.gpsCord = gpsCord;
    }

    public String getGeomentricBoundary() {
        return geomentricBoundary;
    }

    public void setGeomentricBoundary(String geomentricBoundary) {
        this.geomentricBoundary = geomentricBoundary;
    }

    public String getSourceFeeder() {
        return sourceFeeder;
    }

    public void setSourceFeeder(String sourceFeeder) {
        this.sourceFeeder = sourceFeeder;
    }

    public Date getDateCommissioned() {
        return dateCommissioned;
    }

    public void setDateCommissioned(Date dateCommissioned) {
        this.dateCommissioned = dateCommissioned;
    }
}
