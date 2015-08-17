package org.ibu.rpgforge.plotgui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable
{
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    @FXML private WebView mainWebView;
    @FXML private ListView mainNPCList;

    private WebEngine webEngine;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert mainWebView != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
        webEngine = mainWebView.getEngine();
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