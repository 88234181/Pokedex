package me.june.pokedex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/8/23.
 */
public class PokemonDetailFragment extends Fragment{

    public PokemonDetailFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup convertView, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.pokemon_detail_fragment, convertView, false);



        return view;
    }
}
