package org.ibu.rpgforge.dicesbag;

/**
 * Created by bart on 11.08.15.
 */

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DicesBagTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DicesBagTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( DicesBagTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        DicesBag db = new DicesBag();
        int res;

        res = db.diceThrow("d10");

        assertTrue("Dice works (1d10)", res < 11);

        res = db.diceThrow("D10");

        assertTrue("Dice works (1D10)", res < 11);

        res = db.diceThrow("k10");

        assertTrue("Dice works (1k10)", res < 11);

        res = db.diceThrow("K10");

        assertTrue("Dice works (1K10)", res < 11);

    }
}
