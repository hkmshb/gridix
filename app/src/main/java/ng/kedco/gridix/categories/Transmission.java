package com.shaibujnr.kedco.categories;

import java.util.ArrayList;

/**
 * Created by shaibujnr on 8/17/16.
 * Defines the class of a transmission station
 */

public class Transmission {
    private ArrayList<InjectionSubstation> injectionSubstations;
    private String name;
    public Transmission(String nam,ArrayList<InjectionSubstation> injSub){
        this.name = nam;
        this.injectionSubstations = injSub;
    }
    public Transmission(String nam){
        this.name = nam;
    }

    public ArrayList<InjectionSubstation> getInjectionSubstations() {
        return injectionSubstations;
    }

    public void setInjectionSubstations(ArrayList<InjectionSubstation> injectionSubstations) {
        this.injectionSubstations = injectionSubstations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
