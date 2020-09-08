package hu.minesweepers.modell.character;

import hu.minesweepers.modell.character.fallbehaviour.Pullable;
import hu.minesweepers.modell.character.fallbehaviour.Puller;
import hu.minesweepers.modell.character.fallbehaviour.Terrestrial;
import hu.minesweepers.modell.character.movebehaviour.Walk;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Commandable;
import hu.minesweepers.controller.GameController;
import hu.minesweepers.modell.items.components.Component;
import hu.minesweepers.modell.items.consumable.Consumable;
import hu.minesweepers.modell.items.digger.BareHand;
import hu.minesweepers.modell.items.digger.Digger;
import hu.minesweepers.modell.items.protector.Lung;
import hu.minesweepers.modell.items.protector.WaterProtecter;
import hu.minesweepers.modell.items.puller.BasicPull;
import hu.minesweepers.modell.items.puller.PullerDevice;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.modell.tile.building.Tent;
import java.util.ArrayList;
import java.util.List;

public abstract class Character extends Mortal implements Terrestrial, ComponentMaster, Equipper, Refugee, Commandable,
        Puller, Pullable {

    /**
     * A tárgy, ami megvédi a karaktert vízbeesés esetén
     */
    private WaterProtecter waterProtecter;

    /**
     * A tárgy, ami segítségével a karakter el tud húzni más, elhúzható objektumokat.
     */
    private PullerDevice pullerDevice;

    /**
     * A tárgy, ami segítségével a karakter tud ásni.
     */
    private Digger digger;

    /**
     * A karakter ezekkel a komponensekkel rendelkezik.
     */
    private List<Component> components = new ArrayList<>();

    /**
     * A karakter ezzel a sátor tárggyal rendelkezik.
     */
    private Tent tent = null;

    /**
     * A karakter állóképességét reprezentálja.
     */
    private int stamina;

    /**
     * A karakter alapértelmezett állóképességét reprezentálja.
     */
    private final int defaultStamina;

    /**
     * A karakter osztály konstruktora.
     *
     * @param maxB   A karakter objektum maximális testhője.
     * @param defS   A karakter objektum alapértelmezett munkavégző képssége.
     * @param unique egyedi azonosító
     * @param common autogenerált azonosító
     */
    public Character(int maxB, int defS, String unique, String common) {
        super(maxB, unique, common);
        defaultStamina = defS;
        stamina = defaultStamina;
        setDefaultDigger();
        waterProtecter = new Lung(null);
        pullerDevice = new BasicPull(null);
        changeMoveBehaviour(new Walk(null));
    }

    /**
     * Parancs végrehajtása, ezáltal jelezhető a karakternek, hogy az ő köre van, és eképpen viselkedhet.
     */
    public void executeCommand() {
        rest();     //visszaállítjuk a munkavégzőképességet
    }

    /**
     * Az örökölt metódus az osztály szerinti megvalósítása. Ha az objektumnak elfogy a testhője, akkor a játéknak
     * vége
     * @param n a mérték, amivel csökken az objektum testhője
     */
    @Override
    public void reduceBodyHeat(int n) {
        super.reduceBodyHeat(n);
        if (getBodyHeat() <= 0) {
            GameController.getInstance().endGame(false);  //ha nulla vagy az alá csökken a testhője akkor meghal a karakter és vége a játéknak
        }
    }

    /**
     * A metódus az osztálynak megfelelő implementálása, az elhúzást a karakter húzóeszközével végezzük el
     * @param p Az elhúzható objektum
     */
    @Override
    public void pull(Pullable p) {
        pullerDevice.pullByDevice(p, getIceUnit());
    }

    /**
     * A metódus hatása, hogy jelezzük a karakternek hogy vízbeesett
     * Ekkor a karakter a víztől védő tárgyával próbál védekezni
     */
    @Override
    public void fall() {
        waterProtecter.protectFromWater(this);
    }

    /**
     * A metódussal jelezhető az objektumnak, hogy az leesett a vízbe.
     * A játék szabályai szerint ekkor a játéknak vége.
     */
    @Override
    public void fallenDown() {
        GameController.getInstance().endGame(false);      //jelezzük az irányítónak, hogy a játéknak vége
    }

    /**
     * A mozgás levezénylését végző metódus.
     * @param goal A cél jégmező, amire el tervezünk mozdulni
     */
    public void move(IceUnit goal) {
        if(stamina==0) {
            return;
        }
        IceUnit from = getIceUnit();
        super.move(from, goal);     //itt megpróbál mozogni a karakter
        IceUnit iceUnitAfterMove = getIceUnit();
        if (from != iceUnitAfterMove) {     //ha sikeresen ellépett
            tire();     //ha sikeresen ellépett akkor fárad
            from.remove(this);      //amelyik IceUnitról elmozgott, onnan töröljük
            iceUnitAfterMove.accept(this);      //amelyik IceUnitra mozgott az befogadja
        }
    }

    /**
     * Karakter összeszerel.
     */
    public void assemble() {
        if(stamina==0) {
            return;
        }
        if(!components.isEmpty()) {
            components.get(0).assemble(this);
        }
    }

    /**
     * A karakter ás.
     */
    public void dig() {
        if(stamina==0) {
            return;
        }
        IceUnit iu = getIceUnit();
        digger.dig(this, iu);   //az ásást az ásóeszközzel végzi
    }

    /**
     * A karakter sátrat épít.
     */
    public void buildTent() {
        if(stamina==0) {
            return;
        }
        IceUnit iu = getIceUnit();
        if(tent!=null) {    //csak akkor tud építeni, ha ténylegesen van nála sátor
            iu.build(tent);     //megpróbálja felépíteni
            if (iu.getBuilding() == tent) {
                tire();     //ha sikerült felpítenie, akkor ettől elfárad
                GameController.getInstance().addCommandable(tent);
                tent = null;
            }
        }
    }

    /**
     * Karakter képességet használ.
     * @param iu ez az IceUnit kapcsolódik a képességhez
     */
    public abstract void useAbility(IceUnit iu);

    /**
     * A metódus feladata, hogy levezényelje azt az eseményt,
     * amikor egy karakter összetalálkozik egy másik karakterrel.
     * @param c ezzel a Character típusú objektummal találkozik a karakter
     */
    @Override
    public void hitBy(Character c) {
        //Ilyenkor nem történik semmi.
    }

    /**
     * A metódus feladata, hogy levezényelje azt az eseményt,
     * amikor egy karakter összetalálkozik egy jegesmedvével,
     * azaz arra az egységre amin a karakter áll odamegy egy jegesmedve típusú objektum.
     * @param ib ezzel az IceBear típusú objektummal találkozik a karakter
     */
    @Override
    public void hitBy(IceBear ib) {
        getIceUnit().searchShelterFromIceBear(this);
    }

    /**
     * A metódus hatásával jelezhető, hogy a karakter vízbeesett, és emiatt megfulladhat.
     * Jelen esetben ez azt jelenti, hogy a víztől védő tárgy nem tudta megmenteni a karaktert.
     */
    @Override
    public void drowning() {
        getIceUnit().callForPullout(this);
    }


    /**
     * A metódus felelőssége, hogy meghívásával az objektum munkavégző képességét csökkentsük.
     * Tehát majd az objektum stamina-ját kell csökkenteni a karakter osztály esetében.
     */
    @Override
    public void tire() {
        stamina--;
    }

    /**
     * A metódus felelőssége, hogy meghívásával az objektum munkavégző képességét növeljük.
     * Tehát majd az objektum stamina-ját kell növelni a karakter osztály esetében
     */
    @Override
    public void rest() {
        stamina = defaultStamina;
    }

    /**
     * Implementáljuk a Pullable interfész osztály függvénynét, aminek a felelőssége helyesen reagálni arra,
     * hogy az objektumot elhúzták.
     *
     * @param goal A cél jégmező, ahová elhúzták az objektumot
     */
    public void reposition(IceUnit goal) {
        getIceUnit().remove(this);      //Jelezzük, hogy már nem vagyunk az adott jégtáblán
        setIceUnit(goal);           // jelezzük a karakternek, hogy áthelyeződött, beállítjuk az új tábláját
        goal.accept(this);     //jelezzük a célnak, hogy tároljon el minket
    }

    /**
     * A függvény feladata, hogy a paraméterként kapott komponenst hozzáadjuk a tárolni kívánt komponenseinkhez.
     * @param c a hozzáaadni kívánt komponens
     */
    @Override
    public void addToComponents(Component c) {
        components.add(c);
    }

    /**
     * A metódus feladata, hogy amennyiben a paraméterként kapott komponenst tárolja a mester, azt el kell dobnia.
     * @param c az eltávolítandó komponens
     */
    @Override
    public void removeComponent(Component c) {
        components.remove(c);
    }

    /**
     * Visszaadja a komponenseit a karakternek.
     * @return a karakternél levő komponensek összessége
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * A metódus felelőssége, hogy kommunikációt biztosítson az implementáló osztálynak a többi mesterrel,
     * akiktől így elkéri használatra az ő komponenseiket.
     * @return visszaadja a lekért komponensek összeségét
     */
    public ArrayList<Component> getOtherMastersComponents() {
        IceUnit iu = getIceUnit();    //lekéri azt az IceUnit-ot amin áll
        ArrayList<Component> components = iu.askForComponents();  //lekéri a komponseket
        return components;  //visszatér a komponenensek összességével
    }

    /**
     * Ha a menekültnek nincsen fedezéke a katasztrófák ellen, akkor nincsen neki protekciója és testhőt veszít.
     */
    public void protectionFailed() {
        reduceBodyHeat(1);
    }

    /**
     * Nem tudta megvédeni semmi a karaktert a jegesmedvétől, ezért meghal, a játék véget ér.
     */
    @Override
    public void protectionFailedFromBearFailed() {
        GameController.getInstance().endGame(false);
    }


    /**
     * Katasztrófa sújtja az objektumot, megpróbál menedéket keresni.
     */
    public void struckByDisaster() {
        IceUnit iu = getIceUnit();
        iu.searchShelter(this);
    }

    /**
     * elfogyasztja az objektumot
     * @param cons a felvett elfogyasztható objektum
     */
    @Override
    public void equipConsumable(Consumable cons) {
        cons.consume(this);
    }

    /**
     * bállítja az ásást végző attribútumát arra, amit most kapott paraméternek
     * @param d a felvett ásó
     */
    @Override
    public void equipDigger(Digger d) {
        digger = d;
    }

    /**
     * Beállítja a karakter alapértelmezett ásást végző eszközét.
     */
    public void setDefaultDigger() {
        digger = new BareHand(null);
    }

    /**
     * beállítja a vízvédő attribútumát arra, amit most kapott paraméternek
     * @param wp a felvett búvárruha
     */
    @Override
    public void equipWaterProtecter(WaterProtecter wp) {
        waterProtecter = wp;
    }

    /**
     * beállítja a húzást végző attribútumát arra, amit most kapott paraméternek
     * @param p a felvett húzóeszköz
     */
    @Override
    public void equipPuller(PullerDevice p) {
        pullerDevice = p;
    }

    /**
     * A karakter hozzájut egy sátor tárgyhoz.
     * @param t a sátor objektum, amihez a karakter hozzájut
     */
    public void equipTent(Tent t) {
        tent = t;
    }

    /**
     * Eltávolítja a karaktertől az általa birtokolt sátrat.
     */
    public void removeTent() {
        tent = null;
    }

    /**
     * Visszaadja a stamina értékét.
     * @return a stamina értéke
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * A metódussal jelezhető, hogy sikertelen volt a karakter elhúzása.
     * Ebben az esetben ez azt jelenti, hogy a karakter leesett.
     */
    @Override
    public void helpFailed() {
        fallenDown();
    }

    /**
     * A függvény az objektum adataiból létrehoz egy ParsedObject példányt, amivel dolgozva egyszerűen feldolgozhatjuk
     * az objektum adatait.
     * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
     */
    @Override
    public ParsedObject convertToParsedObject() {
        ParsedObject obj = super.convertToParsedObject();
        obj.add("stamina", String.valueOf(getStamina()));
        obj.add("digger", digger.getID());
        obj.add("waterProtecter", waterProtecter.getID());
        obj.add("pullerDevice", pullerDevice.getID());
        obj.add("tent", tent == null ? "null" : tent.getID());
        obj.add("components");
        for (Component c : components) {
            obj.add("components", c.getID());
        }
        return obj;
    }

    /**
     * Visszaadja a karakter ásóeszközét
     * @return karakter ásóeszköze
     */
    public Digger getDigger() {
        return digger;
    }

    /**
     * Visszaadja a karakter húzó eszközét
     * @return Karakter húzóeszköze
     */
    public PullerDevice getPullerDevice() {
        return pullerDevice;
    }

    /**
     * Visszaadja a karakter védőeszközét
     * @return Védőeszköz
     */
    public WaterProtecter getWaterProtecter() {
        return waterProtecter;
    }

    /**
     * Visszaadja a karakter sátrát
     * @return Sátor
     */
    public Tent getTent() {
        return tent;
    }

    /**
     * Állóképesség beállítása
     * @param s Állóképesség
     */
    public void setStamina(int s) {
        stamina = s;
    }

}



