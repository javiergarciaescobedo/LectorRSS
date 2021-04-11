module es.javiergarciaescobedo.lectorrss {
    requires javafx.controls;
    requires java.xml.bind;
    requires jakarta.activation;
    requires java.activation;
    requires java.logging;
    requires javafx.web; // Para WebView de JavaFX
    
    opens es.javiergarciaescobedo.lectorrss.beans to java.xml.bind;
    exports es.javiergarciaescobedo.lectorrss;
}
