package ng.kedco.gridix;

import android.content.res.Configuration;
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

import ng.kedco.gridix.fragments.DistributionsFragment;
import ng.kedco.gridix.fragments.DistributionsListFragment;
import ng.kedco.gridix.fragments.InjectionsFragment;
import ng.kedco.gridix.fragments.InjectionsListFragment;
import ng.kedco.gridix.fragments.TransmissionsFragment;
import ng.kedco.gridix.fragments.TransmissionsListFragment;


public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    NavigationView nvDrawer;
    ActionBarDrawerToggle drawerToggle;
    int selectedViewOption;
    Class currentDisplayClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        selectedViewOption = 1;
        currentDisplayClass = TransmissionsListFragment.class;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new TransmissionsListFragment()).commit();
        setTitle("Transmission Stations");
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
                selectedViewOption = 1;
                if(currentDisplayClass == TransmissionsFragment.class){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new TransmissionsListFragment()).commit();
                    currentDisplayClass = TransmissionsListFragment.class;
                }
                else if(currentDisplayClass == DistributionsFragment.class){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new DistributionsListFragment()).commit();
                    currentDisplayClass = DistributionsListFragment.class;
                }
                else if(currentDisplayClass == InjectionsFragment.class){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new InjectionsListFragment()).commit();
                    currentDisplayClass = InjectionsListFragment.class;
                }
                return true;
            case R.id.show_items_in_grid_option:
                selectedViewOption = 2;
                if(currentDisplayClass == TransmissionsListFragment.class){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new TransmissionsFragment()).commit();
                    currentDisplayClass = TransmissionsFragment.class;
                }
                else if(currentDisplayClass == DistributionsListFragment.class){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new DistributionsFragment()).commit();
                    currentDisplayClass = DistributionsFragment.class;
                }
                else if(currentDisplayClass == InjectionsListFragment.class){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new InjectionsFragment()).commit();
                    currentDisplayClass = InjectionsFragment.class;
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
        switch(id){
            case R.id.nav_menu_transmission:
                setViewArrangement(selectedViewOption,id);
                break;
            case R.id.nav_menu_injection:
                setViewArrangement(selectedViewOption,id);
                break;
            case R.id.nav_menu_distribution:
                setViewArrangement(selectedViewOption,id);
                break;
            default:
                currentDisplayClass = TransmissionsListFragment.class;


        }
        try{
            fragment = (Fragment) currentDisplayClass.newInstance();
        }catch(Exception e){

        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container,fragment).commit();
        item.setChecked(true);
        setTitle(item.getTitle());
        mDrawerLayout.closeDrawers();

    }

    private void setViewArrangement(int selcectedViewOption, int id) {
        if(selcectedViewOption == 1 && id == R.id.nav_menu_transmission){
            currentDisplayClass = TransmissionsListFragment.class;
        }
        else if(selcectedViewOption == 2 && id == R.id.nav_menu_transmission){
            currentDisplayClass = TransmissionsFragment.class;

        }
        else if(selcectedViewOption == 1 && id == R.id.nav_menu_injection){
            currentDisplayClass = InjectionsListFragment.class;
        }
        else if(selcectedViewOption == 2 && id == R.id.nav_menu_injection){
            currentDisplayClass = InjectionsFragment.class;

        }
        else if(selcectedViewOption == 1 && id == R.id.nav_menu_distribution){
            currentDisplayClass = DistributionsListFragment.class;
        }
        else if(selcectedViewOption == 2 && id == R.id.nav_menu_distribution){
            currentDisplayClass = DistributionsFragment.class;

        }
    }

}
