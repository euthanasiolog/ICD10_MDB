package com.dev.piatr.icd_10mdb;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ICDFragment extends Fragment {
    TextView textView;
    private final static String KEY1 = "key1";

    public ICDFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_icd, container, false);
        textView = view.findViewById(R.id.icd_fragment_text);
        Bundle bundle = getArguments();
        if (bundle!=null){
            String text = bundle.getString(KEY1);
            if (text!=null){
                textView.setText(text);
            }
        }
        return view;
    }

}
