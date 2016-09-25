package ng.kedco.gridix;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import ng.kedco.gridix.enums.FragmentTags;
import ng.kedco.gridix.enums.ViewType;
import ng.kedco.gridix.fragments.DistributionsFragment;
import ng.kedco.gridix.fragments.FeederThirtyThreeFragment;
import ng.kedco.gridix.fragments.FeederElevenFragment;
import ng.kedco.gridix.fragments.InjectionsFragment;
import ng.kedco.gridix.fragments.TestFragment;
import ng.kedco.gridix.fragments.TransmissionsFragment;



public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    NavigationView nvDrawer;
    ActionBarDrawerToggle drawerToggle;
    ViewType selectedViewOption;
    Class currentDisplayClass;
    FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(drawerToggle);
        nvDrawer = (NavigationView) findViewById(R.id.nav_menu);
        initialiseActivity();
        setUpDrawerContent(nvDrawer);




    }

    private void initialiseActivity() {
        selectedViewOption = ViewType.LIST;
        currentDisplayClass = TransmissionsFragment.class;
        TransmissionsFragment tf = new TransmissionsFragment();
        Bundle tfargs = new Bundle();
        tfargs.putSerializable("view_type",selectedViewOption);
        tf.setArguments(tfargs);
        fm.beginTransaction().replace(R.id.frame_container,tf, FragmentTags.TRANSMISSION.toString()).commit();
        setTitle("TransmissionStation Stations");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu,menu);
        return true;
    }






    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        item.setChecked(true);
        int id = item.getItemId();
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch(id){
            case R.id.show_items_in_list_option:
                selectedViewOption = ViewType.LIST;
                for(Fragment fg: fm.getFragments()){
                    if(fg.isVisible() && fg.getTag()==FragmentTags.TRANSMISSION.toString()){
                        TransmissionsFragment tfg = (TransmissionsFragment) fg;
                        tfg.setViewArrangement(selectedViewOption);
                    }
                    else if(fg.isVisible() && fg.getTag()==FragmentTags.DISTRIBUTION.toString()){
                        DistributionsFragment dfg = (DistributionsFragment) fg;
                        dfg.setViewArrangement(selectedViewOption);
                    }
                    else if(fg.isVisible() && fg.getTag()==FragmentTags.INJECTION.toString()){
                        InjectionsFragment ifg = (InjectionsFragment) fg;
                        ifg.setViewArrangement(selectedViewOption);


                    }
                }

                return true;
            case R.id.show_items_in_grid_option:
                selectedViewOption = ViewType.GRID;
                for(Fragment fg: fm.getFragments()){
                    if(fg.isVisible() && fg.getTag()==FragmentTags.TRANSMISSION.toString()){
                        TransmissionsFragment tfg = (TransmissionsFragment) fg;
                        tfg.setViewArrangement(selectedViewOption);
                    }
                    else if(fg.isVisible() && fg.getTag()==FragmentTags.DISTRIBUTION.toString()){
                        DistributionsFragment dfg = (DistributionsFragment) fg;
                        dfg.setViewArrangement(selectedViewOption);
                    }
                    else if(fg.isVisible() && fg.getTag()==FragmentTags.INJECTION.toString()){
                        InjectionsFragment ifg = (InjectionsFragment) fg;
                        ifg.setViewArrangement(selectedViewOption);


                    }
                }
                return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void setUpDrawerContent(NavigationView nview) {
        nview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;

            }
        });
    }



    private void selectDrawerItem(MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();
        setViewArrangement(selectedViewOption,id);
        String ftag="";
        try{
            fragment = (Fragment) currentDisplayClass.newInstance();
            Bundle fragArgs = new Bundle();
            fragArgs.putSerializable("view_type",selectedViewOption);
            fragment.setArguments(fragArgs);
        }catch(Exception e){

        }
        if(fragment instanceof TransmissionsFragment){
            ftag = FragmentTags.TRANSMISSION.toString();
        }
        else if(fragment instanceof InjectionsFragment){
            ftag = FragmentTags.INJECTION.toString();
        }
        else if(fragment instanceof DistributionsFragment){
            ftag = FragmentTags.DISTRIBUTION.toString();
        }
        fm.beginTransaction().replace(R.id.frame_container,fragment,ftag).commit();
        item.setChecked(true);
        setTitle(item.getTitle());
        mDrawerLayout.closeDrawers();

    }

    private void setViewArrangement(ViewType selcectedViewOption, int id) {
        if(id == R.id.nav_menu_transmission){
            currentDisplayClass = TransmissionsFragment.class;
        }
        else if( id == R.id.nav_menu_injection){
            currentDisplayClass = InjectionsFragment.class;
        }

        else if(id == R.id.nav_menu_distribution){
            currentDisplayClass = DistributionsFragment.class;
        }
        else if(id == R.id.nav_menu_feeder_33){
            currentDisplayClass = FeederThirtyThreeFragment.class;
        }
        else if(id==R.id.nav_menu_feeder_11){
            currentDisplayClass = FeederElevenFragment.class;
        }
        else if(id==R.id.nav_menu_test){
            currentDisplayClass = TestFragment.class;
        }

    }





}
