package hu.minesweepers.modell.control;

import hu.minesweepers.modell.command.ParsedObject;

/**
 * Parseable interfész
 * Az interfész felelőssége közös működési felületet biztosítani az olyan implementáló osztályok objektumnainak,
 * amelynek adatait fel lehet dolgozni, és azt mentési vagy vizsgálati célból lehet haszálni.
 */
public interface Parseable {

    /**
     * A függvény az objektum adataiból létrehoz egy ParsedObject példányt, amivel dolgozva egyszerűen feldolgozhatjuk
     * az objektum adatait.
     * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
     */
    ParsedObject convertToParsedObject();

}
