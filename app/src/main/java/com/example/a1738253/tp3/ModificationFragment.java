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
 * Classe qui gère le mode modification.
 * @author Yanick Bellavance et Olivier Chan
 */
public class ModificationFragment extends Fragment {

    private static final String ARG_ENDROIT_ID = "endroit_id";
    private static final int REQUEST_CODE = 0;
    private  static final String DIALOG_TAG = "DialogTag";

    private Endroit mEndroit;
    private Button mAnnulerButton;
    private Button mSauvegarderButton;
    private Button mDétailsButton;
    private MapsFragment.CallBacks mode;

    /**
     * Crée une nouvelle instance de ModificationFragment à partir d'un id
     * @param id id de l'endroit
     * @return retourne une nouvelle instance de ModificationFragment
     * @author Yanick Bellavance et Olivier Chan
     */
    public static ModificationFragment NewInstance(UUID id){

        Bundle args = new Bundle();
        args.putSerializable(ARG_ENDROIT_ID, id);

        ModificationFragment fragment = new ModificationFragment();
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
        View v = inflater.inflate(R.layout.modification_layout, container, false);

        mAnnulerButton = v.findViewById(R.id.annuler);
        mAnnulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode.onChangeMode(Mode.Aucun, mEndroit.getmId().toString());
            }
        });

        mSauvegarderButton = v.findViewById(R.id.sauvegarder);
        mSauvegarderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode.onChangeMode(Mode.Information, mEndroit.getmId().toString());
            }
        });

        mDétailsButton = v.findViewById(R.id.détails);
        mDétailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModificationDialogueFragment dialog = ModificationDialogueFragment.
                        newInstance(mEndroit.getmNom(), mEndroit.getmDescription());

                dialog.setTargetFragment(ModificationFragment.this, REQUEST_CODE);
                dialog.show(getFragmentManager(), DIALOG_TAG);
            }
        });

        return v;
    }
}
