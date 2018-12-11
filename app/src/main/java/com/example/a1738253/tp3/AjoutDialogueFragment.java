package com.example.a1738253.tp3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.a1738253.tp3.Modele.Endroit;
import com.example.a1738253.tp3.Modele.EndroitLog;

import java.util.Date;
import java.util.UUID;

/**
 * Classe du dialogue d'ajout.
 * @author Yanick Bellavance et Olivier Chan
 */
public class AjoutDialogueFragment extends DialogFragment {

    private static final String ARG_ENDROIT_ID = "endroit_id";
    public static final String EXTRA_NOM = "com.cstjean.1738253";
    public static final String EXTRA_DESCRIPTION = "com.cstjean.173825312";

    private EditText mNom;
    private EditText mDescription;

    /**
     * Crée une nouvelle instance d'AjoutDialogueFragment à partir d'un id.
     * @param endroitID id de l'endroit à ajouter
     * @return retourne un AjoutDialogueFragment
     * @author Yanick Bellavance et Olivier Chan
     */
    public static AjoutDialogueFragment newInstance(UUID endroitID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_ENDROIT_ID, endroitID);

        AjoutDialogueFragment fragment = new AjoutDialogueFragment();

        return  fragment;
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


                        String nom = mNom.getText().toString();
                        String description = mDescription.getText().toString();

                        sendResult(Activity.RESULT_OK, nom, description);

                    }
                }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                }).create();
    }

    /**
     * Envoit le résultat de la boite de dialogue.
     * @param resultCode Résultat du dialogue, dépendant si l'utilisateur donne un réponde positive
     * ou négative (OK ou Annuler)
     * @param nom Nom de l'endroit
     * @param description description de l'endroit
     * @author Yanick Bellavance et Olivier Chan
     */
    private void sendResult(int resultCode, String nom, String description)
    {
        if (getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putString(EXTRA_NOM, nom);
        extras.putString(EXTRA_DESCRIPTION, description);
        intent.putExtras(extras);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
