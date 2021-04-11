package es.javiergarciaescobedo.lectorrss;

import es.javiergarciaescobedo.lectorrss.beans.Item;
import es.javiergarciaescobedo.lectorrss.beans.Rss;
import es.javiergarciaescobedo.lectorrss.util.HtmlManipulator;
import es.javiergarciaescobedo.lectorrss.util.UtilXML;
import java.net.MalformedURLException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javax.xml.bind.JAXBException;

public class RssView {
    
    public static TextField textFieldUrlRss = new TextField();
    private static Label labelTituloChannel = new Label();
    private static VBox vboxItemList = new VBox();
    private static WebView webView = new WebView();
    
    public static VBox panelSuperior() {        
        Button buttonProcesarRss = new Button("Procesar RSS");
        HBox hbox = new HBox(textFieldUrlRss, buttonProcesarRss);
        HBox.setHgrow(textFieldUrlRss, Priority.ALWAYS);
        hbox.setSpacing(10);
        VBox vbox = new VBox(hbox, labelTituloChannel);
        vbox.setSpacing(10);
        buttonProcesarRss.setOnAction((t) -> {
            leerContenidoRss();
        });
        return vbox;
    }
    
    public static HBox panelCentral() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setMinWidth(300);
        scrollPane.setPrefWidth(300);
        scrollPane.setContent(vboxItemList);
        HBox hBox = new HBox(scrollPane, webView);
        return hBox; 
    }

    private static void leerContenidoRss() {
        try {
            Rss rss = UtilXML.procesarRss(textFieldUrlRss.getText());
            labelTituloChannel.setText(rss.getChannel().getTitle());
            labelTituloChannel.setFont(Font.font(24));
            for(Item item : rss.getChannel().getItems()) {
                Label labelItemTitle = new Label(item.getTitle());
                labelItemTitle.setFont(Font.font(18));
                labelItemTitle.setWrapText(true);
                String description = HtmlManipulator.replaceHtmlEntities(item.getDescription());
                Label labelItemDescription = new Label(description);
                labelItemDescription.setWrapText(true);
                VBox vBoxItem = new VBox();
                vBoxItem.getChildren().addAll(labelItemTitle, labelItemDescription);
                vboxItemList.getChildren().add(vBoxItem);
                vBoxItem.setOnMouseClicked((t) -> {
                    webView.getEngine().loadContent(item.getEncoded());
                });
            }
        } catch (JAXBException ex) {
            App.mostrarError("El formato del contenido RSS es incorrecto", ex);
        } catch (MalformedURLException ex) {
            App.mostrarError("La dirección del RSS es incorrecta", ex);
        } catch (Exception ex) {
            App.mostrarError("Se ha producido un error desconocido", ex);
        }
    }

}
