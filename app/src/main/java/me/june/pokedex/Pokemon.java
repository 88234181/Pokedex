package me.june.pokedex;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/10.
 */
public class Pokemon {
    private String id;
    private String name;
    private String type1;
    private String type2;
    private int candyToEvolve;
    private String about;
    private ArrayList<Skills> possibleSkills;

    public Pokemon(String id, String name, String type1, String type2, int candyToEvolve){
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.candyToEvolve = candyToEvolve;
    }

    public Pokemon(String id, String name){
        this.id = id;
        this.name = name;
    }

    private static int[] POKEMON_DRAWABLES = {
            R.drawable.pokedex001, R.drawable.pokedex002, R.drawable.pokedex003, R.drawable.pokedex004, R.drawable.pokedex005,
            R.drawable.pokedex006, R.drawable.pokedex007, R.drawable.pokedex008, R.drawable.pokedex009, R.drawable.pokedex010,
            R.drawable.pokedex010, R.drawable.pokedex011, R.drawable.pokedex012, R.drawable.pokedex013, R.drawable.pokedex014,
            R.drawable.pokedex015, R.drawable.pokedex016, R.drawable.pokedex017, R.drawable.pokedex018, R.drawable.pokedex019,
            R.drawable.pokedex020, R.drawable.pokedex021, R.drawable.pokedex022, R.drawable.pokedex023, R.drawable.pokedex024,
            R.drawable.pokedex025, R.drawable.pokedex026, R.drawable.pokedex027, R.drawable.pokedex028, R.drawable.pokedex029,
            R.drawable.pokedex030, R.drawable.pokedex031, R.drawable.pokedex032, R.drawable.pokedex033, R.drawable.pokedex034,
            R.drawable.pokedex035, R.drawable.pokedex036, R.drawable.pokedex037, R.drawable.pokedex038, R.drawable.pokedex039,
            R.drawable.pokedex040, R.drawable.pokedex041, R.drawable.pokedex042, R.drawable.pokedex043, R.drawable.pokedex044,
            R.drawable.pokedex045, R.drawable.pokedex046, R.drawable.pokedex047, R.drawable.pokedex048, R.drawable.pokedex049,
            R.drawable.pokedex050, R.drawable.pokedex051, R.drawable.pokedex052, R.drawable.pokedex053, R.drawable.pokedex054,
            R.drawable.pokedex055, R.drawable.pokedex056, R.drawable.pokedex057, R.drawable.pokedex058, R.drawable.pokedex059,
            R.drawable.pokedex060, R.drawable.pokedex061, R.drawable.pokedex062, R.drawable.pokedex063, R.drawable.pokedex064,
            R.drawable.pokedex065, R.drawable.pokedex066, R.drawable.pokedex067, R.drawable.pokedex068, R.drawable.pokedex069,
            R.drawable.pokedex070, R.drawable.pokedex071, R.drawable.pokedex072, R.drawable.pokedex073, R.drawable.pokedex074,
            R.drawable.pokedex075, R.drawable.pokedex076, R.drawable.pokedex077, R.drawable.pokedex078, R.drawable.pokedex079,
            R.drawable.pokedex080, R.drawable.pokedex081, R.drawable.pokedex082, R.drawable.pokedex083, R.drawable.pokedex084,
            R.drawable.pokedex085, R.drawable.pokedex086, R.drawable.pokedex087, R.drawable.pokedex088, R.drawable.pokedex089,
            R.drawable.pokedex090, R.drawable.pokedex091, R.drawable.pokedex092, R.drawable.pokedex093, R.drawable.pokedex094,
            R.drawable.pokedex095, R.drawable.pokedex096, R.drawable.pokedex097, R.drawable.pokedex098, R.drawable.pokedex099,
            R.drawable.pokedex100, R.drawable.pokedex100, R.drawable.pokedex101, R.drawable.pokedex102, R.drawable.pokedex103,
            R.drawable.pokedex104, R.drawable.pokedex105, R.drawable.pokedex106, R.drawable.pokedex107, R.drawable.pokedex108,
            R.drawable.pokedex109, R.drawable.pokedex110, R.drawable.pokedex111, R.drawable.pokedex112, R.drawable.pokedex113,
            R.drawable.pokedex114, R.drawable.pokedex115, R.drawable.pokedex116, R.drawable.pokedex117, R.drawable.pokedex118,
            R.drawable.pokedex119, R.drawable.pokedex120, R.drawable.pokedex121, R.drawable.pokedex122, R.drawable.pokedex123,
            R.drawable.pokedex124, R.drawable.pokedex125, R.drawable.pokedex126, R.drawable.pokedex127, R.drawable.pokedex128,
            R.drawable.pokedex129, R.drawable.pokedex130, R.drawable.pokedex131, R.drawable.pokedex132, R.drawable.pokedex133,
            R.drawable.pokedex134, R.drawable.pokedex135, R.drawable.pokedex136, R.drawable.pokedex137, R.drawable.pokedex138,
            R.drawable.pokedex139, R.drawable.pokedex140, R.drawable.pokedex141, R.drawable.pokedex142, R.drawable.pokedex143,
            R.drawable.pokedex144, R.drawable.pokedex145, R.drawable.pokedex146, R.drawable.pokedex147, R.drawable.pokedex148,
            R.drawable.pokedex149, R.drawable.pokedex150, R.drawable.pokedex151
    };



    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getType1(){
        return type1;
    }

    public String getType2(){
        return type2;
    }

    public String getAbout() { return about;}

    public int getCandyToEvolve(){
        return candyToEvolve;
    }

    public ArrayList<Skills> getPossibleSkills(){
        return possibleSkills;
    }

    public String toString(){
        return "ID: " + id + " Name: " + name + " Type: " + type1 + " Candy to evolve: " + candyToEvolve;
    }

    public static int getAssociateDrawable(int id){
        return POKEMON_DRAWABLES[id-1];
    }

    public static int getTypeDrawable(String type){

        switch (type.toLowerCase()){
            case "bug":
                return R.drawable.type_bug;
            case "dark":
                return R.drawable.type_dark;
            case "dragon":
                return R.drawable.type_dragon;
            case "electric":
                return R.drawable.type_electric;
            case "fairy":
                return R.drawable.type_fairy;
            case "fighting":
                return R.drawable.type_fighting;
            case "fire":
                return R.drawable.type_fire;
            case "flying":
                return R.drawable.type_flying;
            case "ghost":
                return R.drawable.type_ghost;
            case "grass":
                return R.drawable.type_grass;
            case "ground":
                return R.drawable.type_ground;
            case "ice":
                return R.drawable.type_ice;
            case "normal":
                return R.drawable.type_normal;
            case "poison":
                return R.drawable.type_poison;
            case "psychic":
                return R.drawable.type_psychic;
            case "rock":
                return R.drawable.type_rock;
            case "steel":
                return R.drawable.type_steel;
            case "water":
                return R.drawable.type_water;
        }
        return R.drawable.type_unknown;
    }
}
