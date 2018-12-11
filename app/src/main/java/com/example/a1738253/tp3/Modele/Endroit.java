package com.example.a1738253.tp3.Modele;

import java.util.UUID;

/**
 * Classe qui gère la structure d'un endroit.
 * @author Yanick Bellavance et Olivier Chan
 */
public class Endroit {

    private UUID mId;
    private String mNom;
    private String mDescription;
    private double mLatitude;
    private double mLongitude;

    /**
     * Constructruit un endroit à partir d'un UUID
     * @author Yanick Bellavance et Olivier Chan
     */
    public Endroit(){this(UUID.randomUUID());}

    public UUID getmId() {
        return mId;
    }

    public String getmNom() {
        return mNom;
    }

    public String getmDescription() {
        return mDescription;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public Endroit(UUID id)
    {
        mId = id;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public void setmNom(String mNom) {
        this.mNom = mNom;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }
}
