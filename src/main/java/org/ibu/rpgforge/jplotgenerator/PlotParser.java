package org.ibu.rpgforge.jplotgenerator;

import org.ibu.rpgforge.dicesbag.DicesBag;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bart on 12.08.15.
 */
public class PlotParser {

    DicesBag diceBag;
    String projectName;

    public PlotParser(String projectName) {
        super();
        this.projectName = projectName;
        this.diceBag = new DicesBag();
    }

    public String includeData(String source) {
        DataTable randomTable = new DataTable();
        String tmpResult = "";
        String tmpSplit[];
        String datasource;
        Integer count = 0;

        source = source.substring(2,source.length()-2).trim();

        if ( source.matches("^\\d+\\s*,\\s*\\S*$") ) {
            tmpSplit = source.split(",");
            count = new Integer(tmpSplit[0].trim());
            datasource = tmpSplit[1].trim();
        } else{
            datasource = source;
            count = 1;
        }

        randomTable.loadData(new TextDataTableReader(this.projectName + "/" + datasource));

        for ( Integer x = 0; x < count; x++ ) {
            tmpResult += new PlotParser(this.projectName).parse(randomTable.getRandomElement()).trim();
            if ( x+1 < count ) { tmpResult += ", "; }
        }
        return tmpResult;
    }

    public String doDiceThrow(String source) {
        String tmpResult = "";
        String diceString = source.substring(2,source.length()-2).trim();
        tmpResult = "" + diceBag.diceThrow(diceString);
        return tmpResult;
    }

    public String checkDicePattern(String text) {
        String result = "";
        int count=0;
        int tEnd=0;

        Pattern pattern = Pattern.compile("\\{\\{\\s*\\d*[dDkK]\\d*\\s*\\}\\}"); // {{ 1D6 }}

        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            count++;
            result += text.substring(tEnd, matcher.start());
            tEnd = matcher.end();
            result += doDiceThrow(matcher.group(0));
        }
        result += text.substring(tEnd);
        return result;
    }

    public String checkInclude(String text) {
        String result = "";
        int count=0;
        int tEnd=0;

        Pattern pattern = Pattern.compile("\\{\\{\\s*\\d*\\s*,*\\s*\\S+\\s*\\}\\}"); // {{ text }} lub {{ 12, text }}

        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            count++;
            result += text.substring(tEnd,matcher.start());
            tEnd = matcher.end();
            result += includeData(matcher.group(0));
        }
        result += text.substring(tEnd);
        return result;
    }

    public String fileParse(String fileName) {
        String tmpResult = "";
        URL fileNameURL = null;
        try {
            fileNameURL = new File(fileName).toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(fileNameURL.openStream()));
            String inputLine;
            PlotParser parser = new PlotParser(this.projectName);
            while ((inputLine = in.readLine()) != null)
                //System.out.println(inputLine);
                tmpResult += parser.parse(inputLine);
            in.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return tmpResult;
    }

    public String checkFileInclude(String text) {
        String result = "";
        int count=0;
        int tEnd=0;

        Pattern pattern = Pattern.compile("\\[\\[\\s*\\S+\\s*\\]\\]"); // [[ text ]]

        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            count++;
            result += text.substring(tEnd,matcher.start());
            tEnd = matcher.end();
            result += includeData(matcher.group(0));
        }
        result += text.substring(tEnd);
        return result;
    }

    public String parse(String text) {
        String result = text;
        result = checkDicePattern(result); // {{ 3d6 }}
        result = checkInclude(result); // {{ text }}
        result = checkFileInclude(result); // [[ text ]]

        return result;
    }
}
