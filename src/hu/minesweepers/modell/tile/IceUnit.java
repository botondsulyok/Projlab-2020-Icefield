package hu.minesweepers.modell.tile;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.character.IceBear;
import hu.minesweepers.modell.character.fallbehaviour.CommunicationPlatform;
import hu.minesweepers.modell.character.fallbehaviour.Pullable;
import hu.minesweepers.modell.character.fallbehaviour.Puller;
import hu.minesweepers.modell.character.Stander;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Strikeable;
import hu.minesweepers.modell.items.Pickable;
import hu.minesweepers.modell.items.components.Component;
import hu.minesweepers.modell.character.ComponentMaster;
import hu.minesweepers.modell.items.digger.Digger;
import hu.minesweepers.modell.tile.building.Building;

import java.util.ArrayList;
import java.util.Random;

/**
 * A jégtáblát reprezentáló osztály.
 * Kezeli ezt a jégtáblát, azaz a jégtáblán levő hó mennyiségét, és azt,
 * hogy mi helyezkedik el rajta. Az ő felelőssége, hogy meghatározza,
 * hogy rajta milyen műveleteket lehet végrehajtani,
 * azaz hogy hogyan lehet vele interakcióba kerülni.
 */
public class IceUnit extends BuildingPlot implements Digsite, CommunicationPlatform, Strikeable {
    /**
     * A jégmezőn elhelyezkedő, elhúzni képes objektumok
     */
    private ArrayList<Puller> pullers = new ArrayList<>();

    /**
     * A jégmezőn elhelyezkedő objektumok
     */
    private ArrayList<Stander> standers = new ArrayList<>();

    /**
     * A mező által tárolt, róla felvehető objektum.
     */
    private Pickable pickable;

    /**
     * A tábla eltárolja a szomszédait.
     */
    private ArrayList<IceUnit> neighbours = new ArrayList<>();

    /**
     * A jégmezőn elhelyezkedő objektumok, amik képesek műveletet végezni a komponensekkel
     */
    private ArrayList<ComponentMaster> masters = new ArrayList<>();

    /**
     * Az attribútum azt méri, hogy hány réteg hó található ezen a mezőn.
     */
    private int snow;

    /**
     * Maximum ennyi réteg hó lehet az IceUniton.
     */
    private int maxSnow = 5;

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "iceunit";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    /**
     * Az jelzi, hogy a kapacitása látható-e az IceUnit-nak.
     */
    boolean show = false;

    /**
     * Visszaadja annak az értékét, hogy kapacitás látható-e.
     * @return show attribútum értéje
     */
    public boolean isShow() {
        return show;
    }

    /**
     * Visszaadja az IceUnit kapacitását.
     * @return Az IceUnit kapacitása.
     */
    public String getCapacity() {
        return "Stable";
    }

    /**
     * IceUnit (jégtábla) konstruktora.
     * @param snowLevel A hómennyiség, amivel rendelkezik a jégtábla létrehozatalakor.
     * @param unique egyedi azonosító
     */
    public IceUnit(int snowLevel, String unique){
        super(unique, commonID + Integer.toString(objectCount++));
        snow = snowLevel;
        pickable = null;
    }

   @Override
    public ParsedObject convertToParsedObject() {
        ParsedObject obj = super.convertToParsedObject();
        obj.set("type", commonID);
        obj.add("snow", String.valueOf(snow));
        obj.add("pickable", pickable == null ? "null" : pickable.getID());
        obj.add("standers");
        for(Stander s : standers) {
            obj.add("standers", s.getID());
        }
        obj.add("pullers");
        for(Puller p : pullers) {
            obj.add("pullers", p.getID());
        }
        obj.add("masters");
        for(ComponentMaster m : masters) {
            obj.add("masters", m.getID());
        }
        obj.add("neighbours");
        for(IceUnit i : neighbours) {
            obj.add("neighbours", i.getID());
        }
        obj.add("show", String.valueOf(show));
        return obj;
    }

    /**
     * Az eltárolt felvehető tárgy eltávolítása a metódus feladata.
     */
    public void removePickable() {
        //törli a rajta levő felvehető tárgyat
        pickable = null;
    }

    /**
     * A metódus hatására hozzáférhetőve válik az információ, hogy a jégtábla hány objektumot bír el.
     */
    public void show() {
        show = true;
    }


