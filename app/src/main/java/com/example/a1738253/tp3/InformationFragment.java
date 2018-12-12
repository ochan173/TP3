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

/**
 * Classe qui gère le fragment du mode Information.
 * @author Yanick Bellavance et Olivier Chan
 */
public class InformationFragment extends Fragment{
    private static final String ARG_ENDROIT_ID = "endroit_id";

    /**
     * Variable qui contient les informations d'un endroit
     */
    private Endroit mEndroit;
    /**
     * TextView pour le nom de l'endroit
     */
    private TextView mNom;
    /**
     * TextView pour la description de l'endroit
     */
    private TextView mDescription;
    /**
     * Bouton pour modifier l'endroit
     */
    private Button mModifierButton;
    /**
     * Bouton pour supprimer l'endroit
     */
    private Button mSupprimerButton;
    /**
     * CallBack pour le mode actuel
     */
    private MapsFragment.CallBacks mode;

    /**
     * Construit une nouvelle instance à partir d'un id
     * @param id id de l'endroit à obtenir les informations
     * @return retourne une nouvelle instance d'InformationFragment
     * @author Yanick Bellavance et Olivier Chan
     */
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

        mSupprimerButton = v.findViewById(R.id.supprimer);
        mSupprimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndroitLog.get(getContext()).RemoveEndroit(mEndroit);
                mode.onChangeMode(Mode.Aucun, null);
            }
        });

        return v;
    }
}
