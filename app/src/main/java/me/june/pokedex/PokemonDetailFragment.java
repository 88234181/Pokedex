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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

        //grab the pokemon id
        Intent intent = getActivity().getIntent();
        int pokemonId = intent.getExtras().getInt(POKEMON_ID);

        //grab the pokemon info from the local assets according to the id
        XMLParser parser = new XMLParser(getContext());
        Document doc = parser.getDomElement();
        Element pokemon = (Element) doc.getElementsByTagName("pokemon").item(pokemonId-1);


        //locate all the views that needs to be changed in the layout
        TextView id = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailId);
        TextView name = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailName);
        ImageView type1 = (ImageView) fragmentLayout.findViewById(R.id.pokedexDetailType1);
        ImageView type2 = (ImageView) fragmentLayout.findViewById(R.id.pokedexDetailType2);
        TextView stamina = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailStamina);
        TextView attack = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailAttack);
        TextView defense = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailDefense);
        TextView candyToEvolve = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailCandyToEvolve);
        ImageView image = (ImageView) fragmentLayout.findViewById(R.id.pokedexDetailImage);

        //setting all the values
        id.setText(String.valueOf(pokemonId));
        name.setText(parser.getValue(pokemon, "name"));
        type1.setImageResource(Pokemon.getTypeDrawable(parser.getValue(pokemon, "type1")));
        if(!parser.getValue(pokemon, "type2").isEmpty()){//some pokemon only has one type
            type2.setImageResource(Pokemon.getTypeDrawable(parser.getValue(pokemon, "type2")));
        }
        stamina.setText(parser.getValue(pokemon, "stamina"));
        attack.setText(parser.getValue(pokemon, "attack"));
        defense.setText(parser.getValue(pokemon, "defense"));
        candyToEvolve.setText(parser.getValue(pokemon, "candyToEvolve"));
        image.setImageResource(Pokemon.getAssociateDrawable(pokemonId));

        //programmatically generate the resistance image in the resistance layout
        LinearLayout resistanceLayout = (LinearLayout) fragmentLayout.findViewById(R.id.pokedexDetailResistanceLayout);
        NodeList resistances = pokemon.getElementsByTagName("resistance");
        for(int i = 0; i < resistances.getLength(); i++){
            Element e = (Element) resistances.item(i);

            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(Pokemon.getAssociateDrawable(pokemonId));

        }
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(Pokemon.getAssociateDrawable(pokemonId));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(2,2,2,2);
        resistanceLayout.addView(imageView);
        return fragmentLayout;
    }
}
