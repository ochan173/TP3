package com.example.a1738253.tp3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a1738253.tp3.Modele.Endroit;
import com.example.a1738253.tp3.Modele.EndroitLog;
import com.example.a1738253.tp3.Modele.Mode;

import java.util.UUID;

public class AucunModeFragment extends Fragment{

    private static final String ARG_ENDROIT_ID = "endroit_id";

    private Endroit mEndroit;
    private TextView mNbEndroits;
    private Button mAjoutButton;
    private MapsFragment.CallBacks mode;


    public static AucunModeFragment NewInstance(){

        Bundle args = new Bundle();
        //args.putSerializable(ARG_ENDROIT_ID, id);

        AucunModeFragment fragment = new AucunModeFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mode = (MapsFragment.CallBacks)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mode = null;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID endroitID = (UUID) getArguments().getSerializable(ARG_ENDROIT_ID);

        mEndroit = EndroitLog.get(getContext()).getEndroit(endroitID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.aucun_mode_layout, container, false);

       String nbEndroits = String.valueOf(EndroitLog.get(getContext()).getNbEndroits());

       if (nbEndroits == "")
           nbEndroits = "0";

       mNbEndroits = v.findViewById(R.id.nb_endroits);
       mNbEndroits.setText(nbEndroits);

       mAjoutButton = v.findViewById(R.id.ajout);
       mAjoutButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mode.onChangeMode(Mode.Ajout, mEndroit.getmId());
           }
       });

        return v;
    }
}
