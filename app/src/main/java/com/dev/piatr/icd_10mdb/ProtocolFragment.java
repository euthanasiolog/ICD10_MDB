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
public class ProtocolFragment extends Fragment {
    private final static String KEY2 = "key2";
    TextView textView;

    public ProtocolFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_protocol, container, false);
        textView = view.findViewById(R.id.protocol_fragment_text);
        Bundle bundle = getArguments();
        if (bundle!=null){
            String text = bundle.getString(KEY2);
            if (text!=null){
                textView.setText(text);
            }
        }return view;
    }
}
