package com.memento.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Hashtable;

public class Contest extends State {
    int k=0,jt,btnNum,NOT,nx=30;
    float maxHeight,btn_multi;
    boolean even;
    Texture bkg,btn_reverse,play_orange,play_green,slider1,slider_f1,line1,player_btn,sables,sables_f;
    Rectangle btn_reverseR,slider1R,slider_f1R,line1R,play_orangeR,play_greenR,sablesR;
    BitmapFont abc;
    Integer[] tmp;
    String[] endSt;
    Hashtable<Integer,String> endHT;
    //static Hashtable<Integer,Boolean> evenPairs;
    public Contest(GameStateManager gsm){
        super(gsm);
        bkg = new Texture("MenuBackground.png");
        btn_reverse = new Texture("MenuButton_reverse.png");
        play_orange = new Texture("ConfigButton_play_orange.png");
        play_green = new Texture("ConfigButton_play_green.png");
        slider1 = new Texture("slider.png");
        slider_f1 = new Texture("slider_faded.png");
        line1 = new Texture("line.png");
        player_btn = new Texture("player_btn.png");
        sables = new Texture("Sables.png");
        sables_f = new Texture("Sables_f.png");
        abc = new BitmapFont();
        abc.getData().scaleX=3;
        abc.getData().scaleY=3;
        tmp = new Integer[2];
        endSt = new String[10];
        endHT = new Hashtable<Integer,String>();
        //evenPairs = new Hashtable();
        btn_reverseR = new Rectangle(30*memento.minMulti,memento.HEIGHT-(btn_reverse.getHeight()+30)*memento.minMulti,btn_reverse.getWidth()*memento.minMulti,btn_reverse.getHeight()*memento.minMulti);
        sablesR = new Rectangle(memento.WIDTH/2-sables.getWidth()/2*memento.minMulti,btn_reverseR.y/2-sables.getHeight()/2*memento.minMulti,sables.getWidth()*memento.minMulti,sables.getHeight()*memento.minMulti);

        //maxHeight = btn_reverseR.y-60*memento.minMulti;
        memento.tournamentEnd=true;


        for(int i=0;i<memento.NOP;i++){
            for(int j=0;j<memento.NOP;j++)
                if(memento.PlayCounter[j]==i&&memento.Tscore[j]==memento.NOP-1-i){
                    if(k>1){break;}
                    tmp[k]=j;
                    k++;}
            if(k>1){
                memento.firstPlayerInt=tmp[0];
                memento.secondPlayerInt=tmp[1];
                memento.tournamentEnd=false;
                break;}

            //if(k>1){if(k%2==0){even=true;NOT=k;}else NOT=k*2;break;}//evenPairs.put(i,true);else evenPairs.put(i,false);
            tmp[0]=null;
            tmp[1]=null;
            k=0;
        }

        if(k<=1){k=0;
        for(int i=memento.NOP-1;i>=0;i--){
            for(int j=0;j<memento.NOP;j++)
            if(memento.Tscore[j]==i){
            if(k>1){break;}
            tmp[k]=j;
            k++;}
            if(k>1){
                memento.firstPlayerInt=tmp[0];
                memento.secondPlayerInt=tmp[1];
                memento.tournamentEnd=false;
                break;}

            //if(k>1){if(k%2==0){even=true;NOT=k;}else NOT=k*2;break;}//evenPairs.put(i,true);else evenPairs.put(i,false);
            tmp[0]=null;
            tmp[1]=null;
            k=0;
        }}

        k=0;
        if(memento.tournamentEnd) {
            for(int i=0;i<memento.NOP;i++)
            endHT.put(memento.Tscore[i],memento.rivals[i]);
        }
            /*
        }
        if(memento.tournamentEnd){
            k=memento.NOP;
            while(k>0){
                for(int i=0;i<memento.NOP;i++)
                if(memento.Tscore[i]==k)
                endHT.put(k,memento.rivals[i]);
                k--;
            }
            //for(int i=0;i<memento.NOP;i++)if(memento.Tscore[i]==memento.NOP-1-i){endSt[k]=memento.rivals[i];k++;}
        }*/
        k=0;
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
        if(Gdx.input.justTouched()&&!memento.isTouched){
            if(memento.pos.x>btn_reverseR.x&&memento.pos.x<btn_reverseR.x+btn_reverseR.width&&memento.pos.y>btn_reverseR.y&&memento.pos.y<btn_reverseR.y+btn_reverseR.height)btnNum=1;
            if(memento.pos.x>sablesR.x&&memento.pos.x<sablesR.x+sablesR.width&&memento.pos.y>sablesR.y&&memento.pos.y<sablesR.y+sablesR.height)btnNum=2;
        }

        if(btnNum==1&&!memento.tournamentEnd){memento.ROWS=0;memento.COLS=0;if(Gdx.input.isTouched())memento.isTouched=true;gsm.set(new ContestChoose(gsm));}
        if(btnNum==1&&memento.tournamentEnd){System.out.println("////////////////////");
            for(int i=0;i<memento.NOP;i++)memento.Tscore[i]=0;
            for(int i=0;i<memento.NOP;i++)memento.PlayCounter[i]=0;
        if(Gdx.input.isTouched())memento.isTouched=true;gsm.set(new ContestChoose(gsm));}

        if(btnNum==2){memento.tournamentPlay=true;if(Gdx.input.isTouched())memento.isTouched=true;gsm.set(new Play(gsm));}
        sb.begin();
        sb.draw(bkg, 0, 0,0,0,bkg.getWidth(),bkg.getHeight(),memento.maxMulti,memento.maxMulti,0,0,0,bkg.getWidth(),bkg.getHeight(),false,false);
        sb.draw(btn_reverse, btn_reverseR.x, btn_reverseR.y,0,0,btn_reverse.getWidth(),btn_reverse.getHeight(),memento.minMulti,memento.minMulti,0,0,0,btn_reverse.getWidth(),btn_reverse.getHeight(),false,false);

        //System.out.println(tmp[0]);
        //System.out.println(tmp[1]);
        //System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        //System.out.println(endSt[0]);
        //System.out.println(endSt[1]);
        //for(int i=0;i<2;i++)
        //sb.draw(player_btn, memento.WIDTH/2-player_btn.getWidth()/2*memento.minMulti, sablesR.y+(sables.getHeight()+30)*memento.maxMulti,0,0,player_btn.getWidth(),player_btn.getHeight(),memento.minMulti,memento.minMulti,0,0,0,player_btn.getWidth(),player_btn.getHeight(),false,false);
        //sb.draw(player_btn, memento.WIDTH/2-player_btn.getWidth()/2*memento.minMulti, sablesR.y-(30+player_btn.getHeight())*memento.minMulti,0,0,player_btn.getWidth(),player_btn.getHeight(),memento.minMulti,memento.minMulti,0,0,0,player_btn.getWidth(),player_btn.getHeight(),false,false);
        if(!memento.tournamentEnd){
        abc.draw(sb,memento.rivals[tmp[0]],memento.WIDTH/2-memento.rivals[tmp[0]].length()*30/2*memento.minMulti, sablesR.y+(sables.getHeight()+30+50)*memento.maxMulti);
        sb.draw(sables, sablesR.x,sablesR.y,0,0,sables.getWidth(),sables.getHeight(),memento.maxMulti,memento.maxMulti,0,0,0,sables.getWidth(),sables.getHeight(),false,false);
        abc.draw(sb,memento.rivals[tmp[1]],memento.WIDTH/2-memento.rivals[tmp[1]].length()*30/2*memento.minMulti, sablesR.y-(30)*memento.minMulti);
        }else{
            for(int i=0;i<memento.NOP;i++)
                abc.draw(sb,endHT.get(memento.NOP-1-i),30*memento.minMulti, btn_reverseR.y-(30+(i)*player_btn.getHeight()+i*nx)*memento.minMulti);
        }


        sb.end();

        //sb.draw(btn_reverse, btn_reverseR.x, btn_reverseR.y,0,0,btn_reverse.getWidth(),btn_reverse.getHeight(),memento.minMulti,memento.minMulti,0,0,0,btn_reverse.getWidth(),btn_reverse.getHeight(),false,false);
        //sb.draw(NOP, memento.WIDTH/2-NOP.getWidth()/2*memento.minMulti, btn_reverseR.y+(-30-NOP.getHeight())*memento.minMulti,0,0,NOP.getWidth(),NOP.getHeight(),memento.minMulti,memento.minMulti,0,0,0,NOP.getWidth(),NOP.getHeight(),false,false);
        //sb.draw(line1, line1R.x, line1R.y,0,0,line1.getWidth(),line1.getHeight(),memento.minMulti,memento.minMulti,0,0,0,line1.getWidth(),line1.getHeight(),false,false);
        //sb.draw(slider_f1, slider_f1R.x, slider_f1R.y,0,0,slider_f1.getWidth(),slider_f1.getHeight(),memento.minMulti,memento.minMulti,0,0,0,slider_f1.getWidth(),slider_f1.getHeight(),false,false);

    }

    @Override
    protected void dispose() {

    }
}
