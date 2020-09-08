package hu.minesweepers.controller.parser;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.character.movebehaviour.LocationChanger;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.view.Drawables.IceUnitView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A GameController-nek egy segédosztálya. Ez az osztály a pálya betöltéséig eltárolja a játék objektumait
 * és ezzel az osztállyal bármilyen azonosítójú objektumot letudunk kérdezni a jégmezőről.
 */
public class ParseHelper {
    /**
     * Tárolja a játékban levő jégtábla objektumokat egy listában.
     */
    private static ArrayList<IceUnit> iceUnits = new ArrayList<>();

    /**
     * Tárolja a játékban levő jégtáblákat a hozzájuk tartozó nézetekkel együtt.
     */
    private static HashMap<IceUnit, IceUnitView> views = new HashMap<>();

    /**
     * Tárolja a játék karaktereit egy listában.
     */
    private static ArrayList<Character> characters = new ArrayList<>();

    /**
     * Tárolja a játékban levő helyváltoztatásra képes objektumokat egy listában.
     */
    private static ArrayList<LocationChanger> locationChangers = new ArrayList<>();

    /**
     * Hozzáadunk a jégmezőhöz egy jégtáblát.
     * @param iu A jégmező, amit hozzá szeretnénk adni.
     */
    public static void addIceUnit(IceUnit iu) {
        iceUnits.add(iu);
    }

    /**
     * Lekérjük a paraméterben kapott azonosítójú jégtáblát.
     * @param id A lekérdezendő jégtábla azonosítója.
     * @return Visszaadja a jégtáblát, aminek a paraméter az azonosítója. Ha nincs ilyen jégtábla, null értéket ad vissza.
     */
    public static IceUnit getIceUnit(String id) {
        for (IceUnit iu : iceUnits) {
            if (iu.getID().equals(id)) {
                return iu;
            }
        }
        return null;
    }

    /**
     * Hozzáadunk a karakter listához egy újabb karaktert.
     * @param c A karakter, amit hozzá akarunk adni.
     */
    public static void addCharacter(Character c) {
        characters.add(c);
        locationChangers.add(c);
    }

    /**
     * Lekérjük a paraméterben kapott azonosítójú karaktert.
     * @param id A lekérdezendő karakter azonosítója.
     * @return Visszaadja a karaktert, aminek a paraméter az azonosítója. Ha nincs ilyen karakter, null értéket ad vissza.
     */
    public static Character getCharacter(String id) {
        for (Character c : characters) {
            if (c.getID().equals(id)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Hozzáadunk az eltárolt objektumokhoz egy jégtábla-nézet párost.
     * @param iu A jégtábla objektum.
     * @param view A jégtáblához tartozó nézet.
     */
    public static void addView(IceUnit iu, IceUnitView view) {
        views.put(iu, view);
    }

    /**
     * Lekérjük a paraméterben kapott jégtáblához tartozó nézetet.
     * @param iu A jégtábla objektum.
     * @return A jégtáblához tartozó nézet.
     */
    public static IceUnitView getView(IceUnit iu) {
        return views.get(iu);
    }

    /**
     * Hozzáadunk a helyváltoztatásra képes objektumok listájához egy új helyváltoztatásra képes objektumot.
     * @param loc Az objektum, amit hozzá akarunk adni.
     */
    public static void addLocationChanger(LocationChanger loc) {
        locationChangers.add(loc);
    }

    /**
     * Lekérjük a paraméterben kapott azonosítójú helyváltoztatásra képes objektumot.
     * @param id A lekérdezendő objektum azonosítója.
     * @return Visszaadja az objektumot, aminek a paraméter az azonosítója. Ha nincs ilyen objektum, null értéket ad vissza.
     */
    public static LocationChanger getLocationChanger(String id) {
        for (LocationChanger loc : locationChangers) {
            if (loc.getID().equals(id)) {
                return loc;
            }
        }
        return null;
    }

    /**
     * Ha betöltött a pálya, nincs szükség az objektumok többszöri eltárolására, ezért töröljük az itt eltárolt objektumokat
     */
    public static void clear() {
        iceUnits.clear();
        views.clear();
        characters.clear();
        locationChangers.clear();
    }
}
