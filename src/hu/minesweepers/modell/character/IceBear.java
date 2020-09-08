package hu.minesweepers.modell.character;

import hu.minesweepers.modell.character.movebehaviour.Amphibious;
import hu.minesweepers.modell.character.movebehaviour.LocationChanger;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Commandable;
import hu.minesweepers.controller.GameController;
import hu.minesweepers.modell.tile.IceUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 * A jegesmedvét reprezentálja, aki megtámadhatja a karaktereket.
 */
public class IceBear extends LocationChanger implements Commandable {

    /**
     * A jegesmedve objektum pillanatbeli munkavégző képességét jellemző mennyiség.
     */
    private int stamina;

    /**
     * A jegesmedve objektum alapértelmezett munkavégző képességét jellemző mennyiség.
     */
    private final int defaultStamina;

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "icebear";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;


    /**
     * Jegesmedve osztály konstruktora.
     * @param defS alapértelmezett munkavégző képességet jellemző mérték
     * @param unique egyedi azonosító
     */
    public IceBear(int defS, String unique) {
        super(unique, commonID + (objectCount++));
        defaultStamina = defS;
        stamina = defaultStamina;
        changeMoveBehaviour(new Amphibious(null));
    }

    /**
     * Ez által kap figyelmeztetést, hogy a le fog esni,
     * és levezényli az esés folyamatát. A jegesmedve egyből leesik ekkor.
     */
    @Override
    public void fall() {
        fallenDown();
    }

    /**
     * Ennek a metódusnak a meghívása azt jelzi, hogy a metódussal rendelkező dolog már nem esik,
     * hanem leesett. Jegesmedvénél ez nem jár semmilyen következménnyel.
     */
    @Override
    public void fallenDown() {
        //nem történik semmi
    }

    /**
     * A mozgás levezénylését végző metódus.
     * @param goal A cél jégmező, amire el tervezünk mozdulni
     * @param from a jelenlegi IceUnit ahonnan elmegy az objekum
     */
    public void move(IceUnit goal, IceUnit from) {
        super.move(from, goal);
        if(from!=getIceUnit()) {
            from.remove(this);
            getIceUnit().accept(this);
        }
    }

    /**
     * A metódus feladata, hogy levezényelje azt az eseményt,
     * amikor egy jegesmedve összetalálkozik egy karakterrel.
     * @param c a karakter, aki találkozik a jegesmedvével
     */
    @Override
    public void hitBy(Character c) {
        c.hitBy(this);
    }

    /**
     * A metódus feladata, hogy levezényelje azt az eseményt,
     * amikor egy jegesmedve összetalálkozik egy másik jegesmedvével.
     * @param ib a jegesmedve, aki találkozik a jegesmedvével
     */
    @Override
    public void hitBy(IceBear ib) {
        //nem történik semmi
    }

    /**
     * A jegesmedve munkavégző képességének korlátozó függvénye.
     * A jegesmedve nem fáradhat, nem veszíthet munkavégző képességéből.
     */
    @Override
    public void tire() {
        stamina--;
        if(stamina<0) {
            stamina = 0;
        }
    }

    /**
     * A jegesmedve munkavégzőképességének visszaállítása.
     * A jegesmedve mivel nem fáradhat, ezt a képességet nem is kell visszaállítani.
     */
    @Override
    public void rest() {
        stamina = defaultStamina;
    }

    /**
     * A metódus felelőssége, hogy implementálja azon történést, amikor a jegesmedve objektum köre következik.
     * A metódus hatására a jegesmedve ellép egy szomszédos jégtáblára (amennyiben van ilyen).
     * Amennyiben több szomszédos jégtábla is van, a választás teljesen véletlen.
     */
    @Override
    public void executeCommand() {
        while(stamina>0) {      //addig tud cselekedni, amíg van staminája
            ArrayList<IceUnit> possibleGoals = getIceUnit().getNeighbours();    //lekéri a szomszédos mezőket, ezekre léphet
            if(!possibleGoals.isEmpty()) {         //Ha van szomszédős mező, akkor mozgunk véletlenül
                int n = new Random().nextInt(possibleGoals.size()-1);     //véletlen szám generálása
                move(possibleGoals.get(n), getIceUnit());
            }
            tire();     //lépés után fárad
        }
        rest();     //ha végzett a körével, akkor lehetősége lesz pihenni
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
        return obj;
    }
}
