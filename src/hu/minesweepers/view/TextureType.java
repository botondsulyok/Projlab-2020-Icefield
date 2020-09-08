package hu.minesweepers.view;

/**
 * Ilyen textúratípusok lehetnek.
 */
public enum TextureType {
    CARTRIDGE("cartridge"),
    DIVINGSUIT("suit"),
    ESKIMO("eskimo"),
    EXPLORER("explorer"),
    FOOD("food"),
    ICEBEAR("icebear"),
    IGLOO("igloo"),
    LIGHT("light"),
    PISTOL("pistol"),
    ROPE("rope"),
    SHOVEL("shovel"),
    BREAKABLE("breakable"),
    TENT("tentbuilding"),
    PICKABLETENT("tent");

    /**
     * Eltárolja a textúratípust stringben.
     */
    private String type;

    /**
     * Az textúratípus konstruktora
     * @param type A stringben kapott textúratípus.
     */
    TextureType(String type) {
        this.type = type;
    }

    /**
     * Átalakítja a stringben kapott textúratípust enum textúratípussá és azt adja vissza.
     * @param type A stringben kapott textúratípus.
     * @return Visszaadja az enum textúratípust. Ha nincs a paraméterrel megegyező textúra, akkor null-t ad vissza.
     */
    public static TextureType fromString(String type) {
        for (TextureType i : TextureType.values()) {
            if (i.type.equalsIgnoreCase(type)) {
                return i;
            }
        }
        return null;
    }
}
