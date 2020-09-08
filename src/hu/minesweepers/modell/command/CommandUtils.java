package hu.minesweepers.modell.command;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.character.ComponentMaster;
import hu.minesweepers.modell.character.IceUnitManipulator;
import hu.minesweepers.modell.character.Stander;
import hu.minesweepers.modell.character.movebehaviour.LocationChanger;
import hu.minesweepers.modell.character.movebehaviour.MoveBehaviour;
import hu.minesweepers.modell.control.Parseable;
import hu.minesweepers.modell.items.components.Component;
import hu.minesweepers.modell.tile.IceField;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.modell.tile.building.Building;

import java.util.ArrayList;


/**
 * A parancsértelmezőnek egy segédosztálya. Ez az osztály tárolja el a játék objektumait
 * és ezzel az osztállyal bármilyen azonosítójú objektumot letudunk kérdezni a jégmezőről.
 */
public class CommandUtils {

    /**
     * Tárolja a játék jégmezőjét.
     */
    private static IceField iceField = new IceField();

    /**
     * Tárolja a játék karaktereit egy listában.
     */
    private static ArrayList<Character> characters = new ArrayList<>();

    /**
     * Tárolja a játékban levő jégtáblán műveletet végezhető objektumokat egy listában.
     */
    private static ArrayList<IceUnitManipulator> manipulators = new ArrayList<>();

    /**
     * Tárolja a játékban levő helyváltoztatásra képes objektumokat egy listában.
     */
    private static ArrayList<LocationChanger> locationChangers = new ArrayList<>();

    /**
     * Lekérjük a játék jégmezőjét.
     * @return Visszaadja a jégmezőt.
     */
    public static IceField getIceField() {
        return iceField;
    }

    /**
     * Lekérjük a játék karakter listáját.
     * @return Visszaadja a karakter listát.
     */
    public static ArrayList<Character> getCharacters() {
        return characters;
    }

    /**
     * Lekérjük a játékban helyváltoztatásra képes objektum listát.
     * @return Visszaadja ezt az objektum listát.
     */
    public static ArrayList<LocationChanger> getLocationChangers() {
        return locationChangers;
    }

    /**
     * Lekérjük a paraméterben kapott azonosítójú jégtáblát.
     * @param ID A lekérdezendő jégtábla azonosítója.
     * @return Visszaadja a jégtáblát, aminek a paraméter az azonosítója. Ha nincs ilyen jégtábla, null értéket ad vissza.
     */
    public static IceUnit getIceUnit(String ID) {
        for (IceUnit iu : iceField.getIceUnits()) {
            if (iu.getID().equals(ID)) {
                return iu;
            }
        }
        return null;
    }

    /**
     * Hozzáadunk a jégmezőhöz egy jégtáblát.
     * @param iu A jégmező, amit hozzá szeretnénk adni.
     */
    public static void addIceUnit(IceUnit iu) {
        iceField.addIceUnit(iu);
    }

