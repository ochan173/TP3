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
import java.util.UUID;

/**
 * Classe qui gère la boite de dialogue du mode modification.
 * @author Yanick Bellavance et Olivier Chan
 */
public class ModificationDialogueFragment extends DialogFragment {

    private static final String ARG_NOM = "nom";
    private static final String ARG_DESCRIPTION = "description";

    /**
     * EditText pour le nom de l'endroit
     */
    private EditText mNom;
    /**
     * EditText pour la description de l'endroit
     */
    private EditText mDescription;
    /**
     * Contient le nom de l'endroit
     */
    private String Nom;
    /**
     * Contient la description de l'endroit
     */
    private String Description;

    /**
     * Crée une nouvelle instance de ModificationDialogueFragment à partir du nom et de la
     * description.
     * @param nom nom de l'endroit
     * @param description description de l'endroit
     * @return retourne une nouvelle instance de ModificationDialogueFragment
     * @author Yanick Bellavance et Olivier Chan
     */
    public  static  ModificationDialogueFragment newInstance(String nom, String description){
        Bundle args = new Bundle();
        args.putString(ARG_NOM, nom);
        args.putString(ARG_DESCRIPTION, description);

        return new ModificationDialogueFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Nom = getArguments().getString(ARG_NOM);
        Description = getArguments().getString(ARG_DESCRIPTION);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialogue_ajout_layout, null);

        mNom = v.findViewById(R.id.nom_endroit);
        mNom.setText(Nom);

        mDescription = v.findViewById(R.id.description_endroit);
        mDescription.setText(Description);

        return new AlertDialog.Builder(getActivity()).setView(v)
                .setTitle("Modifier un endroit")
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

    private void sendResult(int resultCode, String nom, String description)
    {
        if (getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putString(AjoutDialogueFragment.EXTRA_NOM, nom);
        extras.putString(AjoutDialogueFragment.EXTRA_DESCRIPTION, description);
        intent.putExtras(extras);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}