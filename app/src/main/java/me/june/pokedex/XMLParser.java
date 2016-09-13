package me.june.pokedex;


import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by j8823_000 on 8/16/2016.
 */
public class XMLParser {

    private Context context;
    private int id;

    public XMLParser(Context context, int id){
        this.context = context;
        this.id = id;
    }

    public XMLParser(Context context){
        this.context = context;
    }

    /**convert xml file to DOM structure
     *
     * @return Document
     */
    public Document getPokedexDom(){
        Document doc = null;
        String file = "pokedex.xml";
        try{

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            InputStream raw = context.getAssets().open(file);
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(raw);
        } catch (ParserConfigurationException e){
            Log.e("Error: ", e.getMessage());
            e.printStackTrace();
            return null;
        } catch (SAXException e){
            Log.e("Error: ", e.getMessage());
            e.printStackTrace();
            return null;
        } catch (IOException e){
            Log.e("Error: ", e.getMessage());
            e.printStackTrace();
            return null;
        }

        return doc;
    }


    /**get each xml child element value by passing element node name
     *
     * @param element
     * @return
     */
    public final String getElementValue(Node element){
        Node child;

        if(element != null){
            if(element.hasChildNodes()){
                for(child = element.getFirstChild(); child != null; child = child.getNextSibling()){
                    return child.getNodeValue();
                }
            }
        }
        return "";
    }

    public List<String> getElementValues(Node element){
        Node child;
        List<String> values = new ArrayList<>();

        if(element != null){
            if(element.hasChildNodes()){
                for(child = element.getFirstChild(); child != null; child = child.getNextSibling()){
                    values.add(child.getNodeValue());
                }
            }
        }
        return values;
    }


    public String getValue(Element item, String str){
        NodeList n = item.getElementsByTagName(str);
        return this.getElementValue(n.item(0));
    }

    /**
     *
     * @return the pokemon element according to id
     */
    public Element getPokemon(){
        Document pokedexDom = getPokedexDom();
        Element pokemon = (Element) pokedexDom.getElementsByTagName("pokemon").item(id-1);
        return pokemon;
    }

    /**
     *
     * @return the values of all the resistances
     */
    public List<String> getResistanceValues(){
        Node resistance = getPokemon().getElementsByTagName("resistance").item(0);
        return getElementValues(resistance);
    }
}
