package me.june.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/11.
 */
public class PokemonAdapter extends ArrayAdapter<Pokemon> {

    private static LayoutInflater inflater = null;

    public PokemonAdapter(Context context, ArrayList<Pokemon> pokemons){
        super(context, 0, pokemons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Get the data item for this position
        Pokemon pokemon = getItem(position);

        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.pokedex_row, null);
        }

        TextView pokedexId = (TextView) view.findViewById(R.id.pokedexId);
        ImageView pokedexImage = (ImageView) view.findViewById(R.id.pokedexImage);
        TextView pokedexName = (TextView) view.findViewById(R.id.pokedexName);
        TextView pokedexType1 = (TextView) view.findViewById(R.id.pokedexType1);
        TextView pokedexType2 = (TextView) view.findViewById(R.id.pokedexType2);

        pokedexId.setText(pokemon.getId());
        pokedexImage.setImageResource(pokemon.getAssociateDrawable(Integer.parseInt(pokemon.getId())));
        pokedexName.setText(pokemon.getName());
        pokedexType1.setText(pokemon.getType1());
        pokedexType2.setText(pokemon.getType2());

        return view;
    }
}
