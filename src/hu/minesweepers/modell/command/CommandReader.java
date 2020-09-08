package hu.minesweepers.modell.command;

import hu.minesweepers.modell.command.commands.BuildingCommand;
import hu.minesweepers.modell.command.commands.EntityCommand;
import hu.minesweepers.modell.command.commands.ItemCommand;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

import static hu.minesweepers.modell.command.commands.AbilityCommand.ability;
import static hu.minesweepers.modell.command.commands.AssembleCommand.assemble;
import static hu.minesweepers.modell.command.commands.BuildingCommand.building;
import static hu.minesweepers.modell.command.commands.CurrentCommand.printCurrent;
import static hu.minesweepers.modell.command.commands.DigCommand.dig;
import static hu.minesweepers.modell.command.commands.EntityCommand.entity;
import static hu.minesweepers.modell.command.commands.FieldCommand.field;
import static hu.minesweepers.modell.command.commands.FieldCommand.neighbours;
import static hu.minesweepers.modell.command.commands.ItemCommand.item;
import static hu.minesweepers.modell.command.commands.LoadCommand.load;
import static hu.minesweepers.modell.command.commands.MoveCommand.move;
import static hu.minesweepers.modell.command.commands.SaveCommand.save;
import static hu.minesweepers.modell.command.commands.StatsCommand.stats;
import static hu.minesweepers.modell.command.commands.StormCommand.storm;
import static hu.minesweepers.modell.command.commands.TentCommand.tent;
import static hu.minesweepers.modell.command.commands.TurnCommand.executeNextTurn;


/**
 * A felhasználóval tartja a kapcsolatot a játék során. Ez a statikus osztály tartalmazza a játék
 * irányítására használható parancsokat, amiket értelmez és végrehajt.
 */
public class CommandReader {

    /**
     * Azt jelzi, hogy lehetséges éppen-e az építés. Akkor építhetünk, amikor még nem indult el a játék.
     */
    private static Boolean buildingEnabled = true;
    private static final String cantBuildMessage = "Building is not enabled!";

    /**
     * Ez a függvény elemzi regexekkel a konzolon megadott parancsot és meghivja a megfelelő parancs függvényét a megadott argumentummal.
     * Ha nem megfelelő parancsot adtunk meg, akkor "Command Error" üzenettel jelez vissza a konzolon.
     * @param line A beolvasott sor (parancs), amit a konzolra írtunk, ezt fogja végig elemezni a függvény.
     * @return Ha igazat ad vissza, az azt jelenti, hogy tovább fog futni a játék, szóval sikeresen lefutott a parancs vagy rosszul adtuk meg a parancsot.
     * Hamisat ad vissza, hogyha ki akarunk lépni a játékból, vagy ha a paraméterben kapott string null értékű.
     */
    public static boolean execute(String line) {
        if (line == null) {
            return false;
        }
        line = line.toLowerCase();
        String[] args = line.split(" ");
        if (args.length == 0) {
            return true;
        }
        boolean success = false;
        String outMessage = "Command Error";
        switch(args[0]) {
            case "stats":
                if (args.length == 2 && line.matches("[a-z]+ [a-z0-9]+")) {
                    success = stats(args[1]);
                }
                else if (args.length == 1) {
                    success = stats(new BufferedWriter(new OutputStreamWriter(System.out)));
                }
                break;
            case "field":
                if (!buildingEnabled) {
                    outMessage = cantBuildMessage;
                }
                else if (args.length == 3 && line.matches("[a-z]+ [a-z0-9]+ \\d+")) {
                    success = field(args[1], Integer.parseInt(args[2]));
                }
                else if (args.length == 5 && line.matches("[a-z]+ [a-z0-9]+ \\d+ (false|true) \\d+")) {
                    success = field(args[1], Integer.parseInt(args[2]), Boolean.parseBoolean(args[3]), Integer.parseInt(args[4]));
                }
                break;
            case "neighbours":
                if (!buildingEnabled) {
                    outMessage = cantBuildMessage;
                }
                else if (args.length >= 3) {
                    ArrayList<String> n = new ArrayList<>(Arrays.asList(args).subList(2, args.length));
                    success = neighbours(args[1], n);
                }
                break;
            case "building":
                if (!buildingEnabled) {
                    outMessage = cantBuildMessage;
                }
                else if (args.length == 4 && line.matches("[a-z]+ [a-z0-9]+ [a-z]+ [a-z0-9]+")) {
                    success = building(args[1], BuildingCommand.BuildingType.fromString(args[2]), args[3]);
                }
                break;
            case "entity":
                if (!buildingEnabled) {
                    outMessage = cantBuildMessage;
                }
                else if (args.length == 4 && line.matches("[a-z]+ [a-z0-9]+ [a-z]+ [a-z0-9]+")) {
                    success = entity(args[1], EntityCommand.EntityType.fromString(args[2]), args[3]);
                }
                break;
            case "item":
                if (!buildingEnabled) {
                    outMessage = cantBuildMessage;
                }
                else if (args.length == 4 && line.matches("[a-z]+ [a-z0-9]+ [a-z]+ [a-z0-9]+")) {
                    success = item(args[1], ItemCommand.ItemType.fromString(args[2]), args[3], -1);
                }
                break;
            case "storm":
                if (args.length == 2 && line.matches("[a-z]+ [a-z0-9]+")) {
                    success = storm(args[1]);
                }
                break;
            case "tent":
                if (args. length == 2 && line.matches("[a-z]+ [a-z0-9]+")) {
                    success = tent(args[1]);
                }
                break;
            case "dig":
                if (args. length == 2 && line.matches("[a-z]+ [a-z0-9]+")) {
                    success = dig(args[1]);
                }
                break;
            case "ability":
                if (args.length == 3 && line.matches("[a-z]+ [a-z0-9]+ [a-z0-9]+")) {
                    success = ability(args[1], args[2]);
                }
                break;
            case "move":
                if (args.length == 3 && line.matches("[a-z]+ [a-z0-9]+ [a-z0-9]+")) {
                    success = move(args[1], args[2]);
                }
                break;
            case "assemble":
                if (args. length == 2 && line.matches("[a-z]+ [a-z0-9]+")) {
                    success = assemble(args[1]);
                }
                break;
            case "turn":
                executeNextTurn();
                success = true;
                break;
            case "current":
                printCurrent();
                success = true;
                break;
            case "savemap":
                if (!buildingEnabled) {
                    outMessage = cantBuildMessage;
                }
                else if (args.length == 2 && line.matches("[a-z]+ [a-z0-9]+")) {
                    success = save(args[1]);
                }
                break;
            case "loadmap":
                if (!buildingEnabled) {
                    outMessage = cantBuildMessage;
                }
                else if (args.length == 2 && line.matches("[a-z]+ [a-z0-9]+")) {
                    success = load(args[1]);
                }
                break;
            case "start":
                buildingEnabled = false;
                success = true;
                break;
            case "end":
                buildingEnabled = true;
                success = true;
                break;
            case "exit":
                return false;
        }
        if (!success) {
            System.out.println(outMessage);
        }
        return true;
    }
}
