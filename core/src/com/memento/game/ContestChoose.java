package com.memento.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ContestChoose extends State {
    int btnNum;
    boolean canBeLoad;
    Texture img,bkg,btn,btn_conCr,btn_tournament,btn_reverse,play_orange,play_green;
    Rectangle btn_reverseR,btn_conCrR,slider1R,slider_f1R,line1R,slider2R,slider_f2R,line2R,play_orangeR,play_greenR,x44R,x55R,x66R,x77R;

    public ContestChoose(GameStateManager gsm){
        super(gsm);
        bkg = new Texture("MenuBackground.png");
        btn_conCr=new Texture("createTour.png");
        btn_reverse = new Texture("MenuButton_reverse.png");
        play_orange = new Texture("ConfigButton_play_orange.png");
        play_green = new Texture("ConfigButton_play_green.png");

        btn_reverseR = new Rectangle(30*memento.minMulti,memento.HEIGHT-(btn_reverse.getHeight()+30)*memento.minMulti,btn_reverse.getWidth()*memento.minMulti,btn_reverse.getHeight()*memento.minMulti);
        btn_conCrR = new Rectangle(memento.WIDTH/2-btn_conCr.getWidth()/2*memento.minMulti,btn_reverseR.y+(-30-btn_conCr.getHeight())*memento.minMulti,btn_conCr.getWidth()*memento.minMulti,btn_conCr.getHeight()*memento.minMulti);
        play_orangeR = new Rectangle(memento.WIDTH-(30+play_orange.getWidth())*memento.minMulti,30*memento.minMulti,play_orange.getWidth()*memento.minMulti,play_orange.getHeight()*memento.minMulti);
        play_greenR = new Rectangle(memento.WIDTH-(30+play_green.getWidth())*memento.minMulti,30*memento.minMulti,play_green.getWidth()*memento.minMulti,play_green.getHeight()*memento.minMulti);


    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void render(SpriteBatch sb) {
        if(memento.tournamentEnd=true)memento.tournamentEnd=false;
        if(memento.isTouched&&!Gdx.input.isTouched())memento.isTouched=false;
        if(Gdx.input.isTouched()&&!memento.isTouched){
            if(memento.pos.x>btn_reverseR.x&&memento.pos.x<btn_reverseR.x+btn_reverseR.width&&memento.pos.y>btn_reverseR.y&&memento.pos.y<btn_reverseR.y+btn_reverseR.height)btnNum=1;
            if(memento.pos.x>btn_conCrR.x&&memento.pos.x<btn_conCrR.x+btn_conCrR.width&&memento.pos.y>btn_conCrR.y&&memento.pos.y<btn_conCrR.y+btn_conCrR.height)btnNum=2;
            //if(memento.pos.x>x66R.x&&memento.pos.x<x66R.x+x66R.width&&memento.pos.y>x66R.y&&memento.pos.y<x66R.y+x66R.height)btnNum=4;
            //if(memento.pos.x>x77R.x&&memento.pos.x<x77R.x+x77R.width&&memento.pos.y>x77R.y&&memento.pos.y<x77R.y+x77R.height)btnNum=5;

        }

        if(btnNum==1){if(Gdx.input.isTouched())memento.isTouched=true;gsm.set(new Menu(gsm));}
        if(btnNum==2){if(Gdx.input.isTouched())memento.isTouched=true;gsm.set(new ContestCreate(gsm));}
        if(btnNum==3){
            slider_f1R.x=(line1R.x-slider1R.width/2)+60*4*memento.minMulti;memento.ROWS=6;
            slider_f2R.x=(line2R.x-slider2R.width/2)+60*5*memento.minMulti;memento.COLS=5;}
        if(btnNum==4){
            slider_f1R.x=(line1R.x-slider1R.width/2)+60*5*memento.minMulti;memento.ROWS=6;
            slider_f2R.x=(line2R.x-slider2R.width/2)+60*5*memento.minMulti;memento.COLS=6;}
        if(btnNum==5){
            slider_f1R.x=(line1R.x-slider1R.width/2)+60*6*memento.minMulti;memento.ROWS=8;
            slider_f2R.x=(line2R.x-slider2R.width/2)+60*7*memento.minMulti;memento.COLS=7;}
        if(btnNum==6&&canBeLoad){
            if(Gdx.input.isTouched())memento.isTouched=true;
            gsm.set(new Play(gsm));}
        btnNum=0;

        sb.begin();
        sb.draw(bkg, 0, 0,0,0,bkg.getWidth(),bkg.getHeight(),memento.maxMulti,memento.maxMulti,0,0,0,bkg.getWidth(),bkg.getHeight(),false,false);
        sb.draw(btn_reverse, btn_reverseR.x, btn_reverseR.y,0,0,btn_reverse.getWidth(),btn_reverse.getHeight(),memento.minMulti,memento.minMulti,0,0,0,btn_reverse.getWidth(),btn_reverse.getHeight(),false,false);
        sb.draw(btn_conCr, btn_conCrR.x, btn_conCrR.y,0,0,btn_conCr.getWidth(),btn_conCr.getHeight(),memento.minMulti,memento.minMulti,0,0,0,btn_conCr.getWidth(),btn_conCr.getHeight(),false,false);//-btn.getHeight()/2

        if(canBeLoad)sb.draw(play_green, play_greenR.x, play_greenR.y,0,0,play_green.getWidth(),play_green.getHeight(),memento.minMulti,memento.minMulti,0,0,0,play_green.getWidth(),play_green.getHeight(),false,false);
        else{
            sb.draw(play_orange, play_orangeR.x, play_orangeR.y,0,0,play_orange.getWidth(),play_orange.getHeight(),memento.minMulti,memento.minMulti,0,0,0,play_orange.getWidth(),play_orange.getHeight(),false,false);}
        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
