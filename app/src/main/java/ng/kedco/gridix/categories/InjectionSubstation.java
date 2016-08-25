package com.shaibujnr.kedco.categories;

import java.util.ArrayList;

/**
 * Created by shaibujnr on 8/17/16.
 */

public class InjectionSubstation {
    private ArrayList<Distribution> distributions;
    private String name;
    private Transmission transmissionSource;

    public InjectionSubstation(String name,ArrayList<Distribution> distributions,Transmission transmissionSource) {
        this.distributions = distributions;
        this.name = name;
        this.transmissionSource = transmissionSource;
    }



    public InjectionSubstation(String name, Transmission tSource) {
        this.transmissionSource = tSource;
        this.name = name;
    }

    public ArrayList<Distribution> getDistributions() {
        return distributions;
    }

    public void setDistributions(ArrayList<Distribution> distributions) {
        this.distributions = distributions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Transmission getTransmissionSource() {
        return transmissionSource;
    }

    public void setTransmissionSource(Transmission transmissionSource) {
        this.transmissionSource = transmissionSource;
    }
}
