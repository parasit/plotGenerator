package org.ibu.rpgforge.plotgui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.ibu.rpgforge.jplotgenerator.PlotParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class HelloController implements Initializable
{
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    @FXML private WebView mainWebView;
    @FXML private ListView mainNPCList;
    @FXML private Button mainRefreshButton;
    @FXML private TextArea mainSourceTextArea;

    public class TextFileReader {

        @SuppressWarnings("NestedAssignment")
        public String read(File file) {
            List<String> lines = new ArrayList<String>();
            String result = "";
            String line;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    //lines.add(line);
                    result += line + "\n";
                }
                br.close();
            } catch (IOException ex) {
                //Logger.getLogger(TextFileReader.class.getName()).log(Level.SEVERE, null, ex);
            }

            return result;
        }
    }

    @FXML
    public void mainFrameRefresh(ActionEvent event) {
        PlotParser parser = new PlotParser("CDD4");
        this.webEngine.loadContent(parser.parse(this.mainSourceTextArea.getText()));
    }

    @FXML
    public void sourceTextAreaLoad(ActionEvent event) {
        this.mainSourceTextArea.setText(new TextFileReader().read(new File("CDD4/bb_random.npc")));
    }

    private WebEngine webEngine;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert mainWebView != null : "fx:id=\"mainWebView\" was not injected: check your FXML file 'simple.fxml'.";


        // initialize your logic here: all @FXML variables will have been injected
        webEngine = mainWebView.getEngine();
        this.mainSourceTextArea.setText(new TextFileReader().read(new File("CDD4/bb_random.npc")));
        //webEngine.load("http://natemat.pl");
        //loadContent("http://natemat.pl");
    }

    public void loadContent(URL location) {
        this.webEngine.load(location.toString());
    }

    public void loadContent(String content) {
        this.webEngine.loadContent(content);
    }

}