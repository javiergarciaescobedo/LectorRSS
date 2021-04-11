package es.javiergarciaescobedo.lectorrss;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
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
     
    public static void mostrarError(String mensaje, Exception ex) {
        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(mensaje);
        alert.setContentText(ex.toString());
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch();
    }

}