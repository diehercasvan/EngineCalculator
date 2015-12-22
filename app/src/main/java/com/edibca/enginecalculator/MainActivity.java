package com.edibca.enginecalculator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import Class.*;
import ViewFragment.ContainerFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MyCallback {
    private General ObjGenral;
    private ContainerFragment containerFragment;
    private static boolean bValidaLoad = true;
    private static String sTitleApplication = "";
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //CalligraphyConfig.initDefault("fonts/flaticon.ttf");
        ObjGenral = new General(this, this, Environment.getExternalStorageDirectory().getAbsolutePath());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (bValidaLoad) {
            sTitleApplication = getResources().getString(R.string.app_name);
            menuListener(5);
            selectionTitle(5);
            bValidaLoad=false;
        } else {
            getSupportActionBar().setTitle(sTitleApplication);

        }
    }

    public void menuListener(int iSelection) {


        containerFragment = new ContainerFragment(iSelection);
        fragment = containerFragment.selectionFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameContainer, fragment).commit();
        General.deleteCache(this);


    }

    /*
        @Override
        protected void attachBaseContext(Context newBase) {
            super.attachBaseContext(new CalligraphyContextWrapper(newBase));
        }
    */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int iSelection = 0;

        switch (item.getItemId()) {
            case R.id.item1:
                iSelection = 0;
                break;
            case R.id.item2:
                iSelection = 1;
                break;
            case R.id.item3:
                iSelection = 2;
                break;
            case R.id.item4:
                iSelection = 3;
                break;
            case R.id.item5:
                iSelection = 4;
                break;


        }
        menuListener(iSelection);
        selectionTitle(iSelection);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void selectionTitle(int selection) {

        switch (selection) {
            case 0:
                sTitleApplication = getResources().getString(R.string.item1);
                break;
            case 1:
                sTitleApplication = getResources().getString(R.string.item2);
                break;
            case 2:
                sTitleApplication = getResources().getString(R.string.item3);
                break;
            case 3:
                sTitleApplication = getResources().getString(R.string.item4);
                break;
            case 4:
                sTitleApplication = getResources().getString(R.string.item5);
                break;
            case 5:
                sTitleApplication = getResources().getString(R.string.item6);
                break;

        }
        sTitleApplication = sTitleApplication.substring(1, sTitleApplication.length()).trim();

        getSupportActionBar().setTitle(sTitleApplication);
    }

    @Override
    public void onclickMenu(int iSelect) {

        menuListener(iSelect);
        selectionTitle(iSelect);
    }
}
