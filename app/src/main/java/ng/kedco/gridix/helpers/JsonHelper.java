package ng.kedco.gridix.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.Log;
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
import ng.kedco.gridix.enums.Status;
import ng.kedco.gridix.enums.Voltage;
import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.PowerLine;
import ng.kedco.gridix.models.Station;
import ng.kedco.gridix.models.TransmissionStation;

/**
 * Created by shaibujnr on 8/18/16.
 */

public class JsonHelper {
    Holder holder = Holder.getInstance();
    Context callingContext;

    public JsonHelper(Context c){
        this.callingContext = c;
    }

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
    private String fetchPowerLines(PowerLine.PowerLineType plType,Voltage v){
        String result = "";
        int responseCode;
        int voltage = Voltage.getIntFromVoltage(v);
        int typ = PowerLine.getIntFromType(plType);
        String plurl = GridixApplication.POWERLINES_PATH+"/?type="+typ+"&voltage="+voltage;
        try{
            URL url = new URL(plurl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                StringBuffer sb = new StringBuffer();
                InputStream in = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String inputLine = "";
                while((inputLine=br.readLine())!=null){
                    sb.append(inputLine);
                }
                result = sb.toString();
            }else{
                displayToast("Error in Connection");
            }

        }catch(Exception e){

        }
        return result;

    }

    private String fetchStations(Station.StationType stationType){
        String surl = GridixApplication.STATIONS_PATH;
        surl = surl+"/?type="+Station.getIntFromStationType(stationType);
        String result = "";
        int responseCode;
        try{
            URL url = new URL(surl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                StringBuffer sb = new StringBuffer();
                InputStream in = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String inputLine = "";
                while((inputLine=br.readLine())!=null){
                    sb.append(inputLine);
                }
                result = sb.toString();
            }else{
                displayToast("Error in connection");
            }
        }catch(Exception e){}

        return result;
    }


