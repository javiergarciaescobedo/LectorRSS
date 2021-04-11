package es.javiergarciaescobedo.lectorrss;

import es.javiergarciaescobedo.lectorrss.beans.Item;
import es.javiergarciaescobedo.lectorrss.beans.Rss;
import es.javiergarciaescobedo.lectorrss.util.UtilXML;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javax.xml.bind.JAXBException;

public class RssView {
    
    private static Label labelTitulo = new Label();
    private static VBox vboxItemList = new VBox();
    public static TextField textFieldUrlRss = new TextField();
    
    public static VBox panelSuperior() {        
        Button buttonProcesarRss = new Button("Procesar RSS");
        HBox hbox = new HBox(textFieldUrlRss, buttonProcesarRss);
        HBox.setHgrow(textFieldUrlRss, Priority.ALWAYS);
        hbox.setSpacing(10);
        VBox vbox = new VBox(hbox, labelTitulo);
        vbox.setSpacing(10);
        buttonProcesarRss.setOnAction((t) -> {
            leerContenidoRss();
        });
        return vbox;
    }
    
    public static ScrollPane panelCentral() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(vboxItemList);
        return scrollPane;
    }

    private static void leerContenidoRss() {
        try {
            Rss rss = UtilXML.procesarRss(textFieldUrlRss.getText());
            labelTitulo.setText(rss.getChannel().getTitle());
            labelTitulo.setFont(Font.font(24));
            VBox vBoxItem = new VBox();
            vboxItemList.getChildren().add(vBoxItem);
            for(Item item : rss.getChannel().getItems()) {
                Label labelItemTitle = new Label(item.getTitle());
                labelItemTitle.setFont(Font.font(18));
                labelItemTitle.setWrapText(true);
                Label labelItemDescription = new Label(item.getDescription());
                labelItemDescription.setWrapText(true);
                vboxItemList.getChildren().addAll(labelItemTitle, labelItemDescription);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("El formato del contenido RSS es incorrecto");
            alert.setContentText(ex.toString());
            alert.showAndWait();
        } catch (MalformedURLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("La dirección del RSS es incorrecta");
            alert.setContentText(ex.toString());
            alert.showAndWait();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Se ha producido un error desconocido");
            alert.setContentText(ex.toString());
            alert.showAndWait();
        }
    }

}
