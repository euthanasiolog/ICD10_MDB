package com.dev.piatr.icd_10mdb;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final static String KEY_ICD = "key1";
    private final static String KEY_PROTOCOL = "key2";
    ICDFragment icdFragment;
    ProtocolFragment protocolFragment;
    FragmentTransaction transaction;
    Bundle bundleICD;
    Bundle bundleProtocol;
    String textICD;
    String textProtocol;
    Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        icdFragment = new ICDFragment();
        bundleICD = new Bundle();
        bundleProtocol = new Bundle();
        textICD = getResources().getString(R.string.icd_title);
        textProtocol = getResources().getString(R.string.protocol_title);
        bundleICD.putString(KEY_ICD, textICD);
        icdFragment.setArguments(bundleICD);
        transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.container, icdFragment);
        transaction.commit();

        ArrayList<Map<String, String>> groupDataList = new ArrayList<>();
        String[] f0 = getResources().getStringArray(R.array.f0_title);
        String[] f1 = getResources().getStringArray(R.array.f1_title);
        String[] f2 = getResources().getStringArray(R.array.f2_title);
        String[] f3 = getResources().getStringArray(R.array.f3_title);
        String[] f4 = getResources().getStringArray(R.array.f4_title);
        String[] f5 = getResources().getStringArray(R.array.f5_title);
        String[] f6 = getResources().getStringArray(R.array.f6_title);
        String[] f7 = getResources().getStringArray(R.array.f7_title);
        String[] f8 = getResources().getStringArray(R.array.f8_title);
        String[] f9 = getResources().getStringArray(R.array.f9_title);
        String[] groupTitle = getResources().getStringArray(R.array.f0_f9_title);
        for (String group:groupTitle){
            map = new HashMap<>();
            map.put("groupName", group);
            groupDataList.add(map);
        }

        String groupFrom[] = new String[]{"groupName"};

        int groupTo[] = new int[]{android.R.id.text1};

        ArrayList<ArrayList<Map<String, String>>> childDataList = new ArrayList<>();

        ArrayList<Map<String, String>> childDataItemList = new ArrayList<>();

        for (String child:f0){
            map = new HashMap<>();
            map.put("childName", child);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);
        childDataItemList = new ArrayList<>();
        for (String s:f1){
            map = new HashMap<>();
            map.put("childName", s);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);
        childDataItemList = new ArrayList<>();
        for (String s:f2){
            map = new HashMap<>();
            map.put("childName", s);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);
        childDataItemList = new ArrayList<>();
        for (String s:f3){
            map = new HashMap<>();
            map.put("childName", s);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);
        childDataItemList = new ArrayList<>();
        for (String s:f4){
            map = new HashMap<>();
            map.put("childName", s);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);
        childDataItemList = new ArrayList<>();
        for (String s:f5){
            map = new HashMap<>();
            map.put("childName", s);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);
        childDataItemList = new ArrayList<>();
        for (String s:f6){
            map = new HashMap<>();
            map.put("childName", s);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);
        childDataItemList = new ArrayList<>();
        for (String s:f7){
            map = new HashMap<>();
            map.put("childName", s);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);
        childDataItemList = new ArrayList<>();
        for (String s:f8){
            map = new HashMap<>();
            map.put("childName", s);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);
        childDataItemList = new ArrayList<>();
        for (String s:f9){
            map = new HashMap<>();
            map.put("childName", s);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);

        String childFrom[] = new String[]{"childName"};
        int[] childTo = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, groupDataList,
                android.R.layout.simple_expandable_list_item_1, groupFrom, groupTo, childDataList,
                android.R.layout.simple_list_item_1, childFrom, childTo);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_navigation_menu);
        expandableListView.setAdapter(adapter);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

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
        getMenuInflater().inflate(R.menu.main, menu);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setIcdFragment(View view){
        if (!icdFragment.isVisible()){
            bundleICD.putString(KEY_ICD, textICD);
            icdFragment.setArguments(bundleICD);
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, icdFragment).commit();
        }
    }

    public void setProtocolFragment(View view){
            if (protocolFragment == null) {
                protocolFragment = new ProtocolFragment();
                bundleProtocol.putString(KEY_PROTOCOL, textProtocol);
                protocolFragment.setArguments(bundleProtocol);
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, protocolFragment).commit();
            } else {if (!protocolFragment.isVisible()) {
                bundleProtocol.putString(KEY_PROTOCOL, textProtocol);
                protocolFragment.setArguments(bundleProtocol);
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, protocolFragment).commit();
            }
            }
    }
}
