package hu.minesweepers.modell.command.commands;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.character.Stander;
import hu.minesweepers.modell.character.movebehaviour.LocationChanger;
import hu.minesweepers.modell.character.movebehaviour.MoveBehaviour;
import hu.minesweepers.modell.command.CommandUtils;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Parseable;
import hu.minesweepers.modell.items.components.Component;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.modell.tile.building.Building;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;


/**
 * A statikus osztálynak az egyetlen felelőssége kezelni a "stats" parancsot.
 */
public class StatsCommand {

    /**
     * Ez a függvény oldja meg a "stats" parancsnak az első változatát. Ezzel a verzióval kiírjuk a paraméterben kapott azonosítójú objektumnak a tulajdonságait.
     * @param id Az objektum azonosítója.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha nem jó azonosítót adtunk meg a parancs argumentumában.
     */
    public static boolean stats(String id) {
        Parseable parseable = null;
        IceUnit iu = CommandUtils.getIceUnit(id);
        if (iu != null) {
            parseable = iu;
        }
        Stander s = CommandUtils.getStander(id);
        if (parseable == null && s != null) {
            parseable = s;
        }
        Building b = CommandUtils.getBuilding(id);
        if (parseable == null && b != null) {
            parseable = b;
        }
        MoveBehaviour m = CommandUtils.getMoveBehaviour(id);
        if (parseable == null && m != null) {
            parseable = m;
        }
        Parseable p = CommandUtils.getItem(id);
        if (parseable == null && p != null) {
            parseable = p;
        }
        if (parseable != null) {
            ParsedObject obj = parseable.convertToParsedObject();
            obj.print(new BufferedWriter(new OutputStreamWriter(System.out)));
            return true;
        }
        return false;
    }

    /**
     * Ez a függvény oldja meg a "stats" parancsnak a második változatát. Ebben a verzióban nem kaptunk azonosítót, szóval az összes objektumnak kiírjuk a tulajdonságait.
     * @param bw Egy character-output stream, ahova kiírjuk az objektumok tulajdonságait.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. A parancsnak ez a változata mindig sikeresen fog lefutni.
     */
    public static boolean stats(BufferedWriter bw) {
        // Jégtáblák
        for (IceUnit iu : CommandUtils.getIceField().getIceUnits()) {
            iu.convertToParsedObject().print(bw);
        }
        // Karakterek, medvék
        for (LocationChanger l : CommandUtils.getLocationChangers()) {
            l.convertToParsedObject().print(bw);
        }
        //  Itemek
        for (Character c : CommandUtils.getCharacters()) {
            c.getDigger().convertToParsedObject().print((bw));
            c.getPullerDevice().convertToParsedObject().print(bw);
            c.getWaterProtecter().convertToParsedObject().print(bw);
            if (c.getTent() != null) {
                c.getTent().convertToParsedObject().print(bw);
            }
            for (Component comp : c.getComponents()) {
                comp.convertToParsedObject().print(bw);
            }
            c.getBehaviour().convertToParsedObject().print(bw);
        }
        for (IceUnit iu : CommandUtils.getIceField().getIceUnits()) {
            if (iu.getPickable() != null) {
                iu.getPickable().convertToParsedObject().print(bw);
            }
        }
        // Épületek
        for (IceUnit iu : CommandUtils.getIceField().getIceUnits()) {
            iu.getBuilding().convertToParsedObject().print(bw);
        }
        return true;
    }
}
