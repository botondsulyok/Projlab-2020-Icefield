package hu.minesweepers.modell.items.components;

import hu.minesweepers.modell.character.ComponentMaster;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Identifiable;
import hu.minesweepers.modell.control.Parseable;

/**
 * Az osztály feladata, hogy definiálja a komponensek működését.
 * A komponensek olyan objektumok, amik valami más objektummá összeépíthetőek.
 */
public abstract class Component extends Identifiable implements Parseable {
     /**
      * Azonosítja egyértelműen a komponens típusát.
      * @param unique egyedi azonosító
      * @param common autogenerált azonosító
      */
     private String type;

     public Component(String unique, String common, String type){
          super(unique, common);
          this.type = type;
     }

     /**
      * Visszaadja a komponens típusát.
      * @return a komponens típusa (neve)
      */
     public String getType() {
          return type;
     }


     /**
      * Utasítjuk a komponenst, hogy álljon össze a céltárgyává.
      * @param cc a tárgy birtokosa
      */
     public abstract void assemble(ComponentMaster cc);

     /**
      * A függvény hatására létrehozunk egy ParsedObject példányt, ami az objektum adatainak felhasználására kényelmes
      * felületet biztosít.
      * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
      */
     public ParsedObject convertToParsedObject() {
          ParsedObject obj = new ParsedObject();
          obj.add("id", getID());
          obj.add("type");
          return obj;
     }
}
