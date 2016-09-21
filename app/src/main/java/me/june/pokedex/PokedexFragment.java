package me.june.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/20.
 */
public class PokedexFragment extends ListFragment {

    ArrayList<Pokemon> pokedex;
    PokemonAdapter pokemonAdapter;
    EditText pokedexSearch;

    private static final String POKEMON_ID = "POKEMON ID";
    private static final String POKEMON_NAME = "POKEMON NAME";

    public PokedexFragment(){

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        XMLParser parser = new XMLParser(getContext());
        Document doc = parser.getPokedexDom();

        NodeList pokemons = doc.getElementsByTagName("pokemon");
        pokedex = new ArrayList<>();
        pokedexSearch = (EditText) getActivity().findViewById(R.id.pokedexSearch);

        for(int i = 0; i < pokemons.getLength(); i++){
            Element e = (Element) pokemons.item(i);
            String id = parser.getValue(e, "id");
            String name = parser.getValue(e, "name");
            Pokemon pokemon = new Pokemon(id, name);

            pokedex.add(pokemon);
        }

        pokemonAdapter = new PokemonAdapter(getActivity(), pokedex);

        setListAdapter(pokemonAdapter);

        pokedexSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                PokedexFragment.this.pokemonAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

        startActivity(intent);
    }

}
