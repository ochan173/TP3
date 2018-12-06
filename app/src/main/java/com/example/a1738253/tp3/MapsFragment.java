package com.example.a1738253.tp3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a1738253.tp3.Modele.EndroitLog;
import com.example.a1738253.tp3.Modele.Mode;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends SupportMapFragment {

    private GoogleMap mMap;
    private CallBacks mCallBacks;
    private MapsActivity mapsActivity = newInstance();
    private Mode actualMode = mapsActivity.getActualMode();

    public interface CallBacks
    {
        void onChangeMode(Mode mode);
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

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;


                if (actualMode == Mode.Modification) {

                    mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            MarkerOptions marker = new MarkerOptions().position(latLng);
                            marker.draggable(true);
                            mMap.addMarker(marker);
                        }
                    });

                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            //TODO rendre invisible les autres marqueurs pour tout les marqueurs utilisé marker.isVisible();
                            marker.isDraggable();
                            return true;
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
                           // EndroitLog.get(getContext()).updateEndroit();
                        }
                    });
                }
                else if(actualMode == Mode.Ajout) {
                    mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            MarkerOptions marker = new MarkerOptions().position(latLng);
                            marker.draggable(false);
                            mMap.addMarker(marker);
                            ApparaitreDialogue();
                        }

                        private void ApparaitreDialogue() {
                            LayoutInflater factory = LayoutInflater.from(getContext());

                            final View textEntryView = factory.inflate(R.layout.dialogue_ajout_layout, null);

                            final EditText nom = (EditText) textEntryView.findViewById(R.id.nom_endroit);
                            final EditText descriptiopn = (EditText) textEntryView.findViewById(R.id.description_endroit);


                            nom.setText("Nom de l'endroit : ", TextView.BufferType.EDITABLE);
                            descriptiopn.setText("Description : ", TextView.BufferType.EDITABLE);

                            final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                            alert.setIcon(R.drawable.icon).setTitle("Nouvelle endroit:").setView(textEntryView).setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int whichButton) {

                                            Log.i("AlertDialog","TextEntry 1 Entered " + nom.getText().toString());
                                            Log.i("AlertDialog","TextEntry 2 Entered " + descriptiopn.getText().toString());
                                            mCallBacks.onChangeMode(Mode.Aucun);
                                        }
                                    }).setNegativeButton("Annuler",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int whichButton) {
                                          dialog.dismiss();
                                        }
                                    });
                            alert.show();
                        }
                    });
                }

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        mCallBacks.onChangeMode(Mode.Information);
                        return true;
                    }
                });
            }
        });
    }
}

