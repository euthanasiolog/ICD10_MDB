package com.dev.piatr.icd_10mdb;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by piatr on 04.02.18.
 */

public class ContentAdapter {
    private final static String KEY_ICD = "key1";
    private final static String KEY_PROTOCOL = "key2";
    private final static String EXPANDABLE_GROUP_KEY_CHILD = "expandable_group_key_child";
    private final static String EXPANDABLE_GROUP_KEY_PARENT = "expandable_group_key_parent";
    static final String TITLE = "title";
    static final String COMMENTS = "comments";
    static final String FAVORITES = "favorites";
    static final String protocol = "ПРОТОКОЛ ЛЕЧЕНИЯ";
    private ArrayList<Map<String, String>> commnentList;
    private ArrayList<Map<String, Integer>> favoriteList;

    public ContentAdapter(ArrayList<Map<String, String>> commentList, ArrayList<Map<String, Integer>> favoriteList){
        this.commnentList = commentList;
        this.favoriteList = favoriteList;
    }

    public ArrayList<Map<String, String>> getCommentsFromDB(DB db){
        Cursor cursor = db.getAllData();
        if (cursor==null)
        return null;
        ArrayList<Map<String, String>> commentList = new ArrayList<>();
        if (cursor.moveToFirst()){
            Map<String, String> comment;
            int titleIndex = cursor.getColumnIndex(TITLE);
            int commentIndex = cursor.getColumnIndex(COMMENTS);
            do {
                comment = new HashMap<>();
                comment.put(cursor.getString(titleIndex), cursor.getString(commentIndex));
                commentList.add(comment);
            }while (cursor.moveToNext());
        }return commentList;
    }

    public ArrayList<Map<String, Integer>> getFavoritesFromDB (DB db){
        Cursor cursor = db.getAllData();
        if (cursor==null)
        return null;
        ArrayList<Map<String, Integer>> favoritesList = new ArrayList<>();
        if (cursor.moveToFirst()){
            Map<String, Integer> favorite;
            int titleIndex = cursor.getColumnIndex(TITLE);
            int favoriteIndex = cursor.getColumnIndex(FAVORITES);
            do {
                favorite = new HashMap<>();
                favorite.put(cursor.getString(titleIndex), cursor.getInt(favoriteIndex));
                favoritesList.add(favorite);
            }while (cursor.moveToNext());
        }return favoritesList;
    }

    public void navigationMenuSwitcher (Context context, MyFragmentPagerAdapter myFragmentPagerAdapter,
                                         ViewPager viewPager, int groupPosition, int childPosition, String title){
        String[] parentText;
        String[] childText;
        Bundle expandableGroupFragmentBundle;
        Bundle bundleProtocol;
        ExpandableGroupFragment expandableGroupFragment;
        ProtocolFragment protocolFragment;
        switch (groupPosition){
            case 0:
                switch (childPosition){
                    case 0:
                        parentText = context.getResources().getStringArray(R.array.f00_title);
                        childText = context.getResources().getStringArray(R.array.f00_text);
                        expandableGroupFragmentBundle = new Bundle();
                        expandableGroupFragmentBundle.putStringArray(EXPANDABLE_GROUP_KEY_PARENT, parentText);
                        expandableGroupFragmentBundle.putStringArray(EXPANDABLE_GROUP_KEY_CHILD, childText);
                        expandableGroupFragment = new ExpandableGroupFragment();
                        expandableGroupFragment.setArguments(expandableGroupFragmentBundle);
                        bundleProtocol = new Bundle();
                        bundleProtocol.putString(KEY_PROTOCOL, context.getResources().getString(R.string.protocol_00));
                        protocolFragment = new ProtocolFragment();
                        protocolFragment.setArguments(bundleProtocol);
                        myFragmentPagerAdapter.addFragment(expandableGroupFragment, title);
                        myFragmentPagerAdapter.addFragment(protocolFragment, protocol);
                        viewPager.setAdapter(myFragmentPagerAdapter);
                        break;

                    case 1:
                        parentText = context.getResources().getStringArray(R.array.f01_title);
                        childText = context.getResources().getStringArray(R.array.f01_text);
                        expandableGroupFragmentBundle = new Bundle();
                        expandableGroupFragmentBundle.putStringArray(EXPANDABLE_GROUP_KEY_PARENT, parentText);
                        expandableGroupFragmentBundle.putStringArray(EXPANDABLE_GROUP_KEY_CHILD, childText);
                        expandableGroupFragment = new ExpandableGroupFragment();
                        expandableGroupFragment.setArguments(expandableGroupFragmentBundle);
                        bundleProtocol = new Bundle();
                        bundleProtocol.putString(KEY_PROTOCOL, context.getResources().getString(R.string.f01_protocol));
                        protocolFragment = new ProtocolFragment();
                        protocolFragment.setArguments(bundleProtocol);
                        myFragmentPagerAdapter.addFragment(expandableGroupFragment, title);
                        myFragmentPagerAdapter.addFragment(protocolFragment, protocol);
                        viewPager.setAdapter(myFragmentPagerAdapter);
                        break;
                }
        }
    }

    public void setNavigationMenuData(Context context, ArrayList<ArrayList<Map<String, String>>> childDataList,
                                       ArrayList<Map<String, String>> childDataItemList){
        String[] f0 = context.getResources().getStringArray(R.array.f0_title);
        String[] f1 = context.getResources().getStringArray(R.array.f1_title);
        String[] f2 = context.getResources().getStringArray(R.array.f2_title);
        String[] f3 = context.getResources().getStringArray(R.array.f3_title);
        String[] f4 = context.getResources().getStringArray(R.array.f4_title);
        String[] f5 = context.getResources().getStringArray(R.array.f5_title);
        String[] f6 = context.getResources().getStringArray(R.array.f6_title);
        String[] f7 = context.getResources().getStringArray(R.array.f7_title);
        String[] f8 = context.getResources().getStringArray(R.array.f8_title);
        String[] f9 = context.getResources().getStringArray(R.array.f9_title);

        Map<String, String> map;
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
    }

}
