package com.sturdy_softwares;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 374819
 * 628473
 * 519473 luck
 * 284619
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public  static Utilities utilities;
    public  static Main_DisplayController mainDisplayController;
    public  static New_Entry_FormController newEntryController;
    public  static Edit_FormController editEntryController;
    public  static Open_FormController openform;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("jat_main"));
        stage.setTitle("Job Application Tracker");
        stage.setResizable(false);
        stage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("/JAT Logo.png")));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setRoot(Parent fxml) throws IOException {
        scene.setRoot(fxml);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException {
        App.utilities = new Utilities();
        App.mainDisplayController = new Main_DisplayController();
        mainDisplayController.loadEntries();
        App.newEntryController = new New_Entry_FormController();
        App.editEntryController = new Edit_FormController();
        App.openform = new Open_FormController();
        launch();
    }

}