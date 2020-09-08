package hu.minesweepers.modell.items.components;

import hu.minesweepers.modell.character.ComponentMaster;
import hu.minesweepers.modell.command.ParsedObject;

import java.util.ArrayList;

/**
 * Az AlarmPistolComponents absztrakt ősosztály, gyakorlatilag egy olyan tárgyat reprezentál,
 * ami szükséges a jelzőfényespisztoly összeszereléséhez.
 * Felelőssége az, hogy a jelzőfényespisztoly komponenseként
 * belőle összeállítható legyena jelzőfényespisztoly.
 */
public abstract class AlarmPistolComponent extends Component {

    /**
     * AlarmPistolComponent osztály konstruktora
     * @param unique egyedi azonosító
     * @param common autogenerált azonosító
     */
    public AlarmPistolComponent(String unique, String common, String type){
        super(unique, common, type);
    }

    /**
     * Utasítjuk a komponenst, hogy álljon össze a céltárgyává.
     * @param cc a tárgy birtokosa
     */
    @Override
    public void assemble(ComponentMaster cc) {
        ArrayList<Component> components = cc.getOtherMastersComponents();   //lekéri az összes komponenst
        boolean hasPistol = false;  //azt nézzük, hogy a pisztoly rendelkezésre áll-e az összeszereléshez
        boolean hasCartridege = false;  //azt nézzük, hogy a töltény rendelkezésre áll-e az összeszereléshez
        boolean hasLight = false;   //azt nézzük, hogy a fény rendelkezésre áll-e az összeszereléshez
        for(Component c : components) {
            String temp = c.getType(); //lekérdezzük a komponens típusát
            if(temp.equals("Pistol")) {
                hasPistol = true;   //ha a lekért komponens pisztoly, akkor az rendelkezésre áll
            }
            else if(temp.equals("CARTRIDGE")) {
                hasCartridege = true;   //ha a lekért komponens töltény, akkor az rendelkezésre áll
            }
            else if(temp.equals("Light")) {
                hasLight = true;    //ha a lekért komponens fény, akkor az rendelkezésre áll
            }
        }
        if(hasPistol && hasCartridege && hasLight) {
            AlarmPistol alarmPistol = new AlarmPistol();  //ha rendelkezésre áll mindhárom alkatrész, akkor létrehozzuk a jelzőpisztolyt
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
        obj.add("type", getType());
        return obj;
    }
}
