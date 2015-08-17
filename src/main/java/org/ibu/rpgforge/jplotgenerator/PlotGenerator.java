package org.ibu.rpgforge.jplotgenerator;

/**
 * Created by bart on 29.07.15.
 */
public class PlotGenerator {
    private String finalResult;
    private String inputData;

    public PlotGenerator(String input) {
        this.inputData = input;
    }

    public String generate() {
        this.finalResult = this.inputData;
        return this.finalResult;
    }
}
