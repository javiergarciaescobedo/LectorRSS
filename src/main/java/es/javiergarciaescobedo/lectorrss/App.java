package es.javiergarciaescobedo.lectorrss;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane paneRoot = new BorderPane();
        var scene = new Scene(paneRoot, 640, 480);
        stage.setScene(scene);
        stage.show();
        
        paneRoot.setTop(RssView.panelSuperior());
        paneRoot.setCenter(RssView.panelCentral());
        paneRoot.setStyle("-fx-padding: 10");
        
        RssView.textFieldUrlRss.setText("https://actualidad.radioubrique.com/feed");
    }
    
    
    public static void main(String[] args) {
        launch();
    }

}