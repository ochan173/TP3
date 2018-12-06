package com.example.a1738253.tp3;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.a1738253.tp3.Modele.Mode;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
    public void onChangeMode(Mode mode) {
        switch (mode)
        {
            case Ajout:
                setTopFragment(new AjoutModeFragment());
                actualMode = Mode.Ajout;
                break;
            case Aucun:
                setTopFragment(new AucunModeFragment());
                actualMode = Mode.Aucun;
                break;
            case Information:
                setTopFragment(new InformationFragment());
                actualMode = Mode.Information;
                break;
            case Modification:
                setTopFragment(new ModificationFragment());
                actualMode = Mode.Modification;
                break;
                default:
                    break;
        }
    }

    public Mode getActualMode() {
        return actualMode;
    }
}
