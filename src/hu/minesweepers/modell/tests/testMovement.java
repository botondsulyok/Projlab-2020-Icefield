//package hu.minesweepers.modell.tests;
//
//import hu.minesweepers.modell.character.movebehaviour.Amphibious;
//import hu.minesweepers.modell.character.movebehaviour.Walk;
//import hu.minesweepers.modell.character.playable.Eskimo;
//import hu.minesweepers.modell.character.playable.Explorer;
//import hu.minesweepers.controller.GameController;
//import hu.minesweepers.modell.character.Character;
//import hu.minesweepers.modell.tile.IceUnit;
//import hu.minesweepers.modell.tile.UnstableIceUnit;
//import java.io.IOException;
//
//public class testMovement {
//
//    /**
//     * A tesztben a kiinduló jégmező
//     */
//    IceUnit from;
//
//    /**
//     * A tesztben a cél jégmező
//     */
//    IceUnit goal;
//
//    /**
//     * A tesztben mozgó eszkimó vagy felfedező
//     */
//    Character mover;
//
//    /**
//     * A metódus hatására lefut a teszt
//     */
//    public void startTest() throws IOException {
//        System.out.println("Karakter mozog");
//        this.initialize();      //inicializaljuk a komponenseket
//        System.out.println("Teszt kezdete");
//        //mover.move(goal);
//        System.out.println("Teszt vege\n--------------------------\n");
//    }
//
//    /**
//     * Tesztkomponensek inicializalasa
//     */
//    private void initialize(){
//        System.out.println("Inicializalas");
//        boolean egyertelmu = false;     //ameddig nincs egyertelmu valasz, false
//        String str;     //felhasznalo valasza
//        int n = -1;      //a valasz szamban
//        while(!egyertelmu) {                //amig nincs egyertelmu valasz
//            System.out.println("Eszkimo vagy Felfedezo mozogjon? Eszkimo : 0, Felfedezo : 1");
//            str = Logger.read();        //Beolvassuk a valaszt
//            n = Integer.parseInt(str);      //atalakitjuk szama
//            if(n == 0 || n == 1)
//                egyertelmu = true;      //Ha egyertelmu a valasz, akkor nem kell tobbet kerdezni
//            else System.out.println("Egyertelmu valaszt adjon kerem!");
//        }
//        if(n == 0)
//            mover = new Eskimo();       //eszkimoval mozgunk
//        else
//            mover = new Explorer();         //felfedezovel mozgunk
//        //mezok inicializalasa
//        from = new IceUnit(mover);      //Inicializaljuk a kiindulo mezot, a konstruktorban megadjuk a rajta allo karaktert
//        mover.setIceUnit(from);         //a karakter a kezdeti mezon van
//        goal = new IceUnit();
//        egyertelmu = false;     //ujraallitjuk az egyertelmuseg vizsgalot
//        n = -1;         //ujraallitjuk a valaszt amit kapunk, de mint szam formatumban
//        while(!egyertelmu) {                //amig nincs egyertelmu valasz
//            System.out.println("A mozgo karakter csak szarazfoldi, vagy keteltu viselkedessel mozogjon? Szarazfoldi" +
//                    " : 0, Keteltu : 1");
//            str = Logger.read();        //Beolvassuk a valaszt
//            n = Integer.parseInt(str);      //atalakitjuk szama
//            if(n == 0 || n == 1)
//                egyertelmu = true;      //Ha egyertelmu a valasz, akkor nem kell tobbet kerdezni
//            else System.out.println("Egyertelmu valaszt adjon kerem!");
//        }
//        if(n == 0)
//            mover.changeMoveBehaviour(new Walk());
//        else
//            mover.changeMoveBehaviour(new Amphibious());
//    }
//}
