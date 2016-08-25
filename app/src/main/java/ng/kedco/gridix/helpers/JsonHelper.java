package com.shaibujnr.kedco.helpers;

import android.support.v4.content.res.TypedArrayUtils;

import com.shaibujnr.kedco.categories.Transmission;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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

    
}
