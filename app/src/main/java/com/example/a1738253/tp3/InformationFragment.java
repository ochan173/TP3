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

public class InformationFragment extends Fragment{
    private static final String ARG_ENDROIT_ID = "endroit_id";

    private Endroit mEndroit;
    private TextView mNom;
    private TextView mDescription;
    private Button mModifierButton;
    private Button mSupprimerButton;
    private MapsFragment.CallBacks mode;

    public static InformationFragment NewInstance(UUID id){

        Bundle args = new Bundle();
        args.putSerializable(ARG_ENDROIT_ID, id);

        InformationFragment fragment = new InformationFragment();
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
        View v = inflater.inflate(R.layout.information_layout, container, false);

        mNom = v.findViewById(R.id.nom_endroit);
        mNom.setText(mEndroit.getmNom());

        mDescription = v.findViewById(R.id.description_endroit);
        mDescription.setText(mEndroit.getmDescription());

        mModifierButton = v.findViewById(R.id.modifier);
        mModifierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode.onChangeMode(Mode.Modification, mEndroit.getmId().toString());
            }
        });

        mSupprimerButton = v.findViewById(R.id.modifier);
        mSupprimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode.onChangeMode(Mode.Aucun, mEndroit.getmId().toString());
            }
        });

        return v;
    }
}
