package org.ibu.rpgforge.dicesbag;

/**
 * Created by bart on 11.08.15.
 */
import java.util.Random;

public class DicesBag {
    int sides;
    Random random;

    public DicesBag() {
        this.random = new Random(System.nanoTime());
    }

    public int diceThrow(int sides) {
        return this.random.nextInt(sides) + 1;
    }

    private Pair<Integer> parseDiceString(String diceString) {
        int sides, times;
        String tmp[];
        if (diceString.matches("^\\d*[dDkK]\\d*$")) {
            tmp = diceString.split("[dDkK]");
            sides = new Integer(tmp[1]);
            if (tmp[0].length() != 0) {
                times = new Integer(tmp[0]);
                if (times < 1) {
                    times = 1;
                }
            } else {
                times = 1;
            }
            return new Pair<Integer>(times, sides);
        }
        return null;
    }

    public int diceThrow(String dices) {
        int result = 0;
        Pair<Integer> diceParsed;
        diceParsed = parseDiceString(dices);
        result = doThrows(diceParsed.times(),diceParsed.sides());
        return result;
    }

    public int doThrows(Pair<Integer> dices) {
        return this.doThrows(dices.times(),dices.sides());
    }

    public int doThrows(int times, int sides) {
        int result = 0;
        for (int x = 0; x < times; x++) {
            result += diceThrow(sides);
        }
        return result;
    }

    public int d100() {
        return this.diceThrow(100);
    }

    public int d20() {
        return this.diceThrow(20);
    }

    public int d12() {
        return this.diceThrow(12);
    }

    public int d10() {
        return this.diceThrow(10);
    }

    public int d6() {
        return this.diceThrow(6);
    }

    public int d4() {
        return this.diceThrow(4);
    }
}

class Pair<T> {
    private final T m_times;
    private final T m_sides;

    public Pair(T times, T sides) {
        m_times = times;
        m_sides = sides;
    }

    public T times() {
        return m_times;
    }

    public T sides() {
        return m_sides;
    }
}