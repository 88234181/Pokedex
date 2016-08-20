package me.june.pokedex;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/20.
 */
public class PokedexFragment extends ListFragment {

    private ArrayList<Pokemon> pokedex;
    private PokemonAdapter pokemonAdapter;

    public PokedexFragment(){

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        XMLParser parser = new XMLParser(getContext());
        Document doc = parser.getDomElement();

        NodeList pokemons = doc.getElementsByTagName("pokemon");
        pokedex = new ArrayList<>();

        for(int i = 0; i < pokemons.getLength(); i++){
            Element e = (Element) pokemons.item(i);
            String id = parser.getValue(e, "id");
            String name = parser.getValue(e, "name");
            String type1 = parser.getValue(e, "type1");
            String type2 = parser.getValue(e, "type2");
            String candyToEvolve = parser.getValue(e, "candyToEvolve");
            System.out.println(id + name + type1 + type2 + candyToEvolve);
            Pokemon pokemon = new Pokemon(id, name, type1, type2, Integer.parseInt(candyToEvolve));

            pokedex.add(pokemon);
        }

        pokemonAdapter = new PokemonAdapter(getActivity(), pokedex);

        setListAdapter(pokemonAdapter);

    }

}
