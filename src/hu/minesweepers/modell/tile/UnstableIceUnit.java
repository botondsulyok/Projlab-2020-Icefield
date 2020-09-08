package hu.minesweepers.modell.tile;

import hu.minesweepers.modell.character.ComponentMaster;
import hu.minesweepers.modell.character.IceBear;
import hu.minesweepers.modell.character.Stander;
import hu.minesweepers.modell.character.fallbehaviour.Puller;
import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.items.digger.Digger;
import hu.minesweepers.modell.tile.building.Plains;

import java.util.ArrayList;

/**
 * Egy instabil jégtáblát reprezentáló osztály. A sima jégtáblához képest annyiban tér el, hogy egy
 * bizonyos terhelés hatására át tud fordulni, ekkor a rajta levő dolgok a vízbe esnek.
 */
public class UnstableIceUnit extends IceUnit {
    /**
     * Az egyedi objektumazonosítóhoz szükséges attribútum
     */
    private static int unstableCount = 0;

    /**
     * Azt jelzi, hogy mennyi Stander-t bír el a jégtábla,
     * ha ezt az értéket átlépi, akkor az instabil jégtábla beszakad.
     */
    private int capacity;

    /**
     * A jégtáblának két állapota lehet, beszakadadt vagy nem beszakadt.
     * Ez a tagváltozó ezt az információt jelöli.
     */
    private boolean broken = false;

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "unstable";

    /**
     * Instabil jégtábla konstruktora.
     * @param c A jégtábla teherbíróképessége, ezt túllépve a jégtábla beszakad.
     * @param snow A jégtáblán található kezdeti hómennyiség.
     * @param unique az objektum egyedi azonosítója.
     */
    public UnstableIceUnit(int c, int snow, String unique, boolean b) {
        super(snow, unique);
        capacity = c;
        broken = b;
    }


    /**
     * A metódus felelőssége, hogy lereagálja azt helyes módon, ahogy a meghívás pillanatában éppen a jégtábla
     * terhelése áll.
     * @param commer a jégtáblát újonnan terhelő objektum
     */
    private void breakCheck(Stander commer){
        ArrayList<Stander> standers = getStanders();
        if(getBroken()) {    //ha hóval fedett lukra esünk, akkor beszakad a hó is
            setSnow(0);
            commer.fall();
        }
        else if(capacity <= standers.size()) {
            broken = true;
            setSnow(0);     //ha beszakadunk, eltűnik a hó
            setPickable(null);  //a tárgy is eltűnik
            setBuilding(new Plains(this, null));
            ArrayList<Stander> standersClone = (ArrayList<Stander>) standers.clone();
            for (Stander s : standersClone) {
                if (s.getIceUnit() == this)
                    s.fall();
            }
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
        obj.add("capacity", String.valueOf(capacity));
        obj.add("broken", String.valueOf(getBroken()));
        return obj;
    }

    /**
     * Egy karakter a mezőre kerül, ezt az eseményt kezeli ez a függvény.
     * Hozzáadja a paraméterként kapott objektumot a megfelelő tárolókhoz.
     * A függvény hatására betörhet a jégtábla.
     * @param c a karakter aki az IceUnitra kerül
     */
    public void accept(Character c) {
        ArrayList<Puller> pullers = getPullers();       //referencia
        ArrayList<Stander> standers = getStanders();            //referencia
        ArrayList<ComponentMaster> masters = getMasters();      //referencia
        pullers.add(c);
        standers.add(c);
        masters.add(c);
        breakCheck(c);
        for(Stander s : standers) {     //standerek találkozása az új standerrel
            s.hitBy(c);
        }
    }

    /**
     * Egy jegesmedve a mezőre kerül, ezt az eseményt kezeli ez a függvény.
     * Hozzáadja a paraméterként kapott objektumot a megfelelő tárolóhoz.
     * A metódus hatására betörhet a jégtábla.
     * @param ib a jegesmedve aki az IceUnitra kerül
     */
    public void accept(IceBear ib) {
        ArrayList<Stander> standers = getStanders();        //referencia
        standers.add(ib);
        breakCheck(ib);
        for(Stander s : standers) {     //standerek találkozása az új standerrel
            s.hitBy(ib);
        }
    }

    /**
     *  Egy karakter ás a jégtáblán. Ha törött a jégtábla, akkor nem engedélyezzük az ásást.
     * @param c az a karakter aki az ásást végzi
     * @param d az ásást végző tárgy
     */
    @Override
    public void digSnow(Character c, Digger d) {
        if(!getBroken())            //Ha be van törve, nem ásunk
            super.digSnow(c, d);     //Ha nincs, akkor úgy járunk el, mint a stabil
    }

    /**
     * Visszaadja, hogy törött vagy sem a jégtábla, azaz az objektum töröttségi állapotát.
     * @return az objektum töröttségi állapota, a brokenn változó értéke
     */
    @Override
    public boolean getBroken() {
        return broken;
    }

    @Override
    public String getCapacity() {
        return Integer.toString(capacity);
    }
}
