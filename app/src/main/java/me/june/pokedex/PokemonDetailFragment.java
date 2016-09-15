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
import org.w3c.dom.NodeList;

import java.util.List;

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
        int pokemonId = Integer.parseInt(intent.getExtras().getString(POKEMON_ID));

        //grab the pokemon info from the local assets according to the id
        XMLParser parser = new XMLParser(getContext(), pokemonId);
        Element pokemon = parser.getPokemon();

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
        List<String> resistances = parser.getResistanceValues();
        for(int i = 0; i < resistances.size(); i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(Pokemon.getTypeDrawable(resistances.get(i)));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(2,2,2,2);
            imageView.setLayoutParams(layoutParams);
            resistanceLayout.addView(imageView);
        }
        //programmatically generate the weakness image in the resistance layout
        LinearLayout weaknessLayout = (LinearLayout) fragmentLayout.findViewById(R.id.pokedexDetailWeaknessLayout);
        List<String> weaknesses = parser.getWeaknessValues();
        for(int i = 0; i < resistances.size(); i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(Pokemon.getTypeDrawable(weaknesses.get(i)));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(2,2,2,2);
            imageView.setLayoutParams(layoutParams);
            weaknessLayout.addView(imageView);
        }

        return fragmentLayout;
    }
}
