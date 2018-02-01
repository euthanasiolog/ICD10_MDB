package com.dev.piatr.icd_10mdb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpandableGroupFragment extends Fragment {
    private final static String EXPANDABLE_GROUP_KEY_CHILD = "expandable_group_key_child";
    private final static String EXPANDABLE_GROUP_KEY_PARENT = "expandable_group_key_parent";
    Bundle bundle;
    Map<String, String> map;

    public ExpandableGroupFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expandable_group, container, false);
        bundle = getArguments();
        if (bundle!=null){
            String[] parentList = bundle.getStringArray(EXPANDABLE_GROUP_KEY_PARENT);
            String[] childList = bundle.getStringArray(EXPANDABLE_GROUP_KEY_CHILD);
            if (parentList!=null&&childList!=null){
                ArrayList<Map<String, String>> groupData = new ArrayList<>();
                for (String s:parentList){
                    map = new HashMap<>();
                    map.put("groupName", s);
                    groupData.add(map);
                }
               String[] groupFrom = new String[]{"groupName"};
               int[] groupTo = new int[]{android.R.id.text1};
               ArrayList<ArrayList<Map<String, String>>> childDataList = new ArrayList<>();
               ArrayList<Map<String, String>> item;
               for (String s:childList){
                   item = new ArrayList<>();
                   map = new HashMap<>();
                   map.put("child", s);
                   item.add(map);
                   childDataList.add(item);
               }
                String childFrom[] = new String[]{"child"};
                int childTo[] = new int[]{android.R.id.text1};
                SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(view.getContext(), groupData,
                        android.R.layout.simple_expandable_list_item_1, groupFrom, groupTo, childDataList,
                        android.R.layout.simple_list_item_1, childFrom, childTo);
                ExpandableListView expandableListView = view.findViewById(R.id.expandable_group_fragment);
                expandableListView.setAdapter(adapter);

            }
        }
        return view;
    }
}
