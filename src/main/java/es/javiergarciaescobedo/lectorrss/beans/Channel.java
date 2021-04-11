package es.javiergarciaescobedo.lectorrss.beans;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;

public class Channel {
    
    private String title;
    private ArrayList<Item> items = new ArrayList<Item>();  
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @XmlElement(name="item")
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    
}
