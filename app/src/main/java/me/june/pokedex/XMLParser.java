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

    /*
    public List<String> getElementValues(Node element){
        Node child;
        List<String> values = new ArrayList<>();

        NodeList childNodes = element.getElementsByTagName();
        for(int i = 0; i < childNodes.getLength(); i++){
            Node temp = childNodes.item(i);
            if(temp != null){
                if(temp.hasChildNodes()){
                    for(child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
                        values.add(temp.getNodeValue());
                    }
                }
            }
        }

        return values;
    }
    */

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

    private List<String> getValues(String e){
        List<String> values = new ArrayList<>();
        Element resistance = (Element) getPokemon().getElementsByTagName(e).item(0);
        NodeList childNodes = resistance.getElementsByTagName("item");
        for(int i = 0; i < childNodes.getLength(); i++) {
            values.add(getElementValue(childNodes.item(i)));
        }
        return values;
    }

    /**
     *
     * @return the list of values of all the resistances
     */
    public List<String> getResistanceValues(){
        return getValues("resistance");
    }
    /**
     *
     * @return the list of values of all the weaknesses
     */
    public List<String> getWeaknessValues(){
        return getValues("weakness");
    }

    /**
     *
     * @return the list of values of the ideal moveset
     */
    public List<String> getIdealMoveset(){
        return getValues("idealmoveset");
    }

    public List<Skill> getMoves(String typeOfMoves){
        List<Skill> values = new ArrayList<>();
        Element moveSet;
        if(typeOfMoves.equals("fastmoves")){
            moveSet = (Element) getPokemon().getElementsByTagName("fastmoves").item(0);
        }else{
            moveSet = (Element) getPokemon().getElementsByTagName("specialmoves").item(0);
        }

        NodeList moves = moveSet.getElementsByTagName("move");
        for(int i = 0; i < moves.getLength(); i++) {
            Element move = (Element) moves.item(i);
            Skill skill = new Skill(getValue(move, "name"), getValue(move, "type"), Integer.parseInt(getValue(move, "power")),
                    Float.parseFloat(getValue(move, "cooldown")), Integer.parseInt(getValue(move, "energy")),
                    Float.parseFloat(getValue(move, "dps")), Float.parseFloat(getValue(move, "wstab")));
            values.add(skill);
        }
        return values;
    }
}
