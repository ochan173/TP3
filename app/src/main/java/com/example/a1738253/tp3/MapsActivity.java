package com.example.a1738253.tp3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.a1738253.tp3.AucunModeFragment;
import com.example.a1738253.tp3.DualFragementActivity;
import com.example.a1738253.tp3.MapsFragment;
import com.example.a1738253.tp3.Modele.Mode;


public class MapsActivity extends DualFragementActivity implements MapsFragment.CallBacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTopFragment(new AucunModeFragment());
        setMainFragment(new MapsFragment());
    }

    @Override
    public void onChangeMode(Mode mode) {

    }

//    @Override
//    public void onChangeMode(int nouveauMode) {
//        Toast.makeText(this, "Mode" + nouveauMode, Toast.LENGTH_SHORT).show();
//    }
}