    /**
     * Egy karakter a mezőre kerül, ezt az eseményt kezeli ez a függvény.
     * Hozzáadja a paraméterként kapott objektumot a megfelelő tárolókhoz.
     * Továbbá értesít minden az IceUniton tartózkodó Standert,
     * hogy találkozik az éppen az IceUnitra került paraméterként kapott objektummal.
     * @param c a karakter aki az IceUnitra kerül
     */
    public void accept(Character c) {
        pullers.add(c);
        standers.add(c);
        masters.add(c);
        for(Stander s : standers) {
            s.hitBy(c);
        }
    }

    /**
     * Ezzel a metódussal lehet értesíteni az IceUnitot, hogy a rajta levő
     * Character típusú objektum elhagyta őt és ezáltal az IceUniton levő épületet is.
     * El kell távolítani a paraméterként kapott objektumot a megfelelő tárolókból.
     * @param c a karakter objektum aki elhagyja az IceUnitot
     */
    public void remove(Character c) {
        pullers.remove(c);
        standers.remove(c);
        masters.remove(c);
        getBuilding().leave(c);
    }

    /**
     * Egy jegesmedve a mezőre kerül, ezt az eseményt kezeli ez a függvény.
     * Hozzáadja a paraméterként kapott objektumot a megfelelő tárolóhoz.
     * Továbbá értesít minden az IceUniton tartózkodó Standert,
     * hogy találkozik az éppen az IceUnitra került paraméterként kapott objektummal.
     * @param ib a jegesmedve aki az IceUnitra kerül
     */
    public void accept(IceBear ib) {
        standers.add(ib);
        for(Stander s : standers) {
            s.hitBy(ib);
        }
    }

    /**
     * Ezzel a metódussal lehet értesíteni az IceUnitot, hogy a rajta levő
     * IceBear típusú objektum elhagyta őt.
     * El kell távolítani a paraméterként kapott objektumot a megfelelő tárolóból.
     * @param ib a jegesmedve objektum aki elhagyja az IceUnitot
     */
    public void remove(IceBear ib) {
        standers.remove(ib);
    }


    @Override
    /**
     * A metódus felelőssége az, hogy visszaadja a mezőn létező objektumok komponenseinek összességét.
     * @return az összes mezőn levő objektum által birtokolt komponensek
     */
    public ArrayList<Component> askForComponents() {
        ArrayList<Component> components = new ArrayList<Component>();
        for (ComponentMaster cm : masters)
        {
            components.addAll(cm.getComponents());
        }
        return components;
    }

    /**
     * A metódus hatása, hogy jelezzük a szomszéd mezőknek, hogy a paraméterben kapott elhúzható objektum
     * elhúzást kér
     * @param p a húzást kérő objektum
     */
    @Override
    public void callForPullout(Pullable p) {
        IceUnit from = p.getIceUnit();      //elmentjük, hogy hol álltunk
        boolean repositioned = false;
        for(IceUnit iu : neighbours) {
            iu.alertNeighbourForPullout(p);
            if(from != p.getIceUnit()) {
                repositioned = true;    //ha valaki kihúzta, akkor megváltozott a tartózkodási helye
                break;
            }
        }

        if(!repositioned) { //ha nem változott meg a poziciója, akkor nem tudták kihúzni
            p.helpFailed();
        }
    }

    /**
     * A metódus hatásával jelezhetjük, hogy egy szomszédos mező elhúzható objektuma elhúzást kér a mi mezőnkre,
     * a mi elhúzóink által
     * @param p elhúzandó objektum
     */
    @Override
    public void alertNeighbourForPullout(Pullable p) {
        if (!getBroken()) {                 //Ha nem vagyunk töröttek, akkor a húzóink segíthetnek
            IceUnit from = p.getIceUnit();      //elmentjük, hogy hol álltunk
            ArrayList<Puller> pullersCopy = new ArrayList<>(pullers);
            for (Puller puller : pullersCopy) {
                puller.pull(p);
                if (from != p.getIceUnit()) {
                    break;
                }
            }
        }
    }

