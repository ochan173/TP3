package com.example.a1738253.tp3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class DualFragementActivity extends AppCompatActivity {

    public void setTopFragment (Fragment fragment)
    {
        setFragment(R.id.top_container, fragment);
    }

    public void setMainFragment (Fragment fragment)
    {
        setFragment(R.id.main_container, fragment);
    }

    private void setFragment(int resId, Fragment newFragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(resId);


        if (fragment == null)
            fragment = newFragment;
        fm.beginTransaction().add(resId, fragment).commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dual_fragment_activity);

    }


}
