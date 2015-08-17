package org.ibu.rpgforge.jplotgenerator;

import java.io.IOException;
import java.util.*;

/**
 * Created by bart on 29.07.15.
 */
public class DataTable {
    private int dataIndex[];
    private LinkedHashMap dataValues;

    public DataTable() {
        this.dataValues = new LinkedHashMap();
    }

    public void addValue(String value, int wage) {
        this.dataValues.put(value,new Integer(wage));
        regenerateIndex();
    }

    private void regenerateIndex() {
        if (this.dataValues.size() > 0) {
            this.dataIndex = new int[this.dataValues.size()];
        }
    }

    public String getRandomElement() {
        Random random = new Random();
        return this.getElementByValue(random.nextInt(getSize()) + 1);
    }

    public int getSize() {
        int result = 0;
        Set dataSet = dataValues.entrySet();
        Iterator i = dataSet.iterator();
        while (i.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) i.next();
            result += (Integer) mapEntry.getValue();
        }
        return result;
    }

    public int getElementsCount() {
        return this.dataValues.size();
    }

    public String getElementByValue(int value) {
        String result = "";
        int localIndex = 1;
        int endIndex = 1;
        int keyValue = 0;

        Set dataSet = dataValues.entrySet();
        Iterator i = dataSet.iterator();
        while (i.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) i.next();
            keyValue = (Integer) mapEntry.getValue();
            if (localIndex <= value && value <= (localIndex+(keyValue-1))) {
                return mapEntry.getKey().toString();
            }
            localIndex += keyValue;
        }
        return result;
    }

    public String dumpData() {
        String result = "";
        String tResult = "";
        int localIndex = 1;
        int endIndex = 1;
        int keyValue = 0;

        Set dataSet = dataValues.entrySet();
        Iterator i = dataSet.iterator();
        while (i.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) i.next();
            keyValue = (Integer) mapEntry.getValue();
            tResult = "" + localIndex ;
            if (keyValue > 1) {
                endIndex = localIndex + (keyValue - 1);
                tResult += "-" + endIndex;
            } else {
                endIndex = localIndex;
            }
            tResult += ": " + mapEntry.getKey() + "\n";
            result += tResult;
            localIndex += keyValue;
        }
        return result;
    }

    public void loadData(DataTableReader dataReader) {
        try {
            this.dataValues = dataReader.loadData();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
