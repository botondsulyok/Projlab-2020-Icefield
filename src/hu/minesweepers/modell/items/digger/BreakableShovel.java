package hu.minesweepers.modell.items.digger;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.tile.Digsite;

/**
 * Eltörni képes ásót reprezentáló osztály.
 * */

public class BreakableShovel extends Shovel {

    private int durability;

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "breakable";

    @Override
    public String getCommonID() {
        return commonID;
    }

    /**
     * Az osztály konstruktora.
     * @param e az effectiveness tagváltozó ezt az értéket veszi fel
     * @param d a durability kezdőértéke
     * @param unique az objektum egyedi azonosítója
     */
    public BreakableShovel(int e, int d, String unique) {
        super(e, unique);
        durability = d;
    }

    /**
     * az ásás levezénylését végzi, minden ásásnál csökken az ásó tartóssága,
     * ha ez 0 lesz, akkor eltörik
     * @param c karakter aki az ásást végzi
     * @param ds ahol az ásást végzik
     */
    @Override
    public void dig(Character c, Digsite ds) {
        ds.digSnow(c, this);
    }

    /**
     * A függvénnyel jelezhető az ásás sikeressége, a függvény hatása ezek szerint az ásó karakter munkavégző
     *  képességének csökkentése, és a törhető ásó durability tagváltozó értékének csökkentése.
     * @param c az ásót használó karakter, akinek csökkentjük a munkavégző képességét
     */
    @Override
    public void successfulDig(Character c) {
        c.tire();   //sikeres ásáskor a karakter (aki ásott) fárad
        durability--;   //sikeres ásáskor csökken az ásó hatékonysága
        if(durability==0) {
            //ha a durability nullára csökken, akkor az ásó eltörik
            //és a karakter az alapértelmezett ásó eszközével fog csak rendelkezni
            c.setDefaultDigger();
        }
    }

    /**
     * A függvény az objektum adataiból létrehoz egy ParsedObject példányt, amivel dolgozva egyszerűen feldolgozhatjuk
     * az objektum adatait.
     * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
     */
    @Override
    public ParsedObject convertToParsedObject() {
        ParsedObject obj = super.convertToParsedObject();
        obj.set("type", commonID);
        obj.add("durability", String.valueOf(durability));
        return obj;
    }
}
