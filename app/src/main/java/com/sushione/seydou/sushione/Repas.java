package com.sushione.seydou.sushione;

/**
 * Created by seydou on 10/08/17.
 */

public class Repas {
    private long id;
    private int nombre;
    private String Choix;

    public Repas(int nombre,String Choix) {
        this.nombre = nombre;
        this.Choix=Choix;
    }

    public Repas() {

    }

    public long getId() {
        return id;
    }


    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getChoix() {
        return Choix;
    }

    public void setChoix(String choix) {
        Choix = choix;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Override
    public String toString(){
        return Choix;
    }
}