    public Status updatePowerLinesList(PowerLine.PowerLineType plType, Voltage volt){
        holder.clearPowerLines();
        JSONArray powerlinesArray=null;
        try{
            powerlinesArray = new JSONArray(fetchPowerLines(plType,volt));
            if(powerlinesArray!=null){
                for(int i=0; i<powerlinesArray.length();i++){
                    JSONObject powerLineObject = powerlinesArray.getJSONObject(i);
                    int voltage = powerLineObject.optInt("voltage");
                    Log.e("tagpl","voltage "+voltage);
                    int lineLength = powerLineObject.optInt("lineLength");
                    Log.e("tagpl","linelength: "+lineLength);
                    int poleCount = powerLineObject.optInt("poleCount");
                    Log.e("tagpl","polecount: "+poleCount);
                    int type = powerLineObject.optInt("type");
                    Log.e("tagpl","type: "+type);
                    int sourceStationId = powerLineObject.optInt("sourceStationId");
                    Log.e("tagpl","sourcestationid: "+sourceStationId);
                    int id = powerLineObject.optInt("id");
                    Log.e("tagpl","id: "+id);
                    String code = powerLineObject.optString("code");
                    Log.e("tagpl","code: "+code);
                    String altCode = powerLineObject.optString("altCode");
                    Log.e("tagpl","altCode: "+altCode);
                    String name = powerLineObject.optString("name");
                    Log.e("tagpl","name: "+name);
                    String dateCreated = powerLineObject.optString("dateCreated");
                    Log.e("tagpl","dateCreated: "+dateCreated);
                    String lastUpdated = powerLineObject.optString("lastUpdated");
                    String dateCommissioned = powerLineObject.optString("dateCommissioned");

                    PowerLine pl = new PowerLine();
                    pl.setName(name);
                    pl.setLastUpdated(lastUpdated);
                    pl.setAltCode(altCode);
                    pl.setCode(code);
                    pl.setDateCreated(dateCreated);
                    pl.setDateCommissioned(dateCommissioned);
                    pl.setSourceStationId(sourceStationId);
                    pl.setType(PowerLine.getTypeFromInt(type));
                    pl.setVoltage(Voltage.getVoltageFromInt(voltage));
                    pl.setId(id);
                    pl.setLineLength(lineLength);
                    pl.setPoleCount(poleCount);
                    if(plType == PowerLine.PowerLineType.FEEDER && volt == Voltage.MVOLTH){
                        holder.addFeeder33(pl);
                    }
                    else if(plType == PowerLine.PowerLineType.FEEDER && volt == Voltage.MVOLTL){
                        holder.addFeeder11(pl);
                    }
                    else if(plType== PowerLine.PowerLineType.UPRISER){
                        holder.addUpriser(pl);
                    }
                }
            }

        }catch(Exception e){
            return Status.FAILED;
        }
        return Status.SUCESS;

    }
    public Status updateStationsList(Station.StationType st){
        holder.clearStations();
        JSONArray stationsArray=null;
        try {
            stationsArray = new JSONArray(fetchStations(st));
            if(stationsArray!=null){
                for(int i=0;i<stationsArray.length();i++){
                    JSONObject stationObject = stationsArray.getJSONObject(i);
                    int vratio = stationObject.optInt("voltageRatio");
                    Log.e("tag","voltage ratio: "+vratio);
                    int sourcePowerLineId = stationObject.optInt("sourcePowerLineId");
                    Log.e("tag","source powerline id: "+sourcePowerLineId);
                    int type = stationObject.optInt("type");
                    Log.e("tag","type "+type);
                    boolean isPublic = stationObject.optBoolean("isPublic");
                    Log.e("tag","ispublic: "+isPublic);
                    String addressStreet = stationObject.optString("addressString");
                    String addressTown = stationObject.optString("addressTown");
                    String postalCode = stationObject.optString("postalCode");
                    int addressStateId = stationObject.optInt("addressStateId");
                    String addressRaw = stationObject.optString("addressRaw");
                    int id = stationObject.optInt("id");
                    Log.e("tag","id: "+id);
                    String code = stationObject.optString("code");
                    Log.e("tag","code :"+code);
                    String altCode = stationObject.optString("altCode");
                    Log.e("tag","altCode :"+altCode);
                    String name = stationObject.optString("name");
                    Log.e("tag","name :"+name);
                    String dateCreated = stationObject.optString("dateCreated");
                    Log.e("tag","dateCreated: "+dateCreated);
                    String lastUpdated = stationObject.optString("lastUpdated");
                    Log.e("tag","lastUpdated: "+lastUpdated);
                    String dateCommissioned = stationObject.optString("dateCommissioned");
                    Log.e("tag","dateCommissioned: "+dateCommissioned);


                    Station station = Station.getStationfromType(type);
                    if(station != null){
                        station.setName(name);
                        Log.e("tagg","sname: "+station.getName());
                        station.setId(id);
                        Log.e("tagg","sid: "+station.getId());
                        station.setAddressRaw(addressRaw);
                        Log.e("tagg","saddressRaw: "+station.getName());
                        station.setAddressState(addressStateId);
                        Log.e("tagg","saddressStateId "+station.getAddressState());
                        station.setAddressStreet(addressStreet);
                        station.setAddressTown(addressTown);
                        station.setPostalCode(postalCode);
                        station.setPublic(isPublic);
                        Log.e("tagg","sispublic: "+station.isPublic());
                        station.setSourcePowerLineId(sourcePowerLineId);
                        station.setCode(code);
                        Log.e("tagg","scode: "+station.getCode());
                        station.setAltCode(altCode);
                        Log.e("tagg","saltCode: "+station.getAltCode());
                        station.setDateCommissioned(dateCommissioned);
                        station.setDateCreated(dateCreated);
                        station.setLastUpdated(lastUpdated);
                        station.setVoltageRatio(getVoltageRatioFromInt(vratio));
                        holder.addStation(station);
                    }


                }
            }

        } catch (JSONException e) {
            return Status.FAILED;
        }
        return Status.SUCESS;

    }




    private Station.VoltageRatio getVoltageRatioFromInt(int vr){
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

    private void displayToast(String msg){
        Toast.makeText(callingContext,msg,Toast.LENGTH_SHORT).show();
    }






    
}
