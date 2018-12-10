package com.example.a1738253.tp3;

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

    private GoogleMap mMap;
    private Mode actualMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTopFragment(new AucunModeFragment());
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
                setTopFragment(AucunModeFragment.NewInstance(id));
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
                    setTopFragment(AucunModeFragment.NewInstance(id));
                    actualMode = Mode.Aucun;
                    break;
        }
    }

    @Override
    public Mode getMode() {
        if (actualMode != null)
            return actualMode;
        else
            return Mode.Aucun;
    }
}
