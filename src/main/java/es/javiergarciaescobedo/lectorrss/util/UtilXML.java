package es.javiergarciaescobedo.lectorrss.util;

import es.javiergarciaescobedo.lectorrss.beans.Rss;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class UtilXML {
    
    public static Rss procesarRss(String strUrlRss) throws JAXBException, MalformedURLException {
        JAXBContext contexto = JAXBContext.newInstance(Rss.class);
        Unmarshaller unmarshaller = contexto.createUnmarshaller();
        URL url = new URL(strUrlRss);
        Rss rss = (Rss)unmarshaller.unmarshal(url);      
        return rss;
    }
    
}
