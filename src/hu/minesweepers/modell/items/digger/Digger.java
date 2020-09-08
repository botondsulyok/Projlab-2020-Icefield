package hu.minesweepers.modell.items.digger;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Identifiable;
import hu.minesweepers.modell.control.Parseable;
import hu.minesweepers.modell.tile.Digsite;

/**
 * Absztrakt osztály, aminek felelőssége, hogy az ásás műveletét levezényelje.
 */
public abstract class Digger extends Identifiable implements Parseable {
    /**
     * Az ásó effektívséget, tehát annak hólapátoló képességét jellemző mérték tagváltozója.
     */
    private int effectiveness;

    /**
     * A Digger (Ásó) osztály konstruktora.
     * @param e Az ásó hólapátolási képességét jellemző mérték
     * @param unique egyedi azonosító
     * @param common autogenerált azonosító
     */
    public Digger(int e, String unique, String common) {
        super(unique, common);
        effectiveness = e;
    }

    /**
     * az ásás levezénylését végzi
     * @param c karakter aki az ásást végzi
     * @param ds ahol az ásást végzik
     */
    public void dig(Character c, Digsite ds) {
        ds.digSnow(c, this);       //valamilyen hatékonysággal tud havat eltüntetni
    }

    /**
     * A függvénnyel jelezhető az ásás sikeressége, a függvény hatása ezek szerint az ásó karakter munkavégző
     *  képességének csökkentése.
     * @param c az ásót használó karakter, akinek csökkentjük a munkavégző képességét
     */
    public void successfulDig(Character c) {
        c.tire();
    }

    /**
     * A függvény segítségével lekérdezhető az ásó effektivitása, tehát hólapátoló képessége.
     * @return az ásó hólapátoló képessége
     */
    public int getEffectiveness(){
        return effectiveness;
    }

    /**
     * A függvény hatására létrehozunk egy ParsedObject példányt, ami az objektum adatainak felhasználására kényelmes
     * felületet biztosít.
     * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
     */
     public ParsedObject convertToParsedObject() {
         ParsedObject obj = new ParsedObject();
         obj.add("id", getID());
         obj.add("type");
         obj.add("effectiveness", String.valueOf(effectiveness));
         return obj;
     }

}
