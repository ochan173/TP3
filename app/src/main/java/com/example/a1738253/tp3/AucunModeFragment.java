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
import com.example.a1738253.tp3.Modele.EndroitLog;
import com.example.a1738253.tp3.Modele.Mode;

/**
 * Classe du fragment AucunMode, qui gère le mode AucunMode.
 * @author Yanick Bellavance et Olivier Chan
 */
public class AucunModeFragment extends Fragment{

    /**
     * TextView pour le nombre d'endroits
     */
    private TextView mNbEndroits;
    /**
     * Bouton pour ajouter un endroit
     */
    private Button mAjoutButton;
    /**
     * Callback pour le mode actuel
     */
    private MapsFragment.CallBacks mode;

    /**
     * Crée une nouvelle instance d'AucunModeFragment.
     * @return retourne une instance d'AucunModeFragment
     * @author Yanick Bellavance et Olivier Chan
     */
    public static AucunModeFragment NewInstance(){

        Bundle args = new Bundle();

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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.aucun_mode_layout, container, false);

       String nbEndroits = String.valueOf(EndroitLog.get(getContext()).getNbEndroits());

       if (nbEndroits.equals(""))
           nbEndroits = "0";

       mNbEndroits = v.findViewById(R.id.nb_endroits);
       mNbEndroits.setText(nbEndroits);

       mAjoutButton = v.findViewById(R.id.ajout);
       mAjoutButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mode.onChangeMode(Mode.Ajout, null);
           }
       });

        return v;
    }
}
