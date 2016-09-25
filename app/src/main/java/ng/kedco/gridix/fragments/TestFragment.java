package ng.kedco.gridix.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ng.kedco.gridix.GridixApplication;
import ng.kedco.gridix.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {
    TextView label;


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_test, container, false);
        label = (TextView) v.findViewById(R.id.test_label);
        new GetStations().execute();
        return v;
    }

    private String fetchUpdate(){
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
    private void implementUpdate(String s){
        label.setText(s);

    }

    public class GetStations extends AsyncTask<String,Void,String>{
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        String result;
        @Override
        protected String doInBackground(String... strings) {
            result = fetchUpdate();
            return result;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            implementUpdate(s);

        }
    }
}

