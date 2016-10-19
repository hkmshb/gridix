package ng.kedco.gridix;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import ng.kedco.gridix.enums.FragmentTags;
import ng.kedco.gridix.enums.ViewType;
import ng.kedco.gridix.fragments.DistributionsFragment;
import ng.kedco.gridix.fragments.FeederThirtyThreeFragment;
import ng.kedco.gridix.fragments.FeederElevenFragment;
import ng.kedco.gridix.fragments.HomeFragment;
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
//        ensureSync();
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
        currentDisplayClass = HomeFragment.class;
        HomeFragment hf = new HomeFragment();
        fm.beginTransaction().replace(R.id.frame_container,hf, FragmentTags.HOME.toString()).commit();
        setTitle("Home");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu,menu);
        //Associate searchable config
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
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
                    if(fg!=null && fg.isVisible() && fg.getTag()==FragmentTags.TRANSMISSION.toString()){
                        TransmissionsFragment tfg = (TransmissionsFragment) fg;
                        tfg.setViewArrangement(selectedViewOption);
                    }
                    else if(fg!=null && fg.isVisible() && fg.getTag()==FragmentTags.DISTRIBUTION.toString()){
                        DistributionsFragment dfg = (DistributionsFragment) fg;
                        dfg.setViewArrangement(selectedViewOption);
                    }
                    else if(fg!=null && fg.isVisible() && fg.getTag()==FragmentTags.INJECTION.toString()){
                        InjectionsFragment ifg = (InjectionsFragment) fg;
                        ifg.setViewArrangement(selectedViewOption);
                    }
                    else if(fg!=null && fg.isVisible() && fg.getTag()==FragmentTags.FEEDERTT.toString()){
                        FeederThirtyThreeFragment fttfg = (FeederThirtyThreeFragment) fg;
                        fttfg.setViewArrangement(selectedViewOption);
                    }
                    else if(fg!=null && fg.isVisible() && fg.getTag()==FragmentTags.FEEDERELV.toString()){
                        FeederElevenFragment fefg = (FeederElevenFragment) fg;
                        fefg.setViewArrangement(selectedViewOption);
                    }
                }

                return true;
            case R.id.show_items_in_grid_option:
                selectedViewOption = ViewType.GRID;
                for(Fragment fg: fm.getFragments()){
                    if(fg!=null && fg.isVisible() && fg.getTag()==FragmentTags.TRANSMISSION.toString()){
                        TransmissionsFragment tfg = (TransmissionsFragment) fg;
                        tfg.setViewArrangement(selectedViewOption);
                    }
                    else if(fg!=null && fg.isVisible() && fg.getTag()==FragmentTags.DISTRIBUTION.toString()){
                        DistributionsFragment dfg = (DistributionsFragment) fg;
                        dfg.setViewArrangement(selectedViewOption);
                    }
                    else if(fg != null && fg.isVisible() && fg.getTag()==FragmentTags.INJECTION.toString()){
                        InjectionsFragment ifg = (InjectionsFragment) fg;
                        ifg.setViewArrangement(selectedViewOption);
                    }
                    else if(fg!=null && fg.isVisible() && fg.getTag()==FragmentTags.FEEDERTT.toString()){
                        FeederThirtyThreeFragment fttfg = (FeederThirtyThreeFragment) fg;
                        fttfg.setViewArrangement(selectedViewOption);
                    }
                    else if(fg!=null && fg.isVisible() && fg.getTag()==FragmentTags.FEEDERELV.toString()){
                        FeederElevenFragment fefg = (FeederElevenFragment) fg;
                        fefg.setViewArrangement(selectedViewOption);
                    }
                }
                return true;
            case R.id.settings:
                Intent i = new Intent(this,SettingsActivity.class);
                startActivity(i);
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
        else if(fragment instanceof  FeederThirtyThreeFragment){
            ftag = FragmentTags.FEEDERTT.toString();
        }
        else if(fragment instanceof  FeederElevenFragment){
            ftag = FragmentTags.FEEDERELV.toString();
        }
        else if(fragment instanceof HomeFragment){
            ftag = FragmentTags.HOME.toString();
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
        else if(id==R.id.nav_menu_home){
            currentDisplayClass = HomeFragment.class;
        }

    }

    public void displayAcitivity(int pos,String from){
        Intent intent = new Intent(this, ItemInfoActivity.class);
        intent.putExtra("clicked_item",pos);
        intent.putExtra("from",from);
        startActivity(intent);
    }

    private void ensureSync(){
        SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.first_sp),MODE_PRIVATE);
        if(sp.getBoolean(getResources().getString(R.string.is_first),true)){
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(getResources().getString(R.string.is_first),false);
            editor.commit();
            Intent intent = new Intent(this,SyncActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }






}
