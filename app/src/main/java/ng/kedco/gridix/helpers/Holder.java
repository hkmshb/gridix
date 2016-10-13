package ng.kedco.gridix.helpers;

import java.util.ArrayList;

import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.PowerLine;
import ng.kedco.gridix.models.Station;
import ng.kedco.gridix.models.TransmissionStation;

/**
 * Created by shaibujnr on 9/26/16.
 */
public class Holder {
    ArrayList<TransmissionStation> transmissionStations = new ArrayList<TransmissionStation>();
    ArrayList<InjectionStation> injectionStations = new ArrayList<InjectionStation>();
    ArrayList<DistributionSubstation> distributionSubstations = new ArrayList<DistributionSubstation>();
    ArrayList<PowerLine> feeder33s = new ArrayList<PowerLine>();
    ArrayList<PowerLine> feeder11s = new ArrayList<PowerLine>();
    ArrayList<PowerLine> uprisers = new ArrayList<PowerLine>();
    private static Holder instance = new Holder();

    private Holder(){}
    public static Holder getInstance(){return instance;}
    public ArrayList<TransmissionStation> getTransmissionStations(){return transmissionStations;}
    public ArrayList<InjectionStation> getInjectionStations(){return injectionStations;}
    public ArrayList<DistributionSubstation> getDistributionSubstations(){return distributionSubstations;}

    public ArrayList<PowerLine> getFeeder33s() {
        return feeder33s;
    }

    public ArrayList<PowerLine> getFeeder11() {
        return feeder11s;
    }

    public ArrayList<PowerLine> getUprisers() {
        return uprisers;
    }

    public void addStation(Station station){
        if(station instanceof TransmissionStation){
            TransmissionStation a = (TransmissionStation) station;
            transmissionStations.add(0,a);
        }
        else if(station instanceof InjectionStation){
            InjectionStation b = (InjectionStation) station;
            injectionStations.add(0,b);
        }
        else if(station instanceof DistributionSubstation){
            DistributionSubstation c = (DistributionSubstation) station;
            distributionSubstations.add(0,c);
        }
    }
    public void addFeeder33(PowerLine pl){feeder33s.add(pl);}
    public void addFeeder11(PowerLine pl){feeder11s.add(pl);}
    public void addUpriser(PowerLine pl){uprisers.add(pl);}
    public void clearStations(){
        transmissionStations.clear();
        injectionStations.clear();
        distributionSubstations.clear();
    }
    public void clearPowerLines(){
        feeder33s.clear();
        feeder11s.clear();
    }
}
