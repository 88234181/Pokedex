package me.june.pokedex;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/8/23.
 */
public class PokemonDetailActivity extends AppCompatActivity {

    public PokemonDetailActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        //grab the fragment manager and the fragment transaction so that w can add our pokemon detail fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        PokemonDetailFragment pokemonDetailFragment = new PokemonDetailFragment();
        setTitle("Pokemon Detail");
        fragmentTransaction.add(R.id.pokemonContainer, pokemonDetailFragment, "POKEMON_DETAIL_FRAGMENT");
        fragmentTransaction.commit();
    }
}
