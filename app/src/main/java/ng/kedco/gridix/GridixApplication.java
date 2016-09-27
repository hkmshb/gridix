package ng.kedco.gridix;

import android.app.Application;

import java.util.ArrayList;

import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.Station;
import ng.kedco.gridix.models.TransmissionStation;

/**
 * Created by shaibujnr on 9/22/16.
 */
public class GridixApplication extends Application {
    public static final String BASE_URL = "http://utilitrak.hazeltek.com";
    public static final String API_PATH = BASE_URL+"/api/v1/";
    public static final String STATIONS_PATH = API_PATH+"stations";
    public static final String POWERLINES_PATH = API_PATH+"powerlines";

    public static ArrayList<TransmissionStation> transmissionStations;
    public static ArrayList<InjectionStation> injectionStations;
    public static ArrayList<DistributionSubstation> distributionSubstations;
    public static ArrayList<Station> stations;
}
