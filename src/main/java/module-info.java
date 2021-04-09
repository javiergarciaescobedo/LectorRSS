module es.javiergarciaescobedo.lectorrss {
    requires javafx.controls;
    requires java.xml.bind;
    requires jakarta.activation;
    requires java.activation;
    opens es.javiergarciaescobedo.lectorrss to java.xml.bind;
    exports es.javiergarciaescobedo.lectorrss;
}
