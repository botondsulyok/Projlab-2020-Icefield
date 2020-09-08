package hu.minesweepers.controller;

import hu.minesweepers.controller.parser.BuildingType;
import hu.minesweepers.controller.parser.EntityType;
import hu.minesweepers.controller.parser.ItemType;
import hu.minesweepers.controller.parser.ParseHelper;
import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.character.IceBear;
import hu.minesweepers.modell.character.movebehaviour.Amphibious;
import hu.minesweepers.modell.character.movebehaviour.LocationChanger;
import hu.minesweepers.modell.character.movebehaviour.Walk;
import hu.minesweepers.modell.character.playable.Eskimo;
import hu.minesweepers.modell.character.playable.Explorer;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Commandable;
import hu.minesweepers.modell.control.Parseable;
import hu.minesweepers.modell.items.Pickable;
import hu.minesweepers.modell.items.components.*;
import hu.minesweepers.modell.items.consumable.Food;
import hu.minesweepers.modell.items.digger.Shovel;
import hu.minesweepers.modell.items.protector.DivingSuit;
import hu.minesweepers.modell.items.puller.Rope;
import hu.minesweepers.modell.tile.IceField;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.modell.tile.StormyField;
import hu.minesweepers.modell.tile.UnstableIceUnit;
import hu.minesweepers.modell.tile.building.Building;
import hu.minesweepers.modell.tile.building.Tent;
import hu.minesweepers.view.Drawables.*;
import hu.minesweepers.view.GameFrame;
import hu.minesweepers.view.GraphView;
import hu.minesweepers.view.TextureType;

import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * A játék indításáért, irányításáért, befejezéséért és az alapvető működéséért felel ez az osztály.
 * Az ő feladata, hogy a karakterek egymás után körökre osztva tevékenykednek.
 * Az ő feladata jelezni a katasztrófáknak, hogy olyan időpillanatba jutott a játék, hogy bekövetkezésük szabályos lenne.
 * Ez az osztály az MVC architektúrában a Controller, azaz ez teremti meg a nézet és a játéklogika között a kapcsolatot.
 * A felhasználói események hatását közvetíti a modellnek és a nézetnek jelzi, hogy frissítenie kell magát ha változott a modell.
 */

public class GameController implements Parseable {

    /**
     * Eltárolja azokat a pályákat, amiket érhet vihar.
     */
    private ArrayList<StormyField> disastrousFields = new ArrayList<StormyField>();

    /**
     * Az irányítható dolgok összessége.
     */
    private ArrayList<Commandable> commandables = new ArrayList<Commandable>();

    /**
     * Azt jelzi, hogy véget ért-e a játék.
     */
    private boolean end = false;

    /**
     * Visszaadja, hogy véget ért-e a játék.
     * @return véget ért-e a játék
     */
    public boolean isEnd() {
        return end;
    }

    /**
     * Azt jelzi, hogy a következő körben jön vihar.
     */
    private boolean stormComing = false;

    /**
     * Az alapvető fájlok (pl.: textúrák, mentett játékállások) elérési útja.
     */
    private String resourcesLocation;

    /**
     * Visszaadja az alapvető fájlok (pl.: textúrák, mentett játékállások) elérési útja.
     * @return a fájlok elérési útja.
     */
    public String getResourcesLocation() {
        return resourcesLocation;
    }

    /**
     * A játékban levő karakterek.
     */
    private ArrayList<Character> characters = new ArrayList<Character>();

    /**
     * Az aktív karakter, ha valamilyen felhasználói esemény történik, akkor erre a karakterre vonatkozik.
     */
    private Character activeCharacter = null;

    /**
     * Visszaadja az aktív karaktert.
     * @return az aktív karakter
     */
    public int getActiveCharacterNumber() {
        return activeCharacterNumber;
    }

    /**
     * Az aktív karakter sorszáma.
     */
    private int activeCharacterNumber = 0;

