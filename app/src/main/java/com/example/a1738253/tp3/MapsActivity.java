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
                MapsFragment.Refresh();
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