    /**
     * Egy karakter ás az IceUnit-on.
     * @param c az a karakter aki az ásást végzi
     * @param d az ásást végző tárgy
     */
    @Override
    public void digSnow(Character c, Digger d) {
        if(!getBroken()) {  //törött IceUniton nem lehet ásni
            if(snow==0) {
                if(pickable!=null) {
                    d.successfulDig(c);
                    pickable.pickedBy(c, this);  //ha nem volt rajta hó és van tárgy item akkor azt felveszi a karakter
                }
            }
            else {
                d.successfulDig(c);
                snow = snow - d.getEffectiveness();
                if(snow<0) {
                    snow = 0;   //ha van rajta hó, akkor csökken ennek a mennyisége, de nullánál nem lehet kisebb
                }
            }
        }
    }

    /**
     * Elkapja a katasztrófa az IceUnit-ot, és a rajta levő épületet.
     */
    @Override
    public void struckByDisaster() {
        if(new Random().nextInt()%4==0) {
            Building building = getBuilding();
            building.struckByStorm();
            snow = snow + 1 + new Random().nextInt(2);  //1-3 érték között valamennyi hó kerül a mezőre
            if(snow > maxSnow) {
                snow = maxSnow;   //a hó mennyisége maximum maxSnow értékű lehet
            }
        }
    }

    /**
     * A metódus segítségével szomszéd adható a mezőhöz
     * @param iu a mező szomszédja
     */
    public void addNeighbour(IceUnit iu){
        neighbours.add(iu);
    }

    /**
     * A metódus segítségével szomszédok listája adható a mezőhöz
     * @param iu a mező szomszédjai
     */
    public void addNeighbour(ArrayList<IceUnit> iu){
        neighbours.addAll(iu);
    }

    /**
     * Visszaadja az IceUnit objektummal szomszédos jégegységeket.
     * @return az IceUnit objektum szomszédai
     */
    public ArrayList<IceUnit> getNeighbours() {
        return neighbours;
    }

    /**
     * A metódus visszaadja a jégmezőn tartózkodó Stander típusú objektumokat
     * @return a jégmezőn tartózkodó objektumok
     */
    public ArrayList<Stander> getStanders() {
        return standers;
    }

    /**
     * A metódus visszaadja a jégmezőn tartózkodó Puller típusú objektumokat
     * @return a jégmezőn tartózkodó objektumok
     */
    public ArrayList<Puller> getPullers() {
        return pullers;
    }

    /**
     * A metódus visszaadja a jégmezőn tartózkodó ComponentMaster típusú objektumokat
     * @return a jégmezőn tartózkodó objektumok
     */
    public ArrayList<ComponentMaster> getMasters() {
        return masters;
    }

    /**
     * Visszaadja azt, hogy nem törött.
     * @return visszaadja, hogy nem törött a tábla
     */
    public boolean getBroken() {
        return false;
    }

    /**
     * Visszaadja, hogy szomszédos-e a két mező.
     * @param iu ez az egység amit vizsgálni kell
     * @return szomszédos-e a két mező
     */
    public boolean neigbourUnits(IceUnit iu) {
        return neighbours.contains(iu);
    }

    /**
     *z épülethelyen felépül egy épület. A függvény kicseréli a jelenlegi épülettel az eddigi épületet,
     * amennyiben ez lehetséges. A metódus abban tér el az ősben definiáltól, hogy amennyiben a jégtábla törött,
     * meggátolja az építést.
     * @param b Az épület, amit építeni szeretne a karakter.
     */
    @Override
    public void build(Building b) {
        if(getBroken())
            return;
        super.build(b);
    }

    /**
     * A függvény segítségével lekérdezhető a jégtáblán található hómennyiség.
     * @return A jégtáblán található hómennyiség.
     */
    public int getSnow(){
        return snow;
    }

    /**
     * Beállítja a hó mennyiségét
     * @param s hó mennyisége
     */
    public void setSnow(int s) {
        snow = s;
    }

    /**
     * A függvény segítségével beállítható a jégtábla által tárolt tárgy
     * @param pickable a beállítandó jégtábla által tárolt tárgy
     */
    public void setPickable(Pickable pickable) {
        this.pickable = pickable;
    }

    /**
     * Visszaadja a jégtáblában lévő felvehető tárgyat
     * @return Felvehető tárgy
     */
    public Pickable getPickable() {
        return pickable;
    }

}


