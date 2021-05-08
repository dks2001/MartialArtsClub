package com.dheerendrakumar.martialartsclub;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

import com.dheerendrakumar.martialartsclub.model.MartialArt;

public class MartialArtButton extends AppCompatButton {


    private MartialArt martialArtObjct;

    public MartialArtButton(Context context , MartialArt martialArt) {

        super(context);
        martialArtObjct = martialArt;

    }

    public String MartialArtColor() {
        return martialArtObjct.getMartialArtColor();
    }

    public  double MartialArtPrice() {
        return martialArtObjct.getMartialArtPrice();
    }

}
