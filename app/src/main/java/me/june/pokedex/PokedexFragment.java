package me.june.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

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

    private static final String POKEMON_ID = "POKEMON ID";
    private static final String POKEMON_NAME = "POKEMON NAME";
    private static final String POKEMON_TYPE1 = "POKEMON TYPE1";
    private static final String POKEMON_TYPE2 = "POKEMON TYPE2";

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
            Pokemon pokemon = new Pokemon(id, name, type1, type2, Integer.parseInt(candyToEvolve));

            pokedex.add(pokemon);
        }

        pokemonAdapter = new PokemonAdapter(getActivity(), pokedex);

        setListAdapter(pokemonAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        //grab the pokemon info associated with the item we click
        Pokemon pokemon = (Pokemon) getListAdapter().getItem(position);

        //create a new intent that launches our PokemonDetailActivity
        Intent intent = new Intent(getActivity(), PokemonDetailActivity.class);

        //pass along the information of the pokemon we clicked
        intent.putExtra(POKEMON_ID, pokemon.getId());
        intent.putExtra(POKEMON_NAME, pokemon.getName());
        intent.putExtra(POKEMON_TYPE1, pokemon.getType1());
        intent.putExtra(POKEMON_TYPE2, pokemon.getType2());

        startActivity(intent);
    }

}
