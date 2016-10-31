package ng.kedco.gridix.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.Date;
import java.util.List;

import ng.kedco.gridix.R;
import ng.kedco.gridix.db.dao.Db;
import ng.kedco.gridix.db.dao.IStationDao;
import ng.kedco.gridix.db.dao.StationDao;
import ng.kedco.gridix.db.model.Station;
import ng.kedco.gridix.db.model.StationType;
import ng.kedco.gridix.db.model.Voltage;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayStations();
    }

    private void displayStations()
    {
        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAddStationForm();
            }
        });
        fab.show();

        LinearLayout layout = (LinearLayout)findViewById(R.id.content_main);
        layout.removeAllViewsInLayout();

        Db db = new Db(getApplicationContext());
        IStationDao dao = db.getStationDao();
        List<Station> stations = dao.fetchStations();

        ArrayAdapter<Station> adapter = new ArrayAdapter<Station>(
            this, android.R.layout.simple_list_item_1, stations);

        ListView view = new ListView(this);
        view.setAdapter(adapter);
        layout.addView(view);
    }

    private void displayAddStationForm()
    {
        LinearLayout layout = (LinearLayout)findViewById(R.id.content_main);
        layout.removeAllViewsInLayout();

        LayoutInflater lif = LayoutInflater.from(this);
        LinearLayout form = (LinearLayout)lif.inflate(R.layout.station_form, layout);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.hide();

        /*
        Spinner spinner = (Spinner)form.findViewById(R.id.spnType);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this,
            android.R.layout.simple_spinner_item,
            new String[] {"Transmission", "Injection", "Distribution"});

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(typeAdapter);
        */
        final EditText txtExtId = (EditText)form.findViewById(R.id.txtExtId);
        final EditText txtCode = (EditText)form.findViewById(R.id.txtCode);
        final EditText txtAltCode = (EditText)form.findViewById(R.id.txtAltCode);
        final EditText txtName = (EditText)form.findViewById(R.id.txtExtId);

        Button btnCancel = (Button)form.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayStations();
            }
        });

        Button btnSubmit = (Button)form.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Date today = new Date();
                Station station = new Station(
                    Integer.parseInt(txtExtId.getText().toString()),
                    txtCode.getText().toString(), txtAltCode.getText().toString(),
                    txtName.getText().toString(),
                    StationType.TRANSMISSION, Voltage.Ratio.HVOLTH_HVOLTL,
                    true, 1, null, null, 0, null, null, today, false,
                    today, today, today);

                Db db = new Db(getApplicationContext());
                IStationDao dao = db.getStationDao();
                dao.addStation(station);

                displayStations();
            }
        });
    }


    private void displayGridContent()
    {
        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
                displayAddStationForm();
            }
        });

        LinearLayout layout = (LinearLayout)findViewById(R.id.content_main);
        ListView view = new ListView(this);

        final String[] regions = new String[] {
            "Kano Industrial Region",
            "Kano Central Region",
            "Kano West Region",
            "Kano East Region",
            "Katsina Central Region",
            "Katsina North Region",
            "Katsina South Region"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this, android.R.layout.simple_list_item_1, regions);

        view.setAdapter(adapter);
        layout.addView(view);
    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id=item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
