package com.example.a1738253.tp3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.a1738253.tp3.Modele.Mode;
import com.google.android.gms.maps.GoogleMap;
import java.util.UUID;

/**
 * Classe qui gère les fragments et les changements de mode.
 * @author Yanick Bellavance et Olivier Chan
 */
public class MapsActivity extends DualFragementActivity implements MapsFragment.CallBacks {

    private static  final  String EXTRA_ENDROIT_ID = "com.cstjean.a1738253.tp3";

    private Mode actualMode = Mode.Ajout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTopFragment(AucunModeFragment.NewInstance());
        setMainFragment(MapsFragment.newInstance1(null));
    }

    @Override
    public void onChangeMode(Mode mode, String id) {
        switch (mode)
        {
            case Ajout:
                setTopFragment(AjoutModeFragment.NewInstance());
                actualMode = Mode.Ajout;
                break;
            case Aucun:
                setTopFragment(AucunModeFragment.NewInstance());
                actualMode = Mode.Aucun;
                break;
            case Information:
                setTopFragment(InformationFragment.NewInstance(UUID.fromString(id)));
                actualMode = Mode.Information;
                break;
            case Modification:
                setTopFragment(ModificationFragment.NewInstance(UUID.fromString(id)));
                actualMode = Mode.Modification;
                break;
                default:
                    break;
        }
    }

    @Override
    public Mode getMode() {
            return actualMode;
    }

    /**
     * Crée un nouvelle Intent à partir d'un contexte et du id d'un endroit
     * @param context contexte
     * @param endroit_id id d'un endroit
     * @return retourne un Intent
     */
    public static Intent newIntent(Context context, UUID endroit_id){
        Intent intent =  new Intent(context, MapsActivity.class);
        intent.putExtra(EXTRA_ENDROIT_ID, endroit_id);
        return  intent;
    }
}
