package com.example.a1738253.tp3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import java.util.Date;
import java.util.UUID;

public class ModificationDialogueFragment extends DialogFragment {

    private static final String ARG_ENDROIT_ID = "endroit_id";
    private static final String ARG_NOM = "nom";
    private static final String ARG_DESCRIPTION = "description";
    public static final String EXTRA_DATE = "com.cstjean.1738253";

    private EditText mNom;
    private EditText mDescription;
    private Endroit mEndroit;


    public  static  AjoutDialogueFragment newInstance(String nom, String description){
        Bundle args = new Bundle();
        args.putString(ARG_NOM, nom);
        args.putString(ARG_DESCRIPTION, description);

        AjoutDialogueFragment fragment = new AjoutDialogueFragment();

        return  fragment;
    }

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
        mDescription = v.findViewById(R.id.description_endroit);

        return new AlertDialog.Builder(getActivity()).setView(v)
                .setTitle("Ajouter un nouvel endroit")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mEndroit.setmNom(mNom.getText().toString());

                        mEndroit.setmDescription(mDescription.getText().toString());

                    }
                }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                }).create();
    }
}