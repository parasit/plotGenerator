package org.ibu.rpgforge.plotgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ibu.rpgforge.jplotgenerator.PlotParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Created by bart on 18.08.15.
 */
public class MainGuiApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainGuiApp.class);
    private HelloController controller;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting Hello JavaFX and Maven demonstration application");

        String fxmlFile = "/fxml/hello.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        controller = (HelloController) loader.getController();
        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 800, 600);
        scene.getStylesheets().add("/styles/styles.css");
        //controller.loadContent(new URL("http://onet.pl"));

        PlotParser parser = new PlotParser("CDD4");
        controller.loadContent(parser.fileParse("CDD4/bb_random.npc"));

        stage.setTitle("jPlot Generator");
        stage.setScene(scene);

        stage.show();
    }
}
