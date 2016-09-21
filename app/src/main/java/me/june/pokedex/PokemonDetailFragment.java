package me.june.pokedex;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    private static final String[] skillStat = {"name", "type", "power", "cooldown", "energy", "dps", "wstab"};

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

        /**
         * Locate and set all the values in pokemon detail fragment
         */
        // id of the pokemon
        TextView id = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailId);
        id.setText(intent.getExtras().getString(POKEMON_ID));
        // name of the pokemon
        TextView name = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailName);
        name.setText(parser.getValue(pokemon, "name"));
        // types of the pokemon
        ImageView type1 = (ImageView) fragmentLayout.findViewById(R.id.pokedexDetailType1);
        ImageView type2 = (ImageView) fragmentLayout.findViewById(R.id.pokedexDetailType2);
        type1.setImageResource(Pokemon.getTypeDrawable(parser.getValue(pokemon, "type1")));
        if(!parser.getValue(pokemon, "type2").isEmpty()){//some pokemon only has one type
            type2.setImageResource(Pokemon.getTypeDrawable(parser.getValue(pokemon, "type2")));
        }
        // basic stats of the pokemon
        TextView stamina = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailStamina);
        TextView attack = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailAttack);
        TextView defense = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailDefense);
        TextView candyToEvolve = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailCandyToEvolve);
        stamina.setText(parser.getValue(pokemon, "stamina"));
        attack.setText(parser.getValue(pokemon, "attack"));
        defense.setText(parser.getValue(pokemon, "defense"));
        candyToEvolve.setText(parser.getValue(pokemon, "candyToEvolve"));
        // the image of the pokemon
        ImageView image = (ImageView) fragmentLayout.findViewById(R.id.pokedexDetailImage);
        image.setImageResource(Pokemon.getAssociateDrawable(pokemonId));

        //programmatically generate the resistance image in the resistance layout
        LinearLayout resistanceLayout = (LinearLayout) fragmentLayout.findViewById(R.id.pokedexDetailResistanceLayout);
        List<String> resistances = parser.getResistanceValues();
        populateResistancesAndWeakness(resistanceLayout, resistances);

        //programmatically generate the weakness image in the resistance layout
        LinearLayout weaknessLayout = (LinearLayout) fragmentLayout.findViewById(R.id.pokedexDetailWeaknessLayout);
        List<String> weaknesses = parser.getWeaknessValues();
        populateResistancesAndWeakness(weaknessLayout, weaknesses);

        // about description of the pokemon
        TextView about = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailAboutDescription);
        about.setText(parser.getValue(pokemon, "about"));

        // programmatically generate the fast moves of the pokemon
        LinearLayout fastMovesLayout = (LinearLayout) fragmentLayout.findViewById(R.id.pokedexDetailFastAttackLayout);
        List<Skill> fastMoves = parser.getMoves("fastmoves");
        populateMoves(fastMovesLayout, fastMoves, getString(R.string.fastMoveOdd), getString(R.string.fastMoveEven));

        // programmatically generate the special moves of the pokemon
        LinearLayout specialMovesLayout = (LinearLayout) fragmentLayout.findViewById(R.id.pokedexDetailSpecialAttackLayout);
        List<Skill> specialMoves = parser.getMoves("specialmoves");
        populateMoves(specialMovesLayout, specialMoves, getString(R.string.specialMoveOdd), getString(R.string.specialMoveEven));

        // programmatically generate the ideal moveset of the pokemon
        LinearLayout idealMovesetLayout = (LinearLayout) fragmentLayout.findViewById(R.id.pokedexDetailIdealMovesetLayout);
        List<String> idealMoveset = parser.getIdealMoveset();
        populateIdealMoveset(idealMovesetLayout, idealMoveset);

        // Evolution info
        TextView evolvesFrom = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailEvolveFrom);
        evolvesFrom.setText(parser.getValue(pokemon, "evolvesfrom"));
        TextView evolvesInto = (TextView) fragmentLayout.findViewById(R.id.pokedexDetailEvolveInto);
        evolvesInto.setText(parser.getValue(pokemon, "evolvesinto"));

        return fragmentLayout;
    }

    public void populateIdealMoveset(LinearLayout layout, List<String> values){
        for(int i = 0; i < values.size(); i++){
            TextView textView = new TextView(getActivity());
            textView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            textView.setTextColor(Color.BLACK);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setPadding(2,0,0,0);
            textView.setText(values.get(i));
            layout.addView(textView);
        }

    }

    /**
     *
     * @param layout the layout contains these drawable
     * @param values list of types
     */
    public void populateResistancesAndWeakness(LinearLayout layout, List<String> values){
        for(int i = 0; i < values.size(); i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(Pokemon.getTypeDrawable(values.get(i)));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(2,2,2,2);
            imageView.setLayoutParams(layoutParams);
            layout.addView(imageView);
        }
    }

    /**
     *
     * @param movesLayout the layout contains all moves
     * @param moves list of moves
     * @param color1 background color of the odd number row
     * @param color2 background color of the even number row
     */
    public void populateMoves(LinearLayout movesLayout, List<Skill> moves, String color1, String color2){
        for(int i = 0; i < moves.size(); i++){
            Skill move = moves.get(i);
            LinearLayout fastmove = new LinearLayout(getActivity());
            fastmove.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2));
            fastmove.setOrientation(LinearLayout.VERTICAL);
            for(int j = 0; j < 7; j++){
                TextView textView = new TextView(getActivity());
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                textView.setTypeface(null, Typeface.BOLD);
                textView.setTextColor(Color.BLACK);
                textView.setPadding(2,0,2,0);
                if(j%2 == 0){
                    textView.setBackgroundColor(Color.parseColor(color1));
                }else{
                    textView.setBackgroundColor(Color.parseColor(color2));
                }
                switch(skillStat[j]){
                    case "name":
                        textView.setText(move.getName());
                        break;
                    case "type":
                        textView.setCompoundDrawablesWithIntrinsicBounds(Pokemon.getTypeDrawable(move.getType()),0,0,0);
                        break;
                    case "power":
                        textView.setText(String.valueOf(move.getPower()));
                        break;
                    case "cooldown":
                        textView.setText(String.valueOf(move.getCooldown()));
                        break;
                    case "energy":
                        textView.setText(String.valueOf(move.getEnergy()));
                        break;
                    case "dps":
                        textView.setText(String.valueOf(move.getDps()));
                        break;
                    case "wstab":
                        textView.setText(String.valueOf(move.getWstab()));
                        break;
                }
                fastmove.addView(textView);
            }
            movesLayout.addView(fastmove);
        }
    }
}
