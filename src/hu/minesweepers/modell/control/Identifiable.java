package hu.minesweepers.modell.control;

import java.io.Serializable;

/**
 * Az osztály felelőssége reprezentálni az azonosítható objektumokat.
 */
public abstract class Identifiable implements Serializable {

    /**
     * Az azonosítható objektum egyedi azonosítója
     */
    private String uniqueID;

    public Identifiable() {

    }

    /**
     * Az osztály konstruktora. Meg kell adni az egyedi és a típus azonosító értékét.
     * @param unique egyedi azonosító
     * @param common autogenerált azonosító
     */
    public Identifiable(String unique, String common){
        if(!(unique == null || unique.equals("")))
            uniqueID = unique;
        else uniqueID = common;
    }

    /**
     * A függvény segítségével lekérdezhető az objektum egyedi azonosítója.
     * @return az objektum egyedi azonosítója
     */
    public String getID(){
        return uniqueID;
    }

    /**
     * A függvény segítségével lekérdezhető az objektum azonosítója.
     * @return az objektum azonosítója
     */
    public  String getCommonID() {
        return "";
    }

}
