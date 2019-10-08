package com.memento.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Menu extends State {
    boolean draw,firstT,secondT,thirdT,fourthT;
    int btnNum;
    Texture img,bkg,btn,btn_play,mem,btn_tournament;
    BitmapFont abc;
    String abcd;

    public Menu(GameStateManager gsm){
        super(gsm);
        abc=new BitmapFont();
        draw=true;
        img = new Texture("badlogic.jpg");
        bkg = new Texture("MenuBackground.png");
        btn = new Texture("MenuButton_colored.png");
        btn_play = new Texture("MenuButton_play.png");
        btn_tournament = new Texture("MenuButton_tournament.png");
        mem = new Texture("Memento.png");
        System.out.println("2");
        camera.setToOrtho(false, memento.WIDTH, memento.HEIGHT);
        memento.ROWS=1;
        memento.COLS=1;
        abc.getData().scaleX=3;
        abc.getData().scaleY=3;

        memento.tournamentConfig=false;
        //if(!music.isPlaying())music.play();
        //Menu.music.stop();
        //memento.HEIGHT * abc.getScaleY() / abc.getLineHeight()
        //abc.getData().setScale(memento.HEIGHT * abc.getScaleY() / abc.getLineHeight(),memento.HEIGHT * abc.getScaleY() / abc.getLineHeight());

       // gsm.pop();


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
        sb.setProjectionMatrix(camera.combined);
        if(Gdx.input.isKeyPressed(Input.Keys.W))System.out.println(memento.yes);
        if(Gdx.input.isKeyJustPressed(Input.Keys.A))gsm.set(new Play(gsm));
        if(Gdx.input.justTouched()&&!memento.isTouched)
        for(int i = 1;i<=4;i++){
            if(memento.pos.x>memento.WIDTH/2-(btn_play.getWidth()*memento.minMulti)/2&&
                    memento.pos.x<memento.WIDTH/2-(btn_play.getWidth()*memento.minMulti)/2+btn_play.getWidth()*memento.minMulti &&
                    memento.pos.y>memento.HEIGHT-memento.HEIGHT/5*i-mem.getHeight()/2*memento.minMulti&&
                    memento.pos.y<memento.HEIGHT-memento.HEIGHT/5*i+btn.getHeight()*memento.minMulti-mem.getHeight()/2*memento.minMulti
            ){btnNum=i;}
        }

        if(Gdx.input.isTouched()&&!memento.isTouched){
            if(Math.sqrt(Math.pow(memento.pos.x-memento.WIDTH,2)+Math.pow(memento.pos.y-memento.HEIGHT,2))<=100*memento.minMulti){firstT=true;btnNum=5;}
            if(Math.sqrt(Math.pow(memento.pos.x-memento.WIDTH,2)+Math.pow(memento.pos.y,2))<=100*memento.minMulti&&firstT){secondT=true;firstT=false;btnNum=6;}
            if(Math.sqrt(Math.pow(memento.pos.x,2)+Math.pow(memento.pos.y,2))<=100*memento.minMulti&&secondT){thirdT=true;secondT=false;btnNum=7;}
            if(Math.sqrt(Math.pow(memento.pos.x,2)+Math.pow(memento.pos.y-memento.HEIGHT,2))<=100*memento.minMulti&&thirdT){btnNum=8;thirdT=false;if(!memento.music.isPlaying())memento.music.play();else memento.music.stop();}
        }


        //System.out.println(memento.isTouched);

        if(btnNum==1){
            if(Gdx.input.isTouched())memento.isTouched=true;System.out.println(btnNum);
            gsm.set(new GameConfig(gsm));}
        if(btnNum==2){
            if(Gdx.input.isTouched())memento.isTouched=true;
            gsm.set(new ContestChoose(gsm));}
        //if(btnNum!=0)System.out.println(btnNum);
        btnNum=0;

        if(Gdx.input.isKeyJustPressed(Input.Keys.C))Gdx.input.getTextInput(memento.listener,"Dialog Title", "Initial Textfield Value","");//memento.listener.input(abcd);
        abcd=memento.listener.text;
        //Gdx.app.log("Text",abcd);
        //System.out.println("1 "+memento.listener.text);
        //System.out.println("2 "+abcd);

        //ApplicationListener.render();

        sb.begin();
            sb.draw(bkg, 0, 0,0,0,bkg.getWidth(),bkg.getHeight(),memento.maxMulti,memento.maxMulti,0,0,0,bkg.getWidth(),bkg.getHeight(),false,false);
            if(Gdx.input.isKeyPressed(Input.Keys.D))sb.draw(img, 0, 0);
            //720/2-1145*0.75/2
            sb.draw(mem, memento.WIDTH/2-(mem.getWidth()*memento.minMulti)/2,memento.HEIGHT-mem.getHeight()*memento.minMulti,0,0,mem.getWidth(),mem.getHeight(),memento.minMulti,memento.minMulti,0,0,0,mem.getWidth(),mem.getHeight(),false,false);
            sb.draw(btn_play, memento.WIDTH/2-(btn_play.getWidth()*memento.minMulti)/2,memento.HEIGHT-memento.HEIGHT/5-mem.getHeight()*memento.minMulti/2,0,0,btn_play.getWidth(),btn_play.getHeight(),memento.minMulti,memento.minMulti,0,0,0,btn_play.getWidth(),btn_play.getHeight(),false,false);//-btn.getHeight()/2
            sb.draw(btn_tournament, memento.WIDTH/2-(btn_tournament.getWidth()*memento.minMulti)/2,memento.HEIGHT-memento.HEIGHT/5*2-mem.getHeight()*memento.minMulti/2,0,0,btn_tournament.getWidth(),btn_play.getHeight(),memento.minMulti,memento.minMulti,0,0,0,btn_tournament.getWidth(),btn_tournament.getHeight(),false,false);//-btn.getHeight()/2

        //sb.draw(btn, memento.WIDTH/2-btn.getWidth()/2,memento.HEIGHT-memento.HEIGHT/5*2-mem.getHeight()/2);
            //sb.draw(btn, memento.WIDTH/2-btn.getWidth()/2,memento.HEIGHT-memento.HEIGHT/5*3-mem.getHeight()/2);
            //sb.draw(btn, memento.WIDTH/2-btn.getWidth()/2,memento.HEIGHT-memento.HEIGHT/5*4-mem.getHeight()/2);
            //abc.draw(sb,"X: "+memento.pos.x,300,200);0.75
            //abc.draw(sb,"Y: "+memento.pos.y,300,150);0.6
            //abc.draw(sb,"Y: "+(memento.WIDTH/2-(btn_play.getWidth()*memento.multi)/2+btn_play.getWidth()*memento.multi),300,100);
            //abc.draw(sb,"Y: "+(memento.WIDTH/2-(btn_play.getWidth()*memento.multi)/2),300,150);
        //abc.draw(sb,"getHeight: "+memento.HEIGHT,300,400);
        //abc.draw(sb,"getWidth: "+memento.WIDTH,300,300);
        //abc.draw(sb,"getWidth: "+memento.WIDTH,300,330,400,100,true);
        //abc.draw(sb,"getWidth: "+memento.WIDTH,300,330,);
        //abc.draw(sb,"minMulti: "+memento.minMulti,300,200);
        //abc.draw(sb,memento.listener.text,300,100);
        //abc.draw(sb,"btnNum: "+btnNum,300,100);
        //if(memento.pos.x>memento.WIDTH/2-(btn_play.getWidth()*memento.multi)/2&&
                //memento.pos.x<memento.WIDTH/2-(btn_play.getWidth()*memento.multi)/2+btn_play.getWidth()*memento.multi)abc.draw(sb,"Y: "+(memento.pos.x),300,200);
        sb.end();
    }

    @Override
    protected void dispose() {
        img.dispose();
        bkg.dispose();
        btn.dispose();
    }
}