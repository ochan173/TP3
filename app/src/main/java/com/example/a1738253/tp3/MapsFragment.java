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

/**
 * Classe qui gère la carte
 * @author Yanick Bellavance et Olivier Chan
 */
public class MapsFragment extends SupportMapFragment {

    private  static  final String ARG_ENDROIT_ID = "endroit_id";
    private static final int REQUEST_CODE = 0;
    private  static final String DIALOG_TAG = "DialogTag";

    /**Contient le id d'un endroit
     */
    private UUID endroitID;
    /**
     * variable qui contient la carte
     */
    private static GoogleMap mMap;
    /**
     * Variable pour changer le mode
     */
    private CallBacks mCallBacks;
    /**
     * Variable qui contient les informations d'un endroit
     */
    private Endroit mEndroit;
    /**
     * Contient la position d'un marqueur
     */
    private MarkerOptions mPosition;

    /**
     * Interface de gestion des modes de l'application.
     * @author Yanick Bellavance et Olivier Chan
     */
    public interface CallBacks
    {
        void onChangeMode(Mode mode, String id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBacks = (CallBacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBacks = null;
    }

    /**
     * Crée une nouvelle instance de MapsFragment à partir d'un id.
     * @param id id d'un endroit sous forme de String
     * @author Yanick Bellavance et Olivier Chan
     * @return retourne une nouvelle instance de Mapsfragment
     */
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
        if(getArguments().getSerializable(ARG_ENDROIT_ID) != null)
            endroitID = UUID.fromString(getArguments().getSerializable(ARG_ENDROIT_ID).toString());

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                if (MapsActivity.actualMode == Mode.Aucun)
                {
                   AddAllMarkers(getContext());
                }

                if (MapsActivity.actualMode == Mode.Information)
                {
                    mEndroit = EndroitLog.get(getContext()).getEndroit(endroitID);
                }

                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        if (MapsActivity.actualMode == Mode.Ajout)
                        {
                            mPosition = new MarkerOptions().position(latLng);

                            AjoutDialogueFragment dialog = AjoutDialogueFragment.
                                    newInstance(null, null);

                            dialog.setTargetFragment(MapsFragment.this, REQUEST_CODE);
                            dialog.show(getFragmentManager(), DIALOG_TAG);

                            mPosition.draggable(false);
                            mMap.addMarker(mPosition);


                            mCallBacks.onChangeMode(Mode.Aucun, null);
                        }


                        if (MapsActivity.actualMode == Mode.Modification)
                        {
                            mPosition = new MarkerOptions().position(latLng);

                            mEndroit = EndroitLog.get(getContext()).getEndroit(endroitID);
                            ModificationDialogueFragment dialog = ModificationDialogueFragment.
                                    newInstance(mEndroit.getmNom(), mEndroit.getmDescription());

                            dialog.setTargetFragment(MapsFragment.this, REQUEST_CODE);
                            dialog.show(getFragmentManager(), DIALOG_TAG);

                            mPosition.draggable(true);
                            mMap.addMarker(mPosition);

                            mCallBacks.onChangeMode(Mode.Aucun, null);
                        }
                    }
                });

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        if (MapsActivity.actualMode == Mode.Aucun)
                        {
                            mCallBacks.onChangeMode(Mode.Information, marker.getTag().toString());
                            return true;
                        }

                        if (MapsActivity.actualMode == Mode.Information)
                        {
                            mCallBacks.onChangeMode(Mode.Information, marker.getTag().toString());
                        }

                        if (MapsActivity.actualMode == Mode.Modification)
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
                    }});
                }
            });
        }

    /**
     * Méthode qui supprime tous les marqueurs d'une carte
     * @author Yanick Bellavance et Olivier Chan
     */
    public static void Clear()
    {
        mMap.clear();
    }

    /**
     * Méthode qui ajoute des marqueurs sur la carte
     * @param context contexte du fragment
     * @author Yanick Bellavance et Olivier Chan
     */
    public static void AddAllMarkers(Context context)
    {
        for (Endroit markeur: EndroitLog.get(context).getEndroits()) {
            mMap.addMarker(new MarkerOptions().position(new
                    LatLng(markeur.getmLatitude(), markeur.getmLongitude())))
                    .setTag(markeur.getmId());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == REQUEST_CODE){
            Endroit e = new Endroit();


            String nom = (String) data.getSerializableExtra(AjoutDialogueFragment.EXTRA_NOM);
            String descritpion = (String) data.getSerializableExtra(AjoutDialogueFragment.EXTRA_DESCRIPTION);

            e.setmNom(nom);
            e.setmDescription(descritpion);
            e.setmLatitude(mPosition.getPosition().latitude);
            e.setmLongitude(mPosition.getPosition().longitude);

            EndroitLog.get(getContext()).AddEndroit(e);
            mCallBacks.onChangeMode(Mode.Aucun, null);
        }
    }

}


