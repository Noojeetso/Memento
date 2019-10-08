package com.memento.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameConfig extends State {
    int btnNum;
    Texture img,bkg,btn_reverse,slider1,slider_f1,line1,slider2,slider_f2,line2,fs,checkBox,chek,custom,play_orange,play_green,numbers,x44,x55,x66,x77,err_even;
    Rectangle btn_reverseR,slider1R,slider_f1R,line1R,slider2R,slider_f2R,line2R,play_orangeR,play_greenR,x44R,x55R,x66R,x77R;
    boolean canBeLoad;
    float patternBtnMargin;
    public GameConfig(GameStateManager gsm){
        super(gsm);
        bkg = new Texture("MenuBackground.png");
        err_even = new Texture("Error_even.png");
        fs = new Texture("Field_size.png");
        custom = new Texture("Custom.png");
        btn_reverse = new Texture("MenuButton_reverse.png");
        play_orange = new Texture("ConfigButton_play_orange.png");
        play_green = new Texture("ConfigButton_play_green.png");
        slider1 = new Texture("slider.png");
        slider_f1 = new Texture("slider_faded.png");
        line1 = new Texture("line.png");
        slider2 = new Texture("slider.png");
        slider_f2 = new Texture("slider_faded.png");
        line2 = new Texture("line.png");
        x44 = new Texture("4x4.png");
        x55 = new Texture("5x6.png");
        x66 = new Texture("6x6.png");
        x77 = new Texture("7x8.png");

        btn_reverseR = new Rectangle(30*memento.minMulti,memento.HEIGHT-(btn_reverse.getHeight()+30)*memento.minMulti,btn_reverse.getWidth()*memento.minMulti,btn_reverse.getHeight()*memento.minMulti);

        patternBtnMargin = (memento.WIDTH-(x44.getWidth()*memento.minMulti*4+30*3*memento.minMulti))/2;
        x44R = new Rectangle(patternBtnMargin,btn_reverseR.y+(-fs.getHeight()-x44.getHeight())*memento.minMulti,x44.getWidth()*memento.minMulti,x44.getHeight()*memento.minMulti);
        x55R = new Rectangle(x44R.x+x44R.width+30*memento.minMulti,btn_reverseR.y-(fs.getHeight()+x55.getHeight())*memento.minMulti,x55.getWidth()*memento.minMulti,x55.getHeight()*memento.minMulti);
        x66R = new Rectangle(x55R.x+x55R.width+30*memento.minMulti,btn_reverseR.y-(fs.getHeight()+x66.getHeight())*memento.minMulti,x66.getWidth()*memento.minMulti,x66.getHeight()*memento.minMulti);
        x77R = new Rectangle(x66R.x+x66R.width+30*memento.minMulti,btn_reverseR.y-(fs.getHeight()+x77.getHeight())*memento.minMulti,x77.getWidth()*memento.minMulti,x77.getHeight()*memento.minMulti);

        line1R = new Rectangle(memento.WIDTH/2-line1.getWidth()/2*memento.minMulti-100*memento.minMulti,x44R.y+(-custom.getHeight()-line1.getHeight()-10-slider1.getHeight())*memento.minMulti,    line1.getWidth()*memento.minMulti,line1.getHeight()*memento.minMulti);
        slider1R = new Rectangle(line1R.x-slider1.getWidth()/2*memento.minMulti,line1R.y-slider1.getHeight()/2*memento.minMulti+line1.getHeight()/2*memento.minMulti,slider1.getHeight()*memento.minMulti,slider1.getHeight()*memento.minMulti);//slider1.getWidth(),slider1.getHeight()
        slider_f1R = new Rectangle(line1R.x-slider1.getWidth()/2*memento.minMulti,line1R.y-slider1.getHeight()/2*memento.minMulti+line1.getHeight()/2*memento.minMulti,slider1.getHeight()*memento.minMulti,slider1.getHeight()*memento.minMulti);
        line2R = new Rectangle(memento.WIDTH/2-line2.getWidth()/2*memento.minMulti-100*memento.minMulti,line1R.y+(-line1.getHeight()/2-slider1.getHeight())*memento.minMulti,    line2.getWidth()*memento.minMulti,line2.getHeight()*memento.minMulti);
        slider2R = new Rectangle(line2R.x-slider2.getWidth()/2*memento.minMulti,line2R.y-slider2.getHeight()/2*memento.minMulti+line2.getHeight()/2*memento.minMulti,slider2.getWidth()*memento.minMulti,slider2.getHeight()*memento.minMulti);
        slider_f2R = new Rectangle(line2R.x-slider2.getWidth()/2*memento.minMulti,line2R.y-slider2.getHeight()/2*memento.minMulti+line2.getHeight()/2*memento.minMulti,slider_f2.getWidth()*memento.minMulti,slider_f2.getHeight()*memento.minMulti);
        play_orangeR = new Rectangle(memento.WIDTH-(30+play_orange.getWidth())*memento.minMulti,30*memento.minMulti,play_orange.getWidth()*memento.minMulti,play_orange.getHeight()*memento.minMulti);
        play_greenR = new Rectangle(memento.WIDTH-(30+play_green.getWidth())*memento.minMulti,30*memento.minMulti,play_green.getWidth()*memento.minMulti,play_green.getHeight()*memento.minMulti);

        memento.ROWS=1;memento.COLS=1;
    }////*memento.minMulti
    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void render(SpriteBatch sb) {
        if(memento.isTouched&&!Gdx.input.isTouched())memento.isTouched=false;
        if(Gdx.input.isTouched()&&!memento.isTouched){
            if(memento.pos.x>btn_reverseR.x&&memento.pos.x<btn_reverseR.x+btn_reverseR.width&&memento.pos.y>btn_reverseR.y&&memento.pos.y<btn_reverseR.y+btn_reverseR.height)btnNum=1;
            if(memento.pos.x>x44R.x&&memento.pos.x<x44R.x+x44R.width&&memento.pos.y>x44R.y&&memento.pos.y<x44R.y+x44R.height)btnNum=2;
            if(memento.pos.x>x55R.x&&memento.pos.x<x55R.x+x55R.width&&memento.pos.y>x55R.y&&memento.pos.y<x55R.y+x55R.height)btnNum=3;
            if(memento.pos.x>x66R.x&&memento.pos.x<x66R.x+x66R.width&&memento.pos.y>x66R.y&&memento.pos.y<x66R.y+x66R.height)btnNum=4;
            if(memento.pos.x>x77R.x&&memento.pos.x<x77R.x+x77R.width&&memento.pos.y>x77R.y&&memento.pos.y<x77R.y+x77R.height)btnNum=5;

            if(memento.pos.x>line1R.x&&memento.pos.x<line1R.x+line1R.width&&memento.pos.y>line1R.y-slider_f1R.height/2+line1R.height/2&&memento.pos.y<line1R.y+line1R.height+slider_f1R.height/2-line1R.height/2)
            {slider_f1R.x=(line1R.x-slider1R.width/2)+60*(int)((memento.pos.x-line1R.x+30*memento.minMulti)/(60*memento.minMulti))*memento.minMulti;//-slider1R.width/2 (memento.pos.x-line1R.x)/60
                memento.COLS=1+(int)((memento.pos.x-line1R.x+30*memento.minMulti)/(60*memento.minMulti));
            }
            if(memento.pos.x>line2R.x&&memento.pos.x<line2R.x+line2R.width&&memento.pos.y>line2R.y-slider_f2R.height/2+line2R.height/2&&memento.pos.y<line2R.y+line2R.height+slider_f2R.height/2-line2R.height/2)
            {slider_f2R.x=(line2R.x-slider2R.width/2)+60*(int)((memento.pos.x-line2R.x+30*memento.minMulti)/(60*memento.minMulti))*memento.minMulti;;
                memento.ROWS=1+(int)((memento.pos.x-line2R.x+30*memento.minMulti)/(60*memento.minMulti));}
            if(memento.pos.x>play_greenR.x&&memento.pos.x<play_greenR.x+play_greenR.width&&memento.pos.y>play_greenR.y&&memento.pos.y<play_greenR.y+play_greenR.height)btnNum=6;
            //memento.pos.break;
        }


        if(btnNum==1&&!memento.tournamentConfig){memento.ROWS=0;memento.COLS=0;if(Gdx.input.isTouched())memento.isTouched=true;gsm.set(new Menu(gsm));}
        if(btnNum==1&&memento.tournamentConfig){memento.ROWS=0;memento.COLS=0;if(Gdx.input.isTouched())memento.isTouched=true;gsm.set(new ContestCreate(gsm));}
        if(btnNum==2){
            slider_f1R.x=(line1R.x-slider1R.width/2)+60*3*memento.minMulti;memento.ROWS=4;
            slider_f2R.x=(line2R.x-slider2R.width/2)+60*3*memento.minMulti;memento.COLS=4;}
        if(btnNum==3){
            slider_f1R.x=(line1R.x-slider1R.width/2)+60*4*memento.minMulti;memento.ROWS=6;
            slider_f2R.x=(line2R.x-slider2R.width/2)+60*5*memento.minMulti;memento.COLS=5;}
        if(btnNum==4){
            slider_f1R.x=(line1R.x-slider1R.width/2)+60*5*memento.minMulti;memento.ROWS=6;
            slider_f2R.x=(line2R.x-slider2R.width/2)+60*5*memento.minMulti;memento.COLS=6;}
        if(btnNum==5){
            slider_f1R.x=(line1R.x-slider1R.width/2)+60*6*memento.minMulti;memento.ROWS=8;
            slider_f2R.x=(line2R.x-slider2R.width/2)+60*7*memento.minMulti;memento.COLS=7;}
        if(btnNum==6&&canBeLoad&&!memento.tournamentConfig){
            if(Gdx.input.isTouched())memento.isTouched=true;
            gsm.set(new Play(gsm));}
        if(btnNum==6&&canBeLoad&&memento.tournamentConfig){
            if(Gdx.input.isTouched())memento.isTouched=true;
            gsm.set(new Contest(gsm));}
        btnNum=0;

        if(memento.ROWS*memento.COLS%2==0&&memento.ROWS*memento.COLS!=0)canBeLoad=true;else canBeLoad=false;
        sb.begin();
        sb.draw(bkg, 0, 0,0,0,bkg.getWidth(),bkg.getHeight(),memento.maxMulti,memento.maxMulti,0,0,0,bkg.getWidth(),bkg.getHeight(),false,false);
        //sb.draw(numbers, 0, 0);
        sb.draw(fs, memento.WIDTH/2-fs.getWidth()/2*memento.minMulti, btn_reverseR.y-fs.getHeight()*memento.minMulti,0,0,fs.getWidth(),fs.getHeight(),memento.minMulti,memento.minMulti,0,0,0,fs.getWidth(),fs.getHeight(),false,false);

        sb.draw(x44, x44R.x, x44R.y,0,0,x44.getWidth(),x44.getHeight(),memento.minMulti,memento.minMulti,0,0,0,x44.getWidth(),x44.getHeight(),false,false);
        sb.draw(x55, x55R.x, x55R.y,0,0,x55.getWidth(),x55.getHeight(),memento.minMulti,memento.minMulti,0,0,0,x55.getWidth(),x55.getHeight(),false,false);
        sb.draw(x66, x66R.x, x66R.y,0,0,x66.getWidth(),x66.getHeight(),memento.minMulti,memento.minMulti,0,0,0,x66.getWidth(),x66.getHeight(),false,false);
        sb.draw(x77, x77R.x, x77R.y,0,0,x77.getWidth(),x77.getHeight(),memento.minMulti,memento.minMulti,0,0,0,x77.getWidth(),x77.getHeight(),false,false);
        sb.draw(custom, 30*memento.minMulti, x44R.y-(custom.getHeight()+30)*memento.minMulti,0,0,custom.getWidth(),custom.getHeight(),memento.minMulti,memento.minMulti,0,0,0,custom.getWidth(),custom.getHeight(),false,false);
        sb.draw(line1, line1R.x, line1R.y,0,0,line1.getWidth(),line1.getHeight(),memento.minMulti,memento.minMulti,0,0,0,line1.getWidth(),line1.getHeight(),false,false);
        sb.draw(slider_f1, slider_f1R.x, slider_f1R.y,0,0,slider_f1.getWidth(),slider_f1.getHeight(),memento.minMulti,memento.minMulti,0,0,0,slider_f1.getWidth(),slider_f1.getHeight(),false,false);
        sb.draw(line2, line2R.x, line2R.y,0,0,line2.getWidth(),line2.getHeight(),memento.minMulti,memento.minMulti,0,0,0,line2.getWidth(),line2.getHeight(),false,false);
        sb.draw(slider_f2, slider_f2R.x, slider_f2R.y,0,0,slider_f2.getWidth(),slider_f2.getHeight(),memento.minMulti,memento.minMulti,0,0,0,slider_f2.getWidth(),slider_f2.getHeight(),false,false);
        sb.draw(btn_reverse, btn_reverseR.x,  btn_reverseR.y,0,0,btn_reverse.getWidth(),btn_reverse.getHeight(),memento.minMulti,memento.minMulti,0,0,0,btn_reverse.getWidth(),btn_reverse.getHeight(),false,false);
        if(canBeLoad)sb.draw(play_green, play_greenR.x, play_greenR.y,0,0,play_green.getWidth(),play_green.getHeight(),memento.minMulti,memento.minMulti,0,0,0,play_green.getWidth(),play_green.getHeight(),false,false);
            else{
            sb.draw(play_orange, play_orangeR.x, play_orangeR.y,0,0,play_orange.getWidth(),play_orange.getHeight(),memento.minMulti,memento.minMulti,0,0,0,play_orange.getWidth(),play_orange.getHeight(),false,false);
            sb.draw(err_even, 30, line2R.y-slider2R.height/2+line2R.height/2-100,0,0,err_even.getWidth(),err_even.getHeight(),memento.minMulti,memento.minMulti,0,0,0,err_even.getWidth(),err_even.getHeight(),false,false);}
        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
