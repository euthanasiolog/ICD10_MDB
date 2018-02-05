package com.dev.piatr.icd_10mdb;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<Cursor> {
    private final static String KEY_ICD = "key1";
    private final static String KEY_PROTOCOL = "key2";
    private final static String EXPANDABLE_GROUP_KEY_CHILD = "expandable_group_key_child";
    private final static String EXPANDABLE_GROUP_KEY_PARENT = "expandable_group_key_parent";
    ICDFragment icdFragment;
    ProtocolFragment protocolFragment;
    Bundle bundleICD;
    Bundle bundleProtocol;
    String textICD;
    String textProtocol;
    Map<String, String> map;
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    private ViewPager viewPager;
    private DB db;
    private ContentAdapter contentAdapter;
    ArrayList<Map<String, String>> commentList;
    ArrayList<Map<String, Integer>> favoriteList;
    private int isFavorite;
    private String comment;
    private String actualTitle;
    private ArrayList<String> favorites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentAdapter = new ContentAdapter(commentList, favoriteList);
        db = new DB(this);
        db.openConnection();
        bundleICD = new Bundle();
        bundleProtocol = new Bundle();
        icdFragment = new ICDFragment();
        protocolFragment = new ProtocolFragment();
        textProtocol = getResources().getString(R.string.protocol_title);
        bundleProtocol.putString(KEY_PROTOCOL, textProtocol);
        protocolFragment.setArguments(bundleProtocol);
        textICD = getResources().getString(R.string.icd_title);
        bundleICD.putString(KEY_ICD, textICD);
        icdFragment.setArguments(bundleICD);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        myFragmentPagerAdapter.addFragment(icdFragment, "МКБ 10 - введение");
        myFragmentPagerAdapter.addFragment(protocolFragment, "Клинические протоколы - введение");
        viewPager = (ViewPager)findViewById(R.id.container);
        viewPager.setAdapter(myFragmentPagerAdapter);
        getSupportLoaderManager().initLoader(0, null, this);
        actualTitle = "main";
        favorites = db.getFavorites();
        isFavorite = 0;
        for (String s :favorites){
            if (actualTitle.equals(s))
                isFavorite=1;
        }


        commentList = contentAdapter.getCommentsFromDB(db);
        favoriteList = contentAdapter.getFavoritesFromDB(db);


        ArrayList<Map<String, String>> groupDataList = new ArrayList<>();

        String[] groupTitle = getResources().getStringArray(R.array.f0_f9_title);
        for (String group:groupTitle){
            map = new HashMap<>();
            map.put("groupName", group);
            groupDataList.add(map);
        }

        final ArrayList<ArrayList<Map<String, String>>> childDataList = new ArrayList<>();

        ArrayList<Map<String, String>> childDataItemList = new ArrayList<>();
        contentAdapter.setNavigationMenuData(this, childDataList, childDataItemList);

        String groupFrom[] = new String[]{"groupName"};
        int groupTo[] = new int[]{android.R.id.text1};

        String childFrom[] = new String[]{"childName"};
        int[] childTo = new int[]{android.R.id.text1};

        final SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, groupDataList,
                android.R.layout.simple_expandable_list_item_1, groupFrom, groupTo, childDataList,
                android.R.layout.simple_list_item_1, childFrom, childTo);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_navigation_menu);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
               ExpandableListAdapter adapter1 = parent.getExpandableListAdapter();
               map = (Map<String, String>) adapter1.getChild(groupPosition, childPosition);
               String title = map.get("childName");
               actualTitle = title;
               for (String s :favorites){
                   if (actualTitle.equals(s))
                       isFavorite=1;
               }
               for (Map<String, String> map:commentList){
                   if (map.get(title)!=null){
                       comment=map.get(title);
                   }else comment = null;
                }
                contentAdapter.navigationMenuSwitcher(v.getContext(), myFragmentPagerAdapter, viewPager,
                        groupPosition, childPosition, title);
                return true;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
        getMenuInflater().inflate(R.menu.main, menu);
        if (isFavorite==0){
            menu.findItem(R.id.delete_favorite).setVisible(false);
        } else menu.findItem(R.id.delete_favorite).setVisible(true);
        if (comment==null){
            menu.findItem(R.id.delete_comment).setVisible(false);
        }else menu.findItem(R.id.delete_comment).setVisible(true);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (isFavorite==0){
            menu.findItem(R.id.delete_favorite).setVisible(false);
        } else {
            menu.findItem(R.id.delete_favorite).setVisible(true);
            menu.findItem(R.id.add_favorite).setVisible(false);
        }
         if (comment==null){
            menu.findItem(R.id.delete_comment).setVisible(false);
        }else menu.findItem(R.id.delete_comment).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.info) {

            return true;
        }
        else if (id == R.id.save) {

            return true;
        }

        else if (id == R.id.share){

            return true;
        }

        else if (id == R.id.add_comment){
            Intent intent = new Intent(this, CommentActivity.class);
            if (actualTitle!=null){
                intent.putExtra("title", actualTitle);
                commentList = contentAdapter.getCommentsFromDB(db);
                if (commentList.size()!=0){
                for (Map<String, String> map:commentList) {
                    if (map.get(actualTitle) != null)
                        intent.putExtra("comment", map.get(actualTitle));
                } startActivity(intent);
                return true;
                }else
                    startActivity(intent);
                    return true;
                }
            }

        else if (id == R.id.add_favorite){
            if (actualTitle!=null)
            db.addFavorite(actualTitle);
            isFavorite=1;
            favorites = db.getFavorites();
            return true;
        }

        else if (id == R.id.delete_favorite){
            if (actualTitle!=null)
            db.deleteFavorite(actualTitle);
            isFavorite=0;
            favorites = db.getFavorites();
            return true;
        }

        else if (id == R.id.delete_comment){
            if (actualTitle!=null)
            db.deleteComment(actualTitle);
            return true;
        }

        else if (id == R.id.show_favorites){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            String[] f = favorites.toArray(new String[0]);
            builder.setTitle("ИЗБРАННОЕ:").setItems(f, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
               builder.create().show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.closeConnection();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(this, db);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}
