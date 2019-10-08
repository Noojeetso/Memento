package com.memento.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndGame extends State {
    int nx,btnNum;
    BitmapFont abc;
    Texture pl1,pl2,tshirt,pl1_f,pl2_f,bkg,img,numbers,btn_reverse,won1,won2,score,draw;

    public EndGame(GameStateManager gsm){
        super(gsm);
        abc = new BitmapFont();
        img = new Texture("badlogic.jpg");
        bkg = new Texture("MenuBackground.png");
        numbers = new Texture("numbers.png");
        won1 = new Texture("1haswon.png");
        won2 = new Texture("2haswon.png");
        draw = new Texture("Draw.png");
        score = new Texture("Score.png");
        btn_reverse = new Texture("MenuButton_reverse.png");
        nx=10;
        abc.getData().scaleX=5;
        abc.getData().scaleY=5;
        //camera.setToOrtho(false, memento.WIDTH/4, memento.HEIGHT/4);
        if(memento.scoreAbs==0)nx=10;else nx=memento.scoreAbs;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void render(SpriteBatch sb) {
        if(memento.isTouched&&!Gdx.input.isTouched())memento.isTouched=false;
        //sb.setProjectionMatrix(camera.combined);
        //camera.update();
        if(Gdx.input.justTouched()&&!memento.isTouched){
            if(memento.pos.x>30*memento.minMulti&&memento.pos.x<30*memento.minMulti+btn_reverse.getWidth()*memento.minMulti&&memento.pos.y>memento.HEIGHT-30*memento.minMulti-btn_reverse.getHeight()*memento.minMulti&&memento.pos.y<memento.HEIGHT-30*memento.minMulti)btnNum=1;
        }
        if(btnNum==1&&!memento.tournamentPlay){if(Gdx.input.isTouched())memento.isTouched=true;gsm.set(new Menu(gsm));}
        else if(btnNum==1&&memento.tournamentPlay){memento.tournamentPlay=false;if(Gdx.input.isTouched())memento.isTouched=true;gsm.set(new Contest(gsm));}
        sb.begin();
        sb.draw(bkg, 0, 0,0,0,bkg.getWidth(),bkg.getHeight(),memento.maxMulti,memento.maxMulti,0,0,0,bkg.getWidth(),bkg.getHeight(),false,false);
        sb.draw(btn_reverse,30*memento.minMulti,memento.HEIGHT-30*memento.minMulti-btn_reverse.getHeight()*memento.minMulti, 0,0,btn_reverse.getWidth(),btn_reverse.getHeight(),memento.minMulti,memento.minMulti,0,0,0,btn_reverse.getWidth(),btn_reverse.getHeight(),false,false);
        //sb.draw(img,0,0);
        //sb.draw(numbers,0,0,4+61*(nx-1),0,60,100);
        if(memento.scoreAbs>0){if(memento.player1win)sb.draw(won1,memento.WIDTH/2-won1.getWidth()/2*memento.minMulti,memento.HEIGHT+(-30-btn_reverse.getHeight()-30-won1.getHeight())*memento.minMulti,0,0,won1.getWidth(),won1.getHeight(),memento.minMulti,memento.minMulti,0,0,0,won1.getWidth(),won1.getHeight(),false,false);
        else sb.draw(won2,memento.WIDTH/2-won2.getWidth()/2*memento.minMulti,memento.HEIGHT+(-30-btn_reverse.getHeight()-30-won2.getHeight())*memento.minMulti,0,0,won2.getWidth(),won2.getHeight(),memento.minMulti,memento.minMulti,0,0,0,won2.getWidth(),won2.getHeight(),false,false);
        }else sb.draw(draw,memento.WIDTH/2-draw.getWidth()/2*memento.minMulti,memento.HEIGHT+(-30-btn_reverse.getHeight()-30-draw.getHeight())*memento.minMulti,0,0,draw.getWidth(),draw.getHeight(),memento.minMulti,memento.minMulti,0,0,0,draw.getWidth(),draw.getHeight(),false,false);
        sb.draw(score,memento.WIDTH/2-won1.getWidth()/2*memento.minMulti,memento.HEIGHT+(-30-btn_reverse.getHeight()-30-won1.getHeight()-30-score.getHeight())*memento.minMulti,0,0,score.getWidth(),score.getHeight(),memento.minMulti,memento.minMulti,0,0,0,score.getWidth(),score.getHeight(),false,false);
        //sb.draw(numbers,memento.WIDTH/2-30*memento.minMulti,memento.HEIGHT+(-30-btn_reverse.getHeight()-30-won1.getHeight()-30-score.getHeight()-10)*memento.minMulti,120,0,60,100,memento.minMulti,memento.minMulti,0,4+61*(nx-1),0,60,100,false,false);
        abc.draw(sb,""+memento.scoreAbs,memento.WIDTH/2-30*memento.minMulti,memento.HEIGHT+(-30-btn_reverse.getHeight()-30-won1.getHeight()-30)*memento.minMulti);
        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
