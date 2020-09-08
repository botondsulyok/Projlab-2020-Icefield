package hu.minesweepers.controller.parser;

/**
 * Az entitástípusok felsorolása.
 */
public enum EntityType {

    /**
     * Az entitástípus eszkimó
     */
    ESKIMO("eskimo"),

    /**
     * Az entitástípus felfedező
     */
    EXPLORER("explorer"),

    /**
     * Az entitástípus jegesmedve
     */
    BEAR("icebear");

    /**
     * Eltárolja az entitástípust stringben.
     */
    private String type;

    /**
     * Az entitástípus konstruktora
     * @param type A stringben kapott entitástípus.
     */
    EntityType(String type) {
        this.type = type;
    }


    /**
     * Átalakítja a stringben kapott entitástípust enum entitástípussá és azt adja vissza.
     * @param type A stringben kapott entitástípust.
     * @return Visszaadja az enum entitástípust. Ha nincs a paraméterrel megegyező entitás, akkor null-t ad vissza.
     */
    public static EntityType fromString(String type) {
        for (EntityType e : EntityType.values()) {
            if (e.type.equalsIgnoreCase(type)) {
                return e;
            }
        }
        return null;
    }
}
