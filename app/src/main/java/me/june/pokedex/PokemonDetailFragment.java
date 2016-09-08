package me.june.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Created by Administrator on 2016/8/23.
 */
public class PokemonDetailFragment extends Fragment{

    private static final String POKEMON_ID = "POKEMON ID";

    public PokemonDetailFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup convertView, Bundle savedInstanceState){
        View fragmentLayout = inflater.inflate(R.layout.pokemon_detail_fragment, convertView, false);

        //grab the pokemon info from the local assets
        XMLParser parser = new XMLParser(getContext());
        Document doc = parser.getDomElement();
        NodeList pokemons = doc.getElementsByTagName("pokemon");

        Intent intent = getActivity().getIntent();
        int pokemonId = intent.getExtras().getInt(POKEMON_ID);

        TextView id = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailId);
        TextView name = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailName);
        ImageView type1 = (ImageView) fragmentLayout.findViewById(R.id.pokedexDetailType1);
        ImageView type2 = (ImageView) fragmentLayout.findViewById(R.id.pokedexDetailType2);
        TextView stamina = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailStamina);
        TextView attack = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailAttack);
        TextView defense = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailDefense);
        TextView candyToEvolve = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailCandyToEvolve);
        ImageView image = (ImageView) fragmentLayout.findViewById(R.id.pokedexDetailImage);

        LinearLayout resistanceLayout = (LinearLayout) fragmentLayout.findViewById(R.id.pokedexDetailResistanceLayout);
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(Pokemon.getAssociateDrawable(pokemonId));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(2,2,2,2);
        resistanceLayout.addView(imageView);
        return fragmentLayout;
    }
}
