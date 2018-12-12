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
import com.example.a1738253.tp3.Modele.Endroit;
import com.example.a1738253.tp3.Modele.Mode;

/**
 * Classe qui gère le mode ajout.
 * @author Yanick Bellavance et Olivier Chan
 */
public class AjoutModeFragment extends Fragment {

    /**
     * Bouton pour annuler l'ajout d'un endroit
     */
    private Button mAnnulerButton;
    private MapsFragment.CallBacks mode;

    /**
     * Crée une nouvelle instance d'AjoutModeFragment.
     * @return retourne une nouvelle instance d'AjoutModeFragment
     * @author Yanick Bellavance et Olivier Chan
     */
    public static AjoutModeFragment NewInstance(){

        Bundle args = new Bundle();

        AjoutModeFragment fragment = new AjoutModeFragment();
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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ajout_mode_layout, container, false);

        mAnnulerButton = v.findViewById(R.id.annuler);
        mAnnulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode.onChangeMode(Mode.Aucun, null);
            }
        });

        return v;
    }
}
