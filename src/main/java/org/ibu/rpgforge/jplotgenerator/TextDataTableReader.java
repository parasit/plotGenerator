package org.ibu.rpgforge.jplotgenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;

/**
 * Created by bart on 11.08.15.
 */
public class TextDataTableReader extends DataTableReader implements LoadDataTable {

    private URL filename;
    private LinkedHashMap tmpValues;

    public TextDataTableReader(String filename) {
        super();
        this.tmpValues = new LinkedHashMap();
        try {
            this.filename = new File(filename).toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public TextDataTableReader(URL filename) {
        super();
        this.tmpValues = new LinkedHashMap();
        this.filename = filename;
    }

    private void parseLine(String line) {
        String tmp[];

        if (line.matches("^\\d+;.*$")) {
            tmp = line.split(";");
            this.tmpValues.put(tmp[1], new Integer(tmp[0]));
        } else if (line.matches("^\\s*\\d+-\\d+;.*$")) {
            tmp = line.split(";");
            String tmp2[] = tmp[0].split("-");
            this.tmpValues.put(tmp[1], (new Integer(tmp2[1].trim())+1) - new Integer(tmp2[0].trim()));
        } else {
            this.tmpValues.put(line,1);
        }
    }

    public LinkedHashMap loadData() throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(filename.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            //System.out.println(inputLine);
            parseLine(inputLine);
        in.close();
        return this.tmpValues;
    }
}
