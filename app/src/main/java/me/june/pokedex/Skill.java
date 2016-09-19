package me.june.pokedex;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/10.
 */
public class Skill {
    private String name;
    private String type;
    private int power;
    private float cooldown;
    private int energy;
    private float dps;
    private float wstab;
    private ArrayList<Pokemon> knownBy;

    public Skill(String name, String type, int power, float cooldown, int energy, float dps, float wstab, ArrayList<Pokemon> knownBy){
        this.name =  name;
        this.type = type;
        this.power = power;
        this.cooldown = cooldown;
        this.energy = energy;
        this.dps = dps;
        this.wstab = wstab;
        this.knownBy = knownBy;
    }

    public Skill(String name, String type, int power, float cooldown, int energy, float dps, float wstab){
        this.name =  name;
        this.type = type;
        this.power = power;
        this.cooldown = cooldown;
        this.energy = energy;
        this.dps = dps;
        this.wstab = wstab;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public int getPower(){
        return power;
    }

    public float getCooldown(){
        return cooldown;
    }

    public int getEnergy(){
        return energy;
    }

    public float getDps(){
        return dps;
    }

    public float getWstab(){
        return wstab;
    }
}
