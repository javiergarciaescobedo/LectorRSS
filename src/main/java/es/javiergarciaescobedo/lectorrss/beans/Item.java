package es.javiergarciaescobedo.lectorrss.beans;

import javax.xml.bind.annotation.XmlElement;

public class Item {
    
    private String title;
    private String description;
    private String encoded;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEncoded() {
        return encoded;
    }

    @XmlElement(namespace="http://purl.org/rss/1.0/modules/content/")
    public void setEncoded(String encoded) {
        this.encoded = encoded;
    }

    
}
