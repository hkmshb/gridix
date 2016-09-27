package ng.kedco.gridix.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.res.TypedArrayUtils;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import ng.kedco.gridix.GridixApplication;
import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.Station;
import ng.kedco.gridix.models.TransmissionStation;

/**
 * Created by shaibujnr on 8/18/16.
 */

public class JsonHelper {

    private String returnJsonFileAsString(String filename){
        //reads a json file and returns it as string
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private String fetchStations(){
        String surl = GridixApplication.STATIONS_PATH;
        String result="";
        int responseCode;
        String responseMessage;
        try{
            URL url = new URL(surl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            responseCode = con.getResponseCode();
            responseMessage = con.getResponseMessage();


            switch (responseCode){
                case HttpURLConnection.HTTP_OK:
                    StringBuffer sb = new StringBuffer();
                    InputStream in = new BufferedInputStream(con.getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String inputLine = "";
                    while((inputLine=br.readLine())!=null){
                        sb.append(inputLine);
                    }
                    result = sb.toString();


            }


        }catch(Exception e){

        }
        return result;

    }
    public void updateStationsList(){
        Holder holder = Holder.getInstance();
        try {
            JSONArray stationsArray = new JSONArray(fetchStations());
            for(int i=0;i<stationsArray.length();i++){
                JSONObject stationObject = stationsArray.getJSONObject(i);
                int vratio = stationObject.optInt("voltageRatio");
                int sourcePowerLineId = stationObject.optInt("sourcePowerLineId");
                int type = stationObject.optInt("type");
                boolean isPublic = stationObject.optBoolean("isPublic");
                String addressStreet = stationObject.optString("addressString");
                String addressTown = stationObject.optString("addressTown");
                String postalCode = stationObject.optString("postalCode");
                int addressStateId = stationObject.optInt("addressStateId");
                String addressRaw = stationObject.optString("addressRaw");
                int id = stationObject.optInt("id");
                String code = stationObject.optString("code");
                String altCode = stationObject.optString("altCode");
                String name = stationObject.optString("name");
                String dateCreated = stationObject.optString("dateCreated");
                String lastUpdated = stationObject.optString("lastUpdated");
                String dateCommissioned = stationObject.optString("dateCommissioned");


                Station station = getStationfromType(type);
                station.setName(name);
                station.setId(id);
                station.setAddressRaw(addressRaw);
                station.setAddressState(addressStateId);
                station.setAddressStreet(addressStreet);
                station.setAddressTown(addressTown);
                station.setPostalCode(postalCode);
                station.setPublic(isPublic);
                station.setSourcePowerLineId(sourcePowerLineId);
                station.setCode(code);
                station.setAltCode(altCode);
                station.setDateCommissioned(dateCommissioned);
                station.setDateCreated(dateCreated);
                station.setLastUpdated(lastUpdated);
                station.setVoltageRatio(getVoltageRationFromInt(vratio));
                holder.clearStations();
                holder.addStation(station);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private Station getStationfromType(int typ){
        switch (typ){
            case 1:
                return new TransmissionStation();
            case 2:
                return new InjectionStation();
            case 3:
                return new DistributionSubstation();
        }
        return null;
    }

    private Station.VoltageRatio getVoltageRationFromInt(int vr){
        switch (vr){
            case 1:
                return Station.VoltageRatio.MVOLTL_LVOLT;
            case 2:
                return Station.VoltageRatio.MVOLTH_LVOLT;
            case 3:
                return Station.VoltageRatio.MVOLTH_MVOLTL;
            case 4:
                return Station.VoltageRatio.HVOLTL_MVOLTL;
            case 5:
                return Station.VoltageRatio.HVOLTL_MVOLTH;
            case 6:
                return Station.VoltageRatio.HVOLTH_HVOLTL;
        }
        return null;
    }




    
}
