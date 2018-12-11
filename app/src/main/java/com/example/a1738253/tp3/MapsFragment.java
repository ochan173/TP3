package com.example.a1738253.tp3;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.example.a1738253.tp3.Modele.Endroit;
import com.example.a1738253.tp3.Modele.EndroitLog;
import com.example.a1738253.tp3.Modele.Mode;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.UUID;

public class MapsFragment extends SupportMapFragment {

    private  static  final String ARG_ENDROIT_ID = "endroit_id";
    private static final int REQUEST_CODE = 0;
    private  static final String DIALOG_TAG = "DialogTag";

    private UUID endroitID;
    private GoogleMap mMap;
    private CallBacks mCallBacks;
    private Endroit mEndroit;
    private Mode actualMode;

    public interface CallBacks
    {
        void onChangeMode(Mode mode, String id);
        Mode getMode();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBacks = (CallBacks) context;
        actualMode = mCallBacks.getMode();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBacks = null;
    }

    public static MapsFragment newInstance1(String id)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ENDROIT_ID, id);

        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(args);

        return  fragment;

    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
       // final UUID endroitID = (UUID) getArguments().getSerializable(ARG_ENDROIT_ID);
        if(getArguments().getSerializable(ARG_ENDROIT_ID) != null)
            endroitID = UUID.fromString(getArguments().getSerializable(ARG_ENDROIT_ID).toString());

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                if (actualMode == Mode.Aucun)
                {
                    for (Endroit markeur: EndroitLog.get(getContext()).getEndroits()) {
                        mMap.addMarker(new MarkerOptions().position(new
                                LatLng(markeur.getmLatitude(), markeur.getmLongitude())));
                    }
                }

                if (actualMode == Mode.Information)
                {
                    mEndroit = EndroitLog.get(getContext()).getEndroit(endroitID);
                }

                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        if (actualMode == Mode.Aucun)
                        {

                        }

                        if (actualMode == Mode.Ajout)
                        {
                            Endroit e = new Endroit();

                            AjoutDialogueFragment dialog = AjoutDialogueFragment.
                                    newInstance(e.getmId());

                            dialog.setTargetFragment(MapsFragment.this, REQUEST_CODE);
                            dialog.show(getFragmentManager(), DIALOG_TAG);



                            MarkerOptions marker = new MarkerOptions().position(latLng);
                            marker.draggable(false);
                            mMap.addMarker(marker);

                            e.setmLatitude(latLng.latitude);
                            e.setmLongitude(latLng.longitude);

                            EndroitLog.get(getContext()).AddEndroit(e);
                            mCallBacks.onChangeMode(Mode.Aucun, null);
                        }

                        if (actualMode == Mode.Information)
                        {

                        }

                        if (actualMode == Mode.Modification)
                        {
                            MarkerOptions marker = new MarkerOptions().position(latLng);
                            marker.draggable(true);
                            mMap.addMarker(marker);

                            mEndroit.setmLatitude(latLng.latitude);
                            mEndroit.setmLongitude(latLng.longitude);

                            AjoutDialogueFragment dialog = com.example.a1738253.tp3.
                                    ModificationDialogueFragment.
                                    newInstance(mEndroit.getmNom(), mEndroit.getmDescription());

                            dialog.setTargetFragment(MapsFragment.this, REQUEST_CODE);
                            dialog.show(getFragmentManager(), DIALOG_TAG);
                        }
                    }
                });

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        if (actualMode == Mode.Aucun)
                        {
                            mCallBacks.onChangeMode(Mode.Information, mEndroit.getmId().toString());
                            return true;
                        }

                        if (actualMode == Mode.Information)
                        {

                        }

                        if (actualMode == Mode.Modification)
                        {
                            for (Endroit endroit: EndroitLog.get(getContext()).getEndroits()) {
                                Marker marqueur = mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(endroit.getmLatitude(),
                                                endroit.getmLongitude())));
                                if(marqueur != marker)
                                    marqueur.setVisible(false);
                            }
                            marker.isDraggable();
                            return true;
                        }

                        return false;
                    }
                });



                    mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                        @Override
                        public void onMarkerDragStart(Marker marker) {
                        }

                        @Override
                        public void onMarkerDrag(Marker marker) {
                        }

                        @Override
                        public void onMarkerDragEnd(Marker marker) {
                            EndroitLog.get(getContext()).updateEndroit(EndroitLog.get(getContext())
                                    .getEndroit(endroitID));
                        }
                    });
                }
            });
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;

        if (resultCode == REQUEST_CODE)
        {

        }
    }
}


