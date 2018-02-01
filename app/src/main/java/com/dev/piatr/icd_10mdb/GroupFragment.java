package com.dev.piatr.icd_10mdb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {
    private final static String GROUP_KEY = "group_key";
    Bundle bundle;
    ListView listView;

    public GroupFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_group, container, false);
       bundle = getArguments();
       if (bundle!=null){
           String[] list = bundle.getStringArray(GROUP_KEY);
           if (list!=null){
               listView = view.findViewById(R.id.group_list);
               ArrayAdapter adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, list);
               listView.setAdapter(adapter);
           }
       }
       return view;
    }
}