    /**
     * A játék ebben az ablakban jelenik meg.
     */
    private GameFrame gameFrame;

    /**
     * A játék pályája ebben az objektumban fog kirajzolódni.
     */
    private GraphView graphView;

    /**
     * Azt jelzi, hogy betöltöttek-e már egy pályát.
     */
    private Boolean mapLoaded = false;

    /**
     * A singleton tervezési minta miatt egyszerre csak egy GameController példány lehet, ezt tárolja ez a változó.
     */
    private static GameController instance;

    /**
     * A statikus metóduson keresztük lekérhető az egyetlen GameController példány.
     * @return a GameController példány
     */
    public static GameController getInstance() {
        if(instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    /**
     * Privát konstruktor, ez megakadályozza, hogy több példány lehessen egyszerre az osztályból.
     */
    private GameController() {
        File userFolder = new File(System.getProperty("user.dir"));
        if (userFolder.getName().equals("src")) { // Command line-ból lett indítva
            resourcesLocation = userFolder.getParent() + File.separator + "resources" + File.separator;
        }
        else { // IntelliJ-ből lett indítva
            resourcesLocation = userFolder.getAbsolutePath() + File.separator + "resources" + File.separator;
        }
    }

    /**
     * A metódus feladata a játék indítása.
     */
    public void startGame() {
        gameFrame.setActiveView(graphView);
        gameFrame.setVisible(true);
        graphView.update();
}

    /**
     * Visszaadja, hogy betöltöttek-e már egy pályát.
     * @return annak az értéke, hogy betöltöttek-e már egy pályát
     */
    public Boolean isMapLoaded() {
        return mapLoaded;
    }

    /**
     * A játék fájlból való betöltését végző függvény.
     * @param loadLocation a pálya betöltése ebből a fájlból történik
     */
    public void loadGame(File loadLocation) {
        if (loadLocation == null) {
            return;
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(loadLocation));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (br == null) {
            return;
        }

        initGame();

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

        IceField iceField = new IceField();
        // Create IceUnits, Characters, Items, WalkBehaviours, Buildings
        for (ParsedObject obj : objects) {
            IceUnit iu = null;
            EntityType eType;
            ItemType iType;
            BuildingType bType;
            if (obj.get("type").get(0).equals("iceunit")) {
                iu = new IceUnit(Integer.parseInt(obj.get("snow").get(0)), obj.get("id").get(0));
            }
            else if (obj.get("type").get(0).equals("unstable")) {
                iu = new UnstableIceUnit(Integer.parseInt(obj.get("capacity").get(0)), Integer.parseInt(obj.get("snow").get(0)), obj.get("id").get(0), Boolean.parseBoolean(obj.get("broken").get(0)));
            }
            else if ((eType = EntityType.fromString(obj.get("type").get(0))) != null) {
                if (eType == EntityType.ESKIMO || eType == EntityType.EXPLORER) {
                    Character c;
                    if (eType == EntityType.ESKIMO) c = new Eskimo(5, 4, obj.get("id").get(0));
                    else c = new Explorer(4, 4, obj.get("id").get(0));
                    c.setBodyHeat(Integer.parseInt(obj.get("bodyheat").get(0)));
                    c.setStamina(Integer.parseInt(obj.get("stamina").get(0)));
                    IceUnit unit = ParseHelper.getIceUnit(obj.get("iceUnit").get(0));
                    c.setIceUnit(unit);
                    assert unit != null;
                    unit.accept(c);
                    iceField.addStrikeables(c);
                    ParseHelper.addCharacter(c);
                    characters.add(c);
                    graphView.addPartView(new CharacterView(TextureType.fromString(obj.get("type").get(0)), c));
                }
                else {
                    IceBear b = new IceBear(1, obj.get("id").get(0));
                    IceUnit unit = ParseHelper.getIceUnit(obj.get("iceUnit").get(0));
                    b.setIceUnit(unit);
                    assert unit != null;
                    unit.accept(b);
                    ParseHelper.addLocationChanger(b);
                    commandables.add(b);
                    graphView.addPartView(new IceBearView(TextureType.ICEBEAR, b));

                }
            }
            else if ((iType = ItemType.fromString(obj.get("type").get(0))) != null) {
                if ((obj.get("plot") == null || obj.get("plot").get(0).equals("null"))) {
                    String itemID = obj.get("id").get(0);
                    int durability = obj.get("durability") == null ? -1 : Integer.parseInt(obj.get("durability").get(0));
                    Pickable item = iType.createObject(itemID, durability);
                    for (ParsedObject owner : objects) {
                        if (owner.get("id") == null) {
                            continue;
                        }
                        Character c;
                        if ((c = ParseHelper.getCharacter(owner.get("id").get(0))) != null &&
                                (owner.get("digger").contains(itemID) || owner.get("waterProtecter").contains(itemID) ||
                                        owner.get("pullerDevice").contains(itemID) || owner.get("tent").contains(itemID) ||
                                        owner.get("components").contains(itemID))) {
                            item.pickedBy(c, new IceUnit(0, null));
                        }
                        IceUnit unit;
                        if ((unit = ParseHelper.getIceUnit(owner.get("id").get(0))) != null &&
                                owner.get("pickable").get(0).equals(itemID)) {
                            unit.setPickable(item);
                            ParseHelper.getView(unit).setPickableView(new PickableView(Objects.requireNonNull(TextureType.fromString(obj.get("type").get(0)))));
                        }
                    }
                }
            }
            else if (obj.get("type").get(0).equals("amph") || obj.get("type").get(0).equals("walk")) {
                for (ParsedObject owner : objects) {
                    if (owner.get("id") == null) {
                        continue;
                    }
                    LocationChanger l;
                    if ((l = ParseHelper.getLocationChanger(owner.get("id").get(0))) != null &&
                            owner.get("movebehaviour").get(0).equals(obj.get("id").get(0))) {
                        if (obj.get("type").get(0).equals("walk")) {
                            l.changeMoveBehaviour(new Walk(obj.get("id").get(0)));
                        }
                        else {
                            l.changeMoveBehaviour(new Amphibious(obj.get("id").get(0)));
                        }
                    }
                }
            }
            else if (obj.get("type").get(0).equals("controller")) {
                activeCharacterNumber = Integer.parseInt(obj.get("activenumber").get(0));
                activeCharacter = characters.get(activeCharacterNumber);
            }
            if ((bType = BuildingType.fromString(obj.get("type").get(0))) != null) {
                if (obj.get("plot") != null && obj.get("plot").get(0).equals("null")) {
                    continue;
                }
                IceUnit unit = ParseHelper.getIceUnit(obj.get("plot").get(0));
                Building b = bType.createObject(unit, obj.get("id").get(0));
                assert unit != null;
                unit.build(b);
            }
            if (iu != null) {
                IceUnitView iceUnitView = new IceUnitView(Integer.parseInt(obj.get("x").get(0)), Integer.parseInt(obj.get("y").get(0)),
                        Integer.parseInt(obj.get("width").get(0)), Integer.parseInt(obj.get("height").get(0)), iu);
                if (obj.get("show").get(0).equals("true")) {
                    iu.show();
                }
                graphView.addIceUnitView(iu, iceUnitView);
                iceField.addIceUnit(iu);
                iceField.addStrikeables(iu);
                ParseHelper.addIceUnit(iu);
                ParseHelper.addView(iu, iceUnitView);
            }
        }
        // Set Neighbours
        for (ParsedObject obj : objects) {
            if (obj.get("id") == null) {
                continue;
            }
            IceUnit iu;
            if ((iu = ParseHelper.getIceUnit(obj.get("id").get(0))) != null) {
                for (String s : obj.get("neighbours")) {
                    iu.addNeighbour(ParseHelper.getIceUnit(s));
                }
            }
        }

        mapLoaded = true;
        ParseHelper.clear();
        disastrousFields.add(iceField);

        graphView.update();
    }

    /**
     * A játék fájlba való mentését végző metódus.
     * @param saveLocation a pálya mentése ebbe a fájlba történik
     */
    public void saveGame(File saveLocation) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(saveLocation + ".map"))) {
            // Jégtáblák
            for(Map.Entry<IceUnit, IceUnitView> entry : graphView.getIceUnitViews().entrySet()) {
                entry.getValue().convertToParsedObject().print(bw);
            }
            // Karakterek, medvék
            for (Character c : characters) {
                c.convertToParsedObject().print(bw);
            }
            for (Commandable c : commandables) {
                c.convertToParsedObject().print(bw);
            }
            //  Itemek
            for (Character c : characters) {
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
            for (IceUnit iu : disastrousFields.get((0)).getIceUnits()) {
                if (iu.getPickable() != null) {
                    iu.getPickable().convertToParsedObject().print(bw);
                }
            }
            // Épületek
            for (IceUnit iu : disastrousFields.get((0)).getIceUnits()) {
                iu.getBuilding().convertToParsedObject().print(bw);
            }
            // GameController
            convertToParsedObject().print(bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A játék, a GameController tagváltozóinak inicializálása, alaphelyzetbe állítása.
     * Erre akkor van szükség, amikor egy új játékot indítunk.
     */
    private void initGame() {
        end = false;
        stormComing = false;
        characters.clear();
        activeCharacter = null;
        activeCharacterNumber = 0;
        commandables.clear();

        gameFrame = new GameFrame();
        graphView = new GraphView(gameFrame);
    }

    /**
     * A metódus feladata, hogy a paraméterként kapott változó alapján megfelelően befejezze a játékot.
     * @param isWin azt jelöli, hogy a játék győzelemmel (true) vagy vereséggel (false) ért-e véget
     */
    public void endGame(boolean isWin) {
        if(!end){       //Ha még tart a játék
            if(isWin) {
                ImageIcon gif1 = new ImageIcon(resourcesLocation + "winner1.jpg");
                JOptionPane.showMessageDialog(gameFrame, "", "You WON!", JOptionPane.INFORMATION_MESSAGE, gif1);
            }
            else {
                ImageIcon gif1 = new ImageIcon(resourcesLocation + "looser1.gif");
                JOptionPane.showMessageDialog(gameFrame, "", "You LOST!", JOptionPane.INFORMATION_MESSAGE, gif1);
            }
            end = true;     //A játéknak vége
        }
    }

    /**
     * A metódus feladata, hogy a kapott paramétert eltároljuk az ismert katasztrófával sújtott pályák között.
     * @param df egy a pályához tartozó objektum, amit katasztrófa érhet
     */
    public void addDisastrousNotifiable(StormyField df) {
        disastrousFields.add(df);   //hozzáadja a listához a paraméterként kapott objektumot
    }

    /**
     * A katasztrófával sújtott pályák értesítése, hogy a játék olyan szakaszba ért, ahol a szabályok szerint megtörténhet valamilyen katasztrófa
     * (a jelenlegi követelmények szerint ez a katasztrófa a hóvihar).
     */
    public void notifyDisastrous() {
        for(StormyField sf : disastrousFields) {
            sf.unleashStorm();
        }
    }

    /**
     * A függvény segítségével jelezhetjük a játéknak, hogy új saját körrel rendelkező objektum jött létre.
     * @param comm az objektum, aminek lehet saját köre
     */
    public void addCommandable(Commandable comm){
        commandables.add(comm);         //Hozzáadjuk a listához
    }

    /**
     * Amikor a felhasználói felületen egy olyan esemény következett be, aminek hatására egy karakternek mozognia kell egy kijelölt IceUnitra,
     * akkor ennek az eseménynek, parancsnak a továbbítása a megfelelő Character felé a metódus feladata.
     * Ez a függvény meghívja az aktív karakter move metódusát a kijelölt IceUnittal paraméterezve.
     * @param iu a kijelölt IceUnit
     */
    public void moveAction(IceUnit iu) {
        activeCharacter.move(iu);
        graphView.update();
    }

    /**
     * A felhasználói felületen egy olyan esemény következett be, aminek hatására egy karakternek ásnia kell,
     * ennek az eseménynek, parancsnak a továbbítása a megfelelő Character felé a metódus feladata.
     * Ez a függvény meghívja az aktív karakter dig.
     */
    public void digAction() {
        activeCharacter.dig();
        graphView.update();
    }

    /**
     * felhasználói felületen egy olyan esemény következett be, aminek következményeképpen egy karakter befejezi a körét.
     * Ez a függvény megváltoztatja az éppen aktív karaktert,
     * közben akár lehetőséget adhat más Commandable objektumoknak a cselekvésre.
     */
    public void nextCharacterAction() {
        activeCharacter.executeCommand();
        activeCharacterNumber++;
        if(activeCharacterNumber >= characters.size()) {
            activeCharacterNumber = 0;
            for(Commandable c : commandables) {
                c.executeCommand();
            }
            if(stormComing) {
                notifyDisastrous();
                stormComing = false;
            }
            if(new Random().nextInt()%2==0) {
                stormComing = true;
            }
        }
        activeCharacter = characters.get(activeCharacterNumber);
        graphView.update();
    }

    /**
     * A felhasználói felületen egy olyan esemény következett be, aminek hatására egy karakternek
     * képességet kell használnia egy kijelölt IceUniton, ennek az eseménynek,
     * parancsnak a továbbítása a megfelelő Character felé a metódus feladata.
     * Ez a függvény meghívja az aktív karakter useAbility metódusát a kijelölt IceUnittal paraméterezve.
     * @param iu a kijelölt IceUnit
     */
    public void abilityUseAction(IceUnit iu) {
        activeCharacter.useAbility(iu);
        graphView.update();
    }

    /**
     * A felhasználói felületen egy olyan esemény következett be,
     * aminek hatására egy karakternek építenie kell egy sátrat,
     * ennek az eseménynek, parancsnak a továbbítása a megfelelő Character felé a metódus feladata.
     * Ez a függvény meghívja az aktív karakter buildTent metódusát.
     */
    public void buildTentAction() {
        activeCharacter.buildTent();
        graphView.update();
    }

    /**
     * A felhasználói felületen egy olyan esemény következett be, aminek hatására egy karakternek meg kell próbálnia összeszerelni.
     * Ez a függvény meghívja az aktív karakter assemble metódusát.
     */
    public void assemble() {
        activeCharacter.assemble();
        graphView.update();
    }

    /**
     * Visszaadja az aktív karakter.
     * @return az aktív karakter
     */
    public Character getActiveCharacter() {
        return activeCharacter;
    }

    /**
     * Visszaadja, hogy a következő körben le fog-e csapni vihar.
     * @return annak értéke, hogy a következő körben le fog-e csapni vihar
     */
    public boolean isStormComing() {
        return stormComing;
    }

    /**
     * A függvény az objektum adataiból létrehoz egy ParsedObject példányt, amivel dolgozva egyszerűen feldolgozhatjuk
     * az objektum adatait.
     * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
     */
    @Override
    public ParsedObject convertToParsedObject() {
        ParsedObject obj = new ParsedObject();
        obj.add("type", "controller");
        obj.add("storm", String.valueOf(stormComing));
        obj.add("activechar", activeCharacter == null ? "null" : activeCharacter.getID());
        obj.add("activenumber", String.valueOf(activeCharacterNumber));
        return obj;
    }
}
