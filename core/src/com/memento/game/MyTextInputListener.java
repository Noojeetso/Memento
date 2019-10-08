package com.memento.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MyTextInputListener implements Input.TextInputListener {

    public static String texti,text="";
    MyTextInputListener(){
        texti=new String();

    }

    @Override
    public void input(String text) {
        this.text = text;
    }

    @Override
    public void canceled() {

    }
}
