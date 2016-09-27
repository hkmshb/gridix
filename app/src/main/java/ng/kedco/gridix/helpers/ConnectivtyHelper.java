package ng.kedco.gridix.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * Created by shaibujnr on 9/27/16.
 */
public class ConnectivtyHelper {
    Context context;
    public ConnectivtyHelper(Context c){
        this.context = c;
    }
    public boolean IsDeviceConnected(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null){
            //connected
            connected = true;
        }
        else{connected = false;}
        return connected;
    }
}
