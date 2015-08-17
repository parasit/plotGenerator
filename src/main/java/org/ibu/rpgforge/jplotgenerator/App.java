package org.ibu.rpgforge.jplotgenerator;

import org.ibu.rpgforge.dicesbag.DicesBag;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args )
    {
        /*
        //PlotGenerator generator = new PlotGenerator("Hello world");
        //System.out.println(generator.generate());
        DataTable randomTable = new DataTable();

        randomTable.loadData(new TextDataTableReader("CDD4/age.txt"));



        randomTable.addValue("Miecz",1);
        randomTable.addValue("Topór", 1);
        randomTable.addValue("Włócznia", 1);
        randomTable.addValue("Sztylet", 3);
        randomTable.addValue("Pałka", 1);
        System.out.println("Size: " + randomTable.getSize());
        System.out.println(randomTable.dumpData());

        for (int x=1;x <= randomTable.getSize(); x++) {
            System.out.println(x + " " + randomTable.getElementByValue(x));
        }


        DicesBag db = new DicesBag();
        */
        PlotParser parser = new PlotParser("CDD4");
        //System.out.println(parser.parse("bla bla {{ {{ d3 }}, bron.txt}} bla bla {{ sex.txt }}"));
        System.out.println(parser.fileParse("CDD4/bb_random.npc"));
    }
}
