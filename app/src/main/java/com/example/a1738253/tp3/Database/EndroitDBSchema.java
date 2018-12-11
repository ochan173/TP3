package com.example.a1738253.tp3.Database;

/**
 * Classe du shéma de la base de données.
 * @author Yanick Bellavance et Olivier Chan
 */
public class EndroitDBSchema {

    /**
     * Classe de la structure la table.
     * @author Yanick Bellavance et Olivier Chan
     */
    public  static  final class EndroitTable {
        public static final String NAME = "endroits";

        /**
         * Classe de la structure d'une colonne.
         * @author Yanick Bellavance et Olivier Chan
         */
        public static final class  Cols{
            public static final String UUID = "uuid";
            public static final String NOM = "Nom";
            public static final String DESCRIPTION = "Description";
            public static final String LATITUDE = "Latitude";
            public static final String LONGITUDE = "Longitude";
        }
    }
}
