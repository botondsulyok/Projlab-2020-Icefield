//package hu.minesweepers.modell.tests;
//
//import hu.minesweepers.modell.character.playable.Eskimo;
//import hu.minesweepers.modell.character.playable.Explorer;
//import hu.minesweepers.modell.character.Character;
//import hu.minesweepers.modell.tile.IceUnit;
//import hu.minesweepers.modell.tile.UnstableIceUnit;
//
//public class testFallAndPull {
//
//    /**
//     * A tesztben résztvevő karakter, aki eshet
//     */
//    Character faller;
//
//    /**
//     * A jégmező ahonnan indul a karakter
//     */
//    IceUnit from;
//
//    /**
//     * A jégmező, ahová lépve leeshetünk
//     */
//    UnstableIceUnit breakable;
//
//    public void startTest(){
//        initialize();
//        System.out.println("Teszt kezdete");
//        //breakable.acceptStander(faller,from);
//        System.out.println("Teszt vege\n--------------------------\n");
//    }
//
//    private void initialize(){
//        System.out.println("Inicializalas");
//        boolean egyertelmu = false;     //ameddig nincs egyertelmu valasz, false
//        String str = new String();     //felhasznalo valasza
//        while(!egyertelmu) {                //amig nincs egyertelmu valasz
//            System.out.println("Eszkimo vagy Felfedezo essen le? Eszkimo : 0, Felfedezo : 1");
//            str = Logger.read();        //Beolvassuk a valaszt
//            if(str.equals("0") || str.equals("1"))
//                egyertelmu = true;      //Ha egyertelmu a valasz, akkor nem kell tobbet kerdezni
//            else System.out.println("Egyertelmu valaszt adjon kerem!");
//        }
//        if(str.equals("0"))
//            faller = new Eskimo();       //eszkimoval esunk
//        else
//            faller = new Explorer();         //felfedezovel esunk
//        from = new IceUnit();
//        breakable = new UnstableIceUnit();
//        faller.setIceUnit(breakable);       //megjegyezzük, hogy már a célon állunk
//    }
//}
