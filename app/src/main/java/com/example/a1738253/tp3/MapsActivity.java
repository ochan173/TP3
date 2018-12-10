package com.example.a1738253.tp3;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.a1738253.tp3.Modele.Endroit;
import com.example.a1738253.tp3.Modele.EndroitLog;
import com.example.a1738253.tp3.Modele.Mode;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.UUID;

public class MapsActivity extends DualFragementActivity implements MapsFragment.CallBacks {

    private static  final  String EXTRA_ENDROIT_ID = "com.cstjean.a1738253.tp3";

    private GoogleMap mMap;
    private Mode actualMode = Mode.Ajout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID endroitID = (UUID)getIntent().getSerializableExtra(MapsActivity.EXTRA_ENDROIT_ID);

        setTopFragment(AucunModeFragment.NewInstance());
        setMainFragment(new MapsFragment());
    }

    @Override
    public void onChangeMode(Mode mode, UUID id) {
        switch (mode)
        {
            case Ajout:
                setTopFragment(AjoutModeFragment.NewInstance(id));
                actualMode = Mode.Ajout;
                break;
            case Aucun:
                setTopFragment(AucunModeFragment.NewInstance());
                actualMode = Mode.Aucun;
                break;
            case Information:
                setTopFragment(InformationFragment.NewInstance(id));
                actualMode = Mode.Information;
                break;
            case Modification:
                setTopFragment(ModificationFragment.NewInstance(id));
                actualMode = Mode.Modification;
                break;
                default:
                    setTopFragment(AucunModeFragment.NewInstance());
                    actualMode = Mode.Aucun;
                    break;
        }
    }

    @Override
    public Mode getMode() {
            return actualMode;
    }

    public static Intent newIntent(Context context, UUID endroit_id){
        Intent intent =  new Intent(context, MapsActivity.class);
        intent.putExtra(EXTRA_ENDROIT_ID, endroit_id);
        return  intent;
    }
}
