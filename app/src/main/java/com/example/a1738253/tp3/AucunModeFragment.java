package com.example.a1738253.tp3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a1738253.tp3.Modele.Endroit;
import com.example.a1738253.tp3.Modele.EndroitLog;

import java.util.UUID;

public class AucunModeFragment extends Fragment{

    private  static  final String ARG_ENDROIT_ID = "endroit_id";
    private TextView mNbEndroits;
    private Endroit mEndroits;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID endroitID = (UUID) getArguments().getSerializable(ARG_ENDROIT_ID);

        mEndroits = EndroitLog.get(getContext()).getEndroit(endroitID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.aucun_mode_fragment, container, false);


        mNbEndroits = v.findViewById(R.id.nb_endroits_visite);
        mNbEndroits.setText(mEndroits.);

        return v;
    }
}
