package com.example.project_htetwaiyan_2;

//import com.example.project_htetwaiyan_2.Controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    /*@Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(("planet0.png")));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("StartPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("SolarSim");
        stage.setScene(scene);
        stage.show();
    }*/

    @Override
    public void start(Stage stage) {

        stage.getIcons().add(new Image(("planet0.png")));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/project_htetwaiyan_2/View/StartPage.fxml"));

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            stage.setTitle("SolarSim");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}