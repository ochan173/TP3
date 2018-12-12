package com.example.a1738253.tp3;

import android.os.Bundle;
import com.example.a1738253.tp3.Modele.Mode;

import java.util.UUID;

/**
 * Classe qui gère les fragments et les changements de mode.
 * @author Yanick Bellavance et Olivier Chan
 */
public class MapsActivity extends DualFragementActivity implements MapsFragment.CallBacks {

    /**
     * Variable qui contient le mode actuel de l'application
     */
    public static Mode actualMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTopFragment(AucunModeFragment.NewInstance());
        actualMode = Mode.Aucun;

        setMainFragment(MapsFragment.newInstance1(null));
    }

    @Override
    public void onChangeMode(Mode mode, String id) {
        switch (mode)
        {
            case Ajout:
                actualMode = Mode.Ajout;
                setTopFragment(AjoutModeFragment.NewInstance());
                break;
            case Aucun:
                actualMode = Mode.Aucun;
                setTopFragment(AucunModeFragment.NewInstance());
                MapsFragment.Clear();
                MapsFragment.AddAllMarkers(getBaseContext());
                break;
            case Information:
                actualMode = Mode.Information;
                setTopFragment(InformationFragment.NewInstance(UUID.fromString(id)));
                break;
            case Modification:
                actualMode = Mode.Modification;
                setTopFragment(ModificationFragment.NewInstance(UUID.fromString(id)));
                break;
                default:
                    break;
        }
    }
}
