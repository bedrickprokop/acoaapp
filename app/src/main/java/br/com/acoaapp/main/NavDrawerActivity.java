package br.com.acoaapp.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.acoaapp.R;
import br.com.acoaapp.constants.MessageConstants;
import br.com.acoaapp.main.consumptionhistory.ConsumptionHistoryFragment;
import br.com.acoaapp.main.generalstatistics.GeneralStatisticsFragment;
import br.com.acoaapp.main.myaccount.MyAccountFragment;
import br.com.acoaapp.main.sustaintability.SustaintabilityFragment;

public class NavDrawerActivity extends AppCompatActivity implements NavDrawerFragment.Callbacks {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_nav_drawer);

        // setup toolbar
        this.toolbar = findViewById(R.id.toolbar);
        this.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.rackley));
        changeToolbarTitle(getString(R.string.toolbar_title_general_statistics));
        setSupportActionBar(this.toolbar);

        // setup drawer view
        drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout != null) {
            NavigationView navigationView = findViewById(R.id.navigation_container);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    return true;
                }
            });

            // setup menu icon
            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        // insert the content fragment into frame_container
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame_container,
                GeneralStatisticsFragment.newInstance(),
                MessageConstants.TAG_GENERAL_STATISTICS_FRAGMENT).commit();

        // insert menu drawer fragment into navigation container
        NavDrawerFragment navDrawerFragment = NavDrawerFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.navigation_container, navDrawerFragment,
                MessageConstants.TAG_NAVIGATION_DRAWER_FRAGMENT).commit();
    }

    private void changeToolbarTitle(String title) {
        this.toolbar.setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavItemClicked(int navItemId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (navItemId) {
            case 1:
                changeToolbarTitle(getString(R.string.toolbar_title_general_statistics));
                fragmentManager.beginTransaction().replace(R.id.frame_container,
                        GeneralStatisticsFragment.newInstance(),
                        MessageConstants.TAG_GENERAL_STATISTICS_FRAGMENT).commit();
                break;
            case 2:
                changeToolbarTitle(getString(R.string.toolbar_title_history_consumption));
                fragmentManager.beginTransaction().replace(R.id.frame_container,
                        ConsumptionHistoryFragment.newInstance(),
                        MessageConstants.TAG_CONSUMPTION_HISTORY_FRAGMENT).commit();
                break;
            case 3:
                changeToolbarTitle(getString(R.string.toolbar_title_sustaintability));
                fragmentManager.beginTransaction().replace(R.id.frame_container,
                        SustaintabilityFragment.newInstance(),
                        MessageConstants.TAG_SUSTAINTABILITY_FRAGMENT).commit();
                break;
            case 4:
                changeToolbarTitle(getString(R.string.toolbar_title_my_account));
                fragmentManager.beginTransaction().replace(R.id.frame_container,
                        MyAccountFragment.newInstance(),
                        MessageConstants.TAG_MY_ACCOUNT_FRAGMENT).commit();
                break;
            case 5:
                this.finish();
                break;
        }

        // Close the navigation drawer
        if (drawerLayout != null) {
            drawerLayout.closeDrawers();
        }
    }
}