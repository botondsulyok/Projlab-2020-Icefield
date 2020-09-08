package hu.minesweepers.modell.command;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.character.movebehaviour.Amphibious;
import hu.minesweepers.modell.character.movebehaviour.LocationChanger;
import hu.minesweepers.modell.character.movebehaviour.Walk;
import hu.minesweepers.modell.command.commands.BuildingCommand;
import hu.minesweepers.modell.command.commands.EntityCommand;
import hu.minesweepers.modell.command.commands.ItemCommand;
import hu.minesweepers.modell.tile.IceUnit;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static hu.minesweepers.modell.command.commands.BuildingCommand.building;
import static hu.minesweepers.modell.command.commands.EntityCommand.addEntityToIceUnit;
import static hu.minesweepers.modell.command.commands.EntityCommand.createEntity;
import static hu.minesweepers.modell.command.commands.FieldCommand.field;
import static hu.minesweepers.modell.command.commands.FieldCommand.neighbours;
import static hu.minesweepers.modell.command.commands.ItemCommand.item;


/**
 * A statikus osztály egyetlen felelőssége egy fájlból betöltött pályának a kezelése és játékba állítása.
 */
public class MapParser {

    /**
     * Betölt egy pályát egy character-input stream-ről, majd feldolgozza és eltárolja a megfelelő helyekre
     * a pályán megtalálható összes objektumot. Ezzel játszható és szerkeszthető állapotba rakja a pályát.
     * @param br A character-input stream, amiről beolvastuk a pályát.
     */
    public static void loadMap(BufferedReader br) {
        // Create parsed objects
        ArrayList<ParsedObject> objects = new ArrayList<>();
        objects.add(new ParsedObject());

        String line;
        while(true) {
            try {
                if ((line = br.readLine()) == null) {
                    break;
                }
                if (line.equals("")) {
                    objects.add(new ParsedObject());
                    continue;
                }
                String[] attribute = line.split(": ");
                String[] values = attribute[1].split(", ");
                for (String value : values) {
                    objects.get(objects.size() - 1).add(attribute[0], value);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Last one is empty
        objects.remove(objects.size() - 1);

        // Create IceUnits, Characters, Items, WalkBehaviours, Buildings
        for (ParsedObject obj : objects) {
            EntityCommand.EntityType eType;
            ItemCommand.ItemType iType;
            BuildingCommand.BuildingType bType;
            if (obj.get("type").get(0).equals("iceunit")) {
                field(obj.get("id").get(0), Integer.parseInt(obj.get("snow").get(0)));
            }
            else if (obj.get("type").get(0).equals("unstable")) {
                field(obj.get("id").get(0), Integer.parseInt(obj.get("capacity").get(0)),
                        Boolean.parseBoolean(obj.get("broken").get(0)), Integer.parseInt(obj.get("snow").get(0)));
            }
            else if ((eType = EntityCommand.EntityType.fromString(obj.get("type").get(0))) != null) {
                int stamina = obj.get("stamina") == null ? -1 : Integer.parseInt(obj.get("stamina").get(0));
                int bodyheat = obj.get("bodyheat") == null ? -1 : Integer.parseInt(obj.get("bodyheat").get(0));
                createEntity(obj.get("id").get(0), eType, bodyheat, stamina);
            }
            else if ((iType = ItemCommand.ItemType.fromString(obj.get("type").get(0))) != null) {
                if (!(obj.get("plot") != null && !obj.get("plot").get(0).equals("null"))) {
                    String itemID = obj.get("id").get(0);
                    int durability = obj.get("durability") == null ? -1 : Integer.parseInt(obj.get("durability").get(0));
                    for (ParsedObject owner : objects) {
                        Character c;
                        if ((c = CommandUtils.getCharacter(owner.get("id").get(0))) != null &&
                                (owner.get("digger").contains(itemID) || owner.get("waterProtecter").contains(itemID) ||
                                        owner.get("pullerDevice").contains(itemID) || owner.get("tent").contains(itemID) ||
                                        owner.get("components").contains(itemID))) {
                            item(itemID, iType, c.getID(), durability);
                        }
                        IceUnit iu;
                        if ((iu = CommandUtils.getIceUnit(owner.get("id").get(0))) != null &&
                                owner.get("pickable").get(0).equals(itemID)) {
                            item(itemID, iType, iu.getID(), durability);
                        }
                    }
                }
            }
            else if (obj.get("type").get(0).equals("amph") || obj.get("type").get(0).equals("walk")) {
                for (ParsedObject owner : objects) {
                    LocationChanger l;
                    if ((l = CommandUtils.getLocationChanger(owner.get("id").get(0))) != null &&
                        owner.get("movebehaviour").get(0).equals(obj.get("id").get(0))) {
                        if (obj.get("type").get(0).equals("walk")) {
                            l.changeMoveBehaviour(new Walk(owner.get("id").get(0)));
                        }
                        else {
                            l.changeMoveBehaviour(new Amphibious(owner.get("id").get(0)));
                        }
                    }
                }
            }
            if ((bType = BuildingCommand.BuildingType.fromString(obj.get("type").get(0))) != null) {
                if (obj.get("plot") != null && obj.get("plot").get(0).equals("null")) {
                    continue;
                }
                building(obj.get("id").get(0), bType, obj.get("plot").get(0));
            }
        }
        // Set Neighbours, add Characters to IceUnits
        for (ParsedObject obj : objects) {
            IceUnit iu;
            if ((iu = CommandUtils.getIceUnit(obj.get("id").get(0))) != null) {
                neighbours(obj.get("id").get(0), obj.get("neighbours"));
                for (String standerID : obj.get("standers")) {
                    addEntityToIceUnit(standerID, iu.getID());
                }
            }
        }
    }
}
