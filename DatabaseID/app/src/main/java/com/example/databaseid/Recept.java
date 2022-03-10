package com.example.databaseid;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
public class Recept {

    String Nazev;
    String Suroviny;
    String Postup;





    public Recept()
    {


    }


    public String getNazev() {
        return Nazev;
    }

    public void setNazev(String nazev) {
        Nazev = nazev;
    }

    public String getSuroviny() {
        return Suroviny;
    }

    public void setSuroviny(String suroviny) {
        Suroviny = suroviny;
    }

    public String getPostup() {
        return Postup;
    }

    public void setPostup(String postup) {
        Postup = postup;
    }


}