    /**
     * Lekérjük a paraméterben kapott azonosítójú jégtáblát terhelő objektumot.
     * @param ID A lekérdezendő objektum azonosítója.
     * @return Visszaadja az objektumot, aminek a paraméter az azonosítója. Ha nincs ilyen objektum, null értéket ad vissza.
     */
    public static Stander getStander(String ID) {
        for (IceUnit iu : iceField.getIceUnits()) {
            for (Stander s : iu.getStanders()) {
                if (s.getID().equals(ID)) {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * Lekérjük a paraméterben kapott azonosítójú karaktert.
     * @param ID A lekérdezendő karakter azonosítója.
     * @return Visszaadja a karaktert, aminek a paraméter az azonosítója. Ha nincs ilyen karakter, null értéket ad vissza.
     */
    public static Character getCharacter(String ID) {
        for (Character c : characters) {
            if (c.getID().equals(ID)) {
                return c;
            }
        }
        return  null;
    }

    /**
     * Hozzáadunk a karakter listához egy újabb karaktert.
     * @param c A karakter, amit hozzá akarunk adni.
     */
    public static void addCharacter(Character c) {
        characters.add(c);
    }

    /**
     * Lekérjük a paraméterben kapott azonosítójú jégtáblán műveletet végezhető objektumot.
     * @param ID A lekérdezendő objektum azonosítója.
     * @return Visszaadja az objektumot, aminek a paraméter az azonosítója. Ha nincs ilyen objektum, null értéket ad vissza.
     */
    public static IceUnitManipulator getManipulator(String ID) {
        for (IceUnitManipulator m : manipulators) {
            if (m.getID().equals(ID)) {
                return m;
            }
        }
        return  null;
    }

    /**
     * Hozzáadunk a jégtáblán műveletet végezhető objektumok listához egy újabb ilyen objektumot.
     * @param m Az objektum, amit hozzá akarunk adni.
     */
    public static void addManipulator(IceUnitManipulator m) {
        manipulators.add(m);
    }

    /**
     * Lekérjük a paraméterben kapott azonosítójú helyváltoztatásra képes objektumot.
     * @param ID A lekérdezendő objektum azonosítója.
     * @return Visszaadja az objektumot, aminek a paraméter az azonosítója. Ha nincs ilyen objektum, null értéket ad vissza.
     */
    public static LocationChanger getLocationChanger(String ID) {
        for (LocationChanger l : locationChangers) {
            if (l.getID().equals(ID)) {
                return l;
            }
        }
        return  null;
    }

    /**
     * Hozzáadunk a helyváltoztatásra képes objektumok listájához egy új helyváltoztatásra képes objektumot.
     * @param l Az objektum, amit hozzá akarunk adni.
     */
    public static void addLocationChanger(LocationChanger l) {
        locationChangers.add(l);
    }

    /**
     * Lekérjük a paraméterben kapott azonosítójú építményt.
     * @param ID A lekérdezendő építmény azonosítója.
     * @return Visszaadja az építményt, aminek a paraméter az azonosítója. Ha nincs ilyen építmény, null értéket ad vissza.
     */
    public static Building getBuilding(String ID) {
        for (IceUnit iu : iceField.getIceUnits()) {
            if (iu.getBuilding() != null) {
                if (iu.getBuilding().getID().equals(ID)) {
                    return iu.getBuilding();
                }
            }
        }
        return null;
    }

    /**
     * Lekérjük a paraméterben kapott azonosítójú mozgás viselkedést.
     * @param ID A lekérdezendő mozgás viselkedés azonosítója.
     * @return Visszaadja a mozgást, aminek a paraméter az azonosítója. Ha nincs ilyen mozgás, null értéket ad vissza.
     */
    public static MoveBehaviour getMoveBehaviour(String ID) {
        for (LocationChanger l : locationChangers) {
            if (l.getBehaviour().getID().equals(ID)) {
                return l.getBehaviour();
            }
        }
        return null;
    }

    /**
     * Lekérjük a paraméterben kapott azonosítójú tárgyat. A tárgy lehet a karakternél, bármelyik jégtáblán vagy a CompnentMaster-nél.
     * @param ID A lekérdezendő tárgy azonosítója.
     * @return Visszaadja a tárgyat, aminek a paraméter az azonosítója. Ha nincs ilyen tárgy, null értéket ad vissza.
     */
    public static Parseable getItem(String ID) {
        for (Character c : characters) {
            if (c.getDigger().getID().equals(ID)) {
                return c.getDigger();
            }
            if (c.getPullerDevice().getID().equals(ID)) {
                return c.getPullerDevice();
            }
            if (c.getWaterProtecter().getID().equals(ID)) {
                return c.getWaterProtecter();
            }
            if (c.getTent() != null) {
                return c.getTent();
            }
        }
        for (IceUnit iu : iceField.getIceUnits()) {
            if (iu.getPickable() != null) {
                if (iu.getPickable().getID().equals(ID)) {
                    return iu.getPickable();
                }
            }
            for (ComponentMaster m : iu.getMasters()) {
                for (Component c : m.getComponents()) {
                    if (c.getID().equals(ID)) {
                        return c;
                    }
                }
            }
        }
        return null;
    }
}
