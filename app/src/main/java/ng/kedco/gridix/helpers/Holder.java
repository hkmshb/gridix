package ng.kedco.gridix.helpers;

import java.util.ArrayList;

import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.Station;
import ng.kedco.gridix.models.TransmissionStation;

/**
 * Created by shaibujnr on 9/26/16.
 */
public class Holder {
    ArrayList<TransmissionStation> transmissionStations = new ArrayList<TransmissionStation>();
    ArrayList<InjectionStation> injectionStations = new ArrayList<InjectionStation>();
    ArrayList<DistributionSubstation> distributionSubstations = new ArrayList<DistributionSubstation>();
    private static Holder instance = new Holder();

    private Holder(){}
    public static Holder getInstance(){return instance;}
    public ArrayList<TransmissionStation> getTransmissionStations(){return transmissionStations;}
    public ArrayList<InjectionStation> getInjectionStations(){return injectionStations;}
    public ArrayList<DistributionSubstation> getDistributionSubstations(){return distributionSubstations;}
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
    public void clearStations(){
        transmissionStations.clear();
        injectionStations.clear();
        distributionSubstations.clear();
    }
}
