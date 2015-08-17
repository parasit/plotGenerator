package org.ibu.rpgforge.jplotgenerator;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by bart on 11.08.15.
 */
public class DataTableReader implements LoadDataTable {
    static LinkedHashMap tmpValues;

    public DataTableReader() {
        this.tmpValues = new LinkedHashMap();
    }

    public LinkedHashMap loadData() throws IOException {
        return this.tmpValues;
    }
}
