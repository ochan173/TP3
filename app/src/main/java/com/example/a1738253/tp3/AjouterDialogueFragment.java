package com.example.a1738253.tp3;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a1738253.tp3.Modele.Endroit;
import com.example.a1738253.tp3.Modele.EndroitLog;
import com.example.a1738253.tp3.Modele.Mode;

import java.util.UUID;

public class AjouterDialogueFragment extends DialogFragment {

    private static final String ARG_ENDROIT_ID = "endroit_id";

    private EditText mNom;
    private EditText mDescription;
    private Button mBtnOK;
    private Button mBtnAnnuler;
    private Endroit mEndroit;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID endroitID = (UUID) getArguments().getSerializable(ARG_ENDROIT_ID);

        mEndroit = EndroitLog.get(getContext()).getEndroit(endroitID);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialogue_ajout_layout, null);

        mNom = v.findViewById(R.id.nom_endroit);
        mEndroit.setmNom(mNom.getText().toString());

        mDescription = v.findViewById(R.id.description_endroit);
        mEndroit.setmDescription(mDescription.getText().toString());

        mBtnOK = v.findViewById(R.id.ok);
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        mBtnAnnuler = v.findViewById(R.id.annuler);


        return super.onCreateDialog(savedInstanceState);
    }
}
