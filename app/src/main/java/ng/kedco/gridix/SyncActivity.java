package ng.kedco.gridix;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ng.kedco.gridix.enums.Status;
import ng.kedco.gridix.enums.Voltage;
import ng.kedco.gridix.helpers.Holder;
import ng.kedco.gridix.helpers.JsonHelper;
import ng.kedco.gridix.helpers.SqliteHelper;
import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.PowerLine;
import ng.kedco.gridix.models.Station;
import ng.kedco.gridix.models.TransmissionStation;

public class SyncActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView label;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        progressBar = (ProgressBar) findViewById(R.id.sync_progress);
        label = (TextView) findViewById(R.id.sync_desc);



    }

}
