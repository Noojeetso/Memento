package com.memento.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sun.org.apache.bcel.internal.generic.NOP;

public class ContestCreate extends State{
    int btnNum,nx=30,typeEnd;
    float maxHeight,btnMulti;
    boolean canBeLoad;
    Texture bkg,NOP,btn_conCr,btn_reverse,play_orange,play_green,slider1,slider_f1,line1,player_btn;
    Rectangle btn_reverseR,slider1R,slider_f1R,line1R,play_orangeR,play_greenR;
    BitmapFont abc;
    public ContestCreate(GameStateManager gsm){
        super(gsm);
        bkg = new Texture("MenuBackground.png");
        btn_conCr=new Texture("createTour.png");
        btn_reverse = new Texture("MenuButton_reverse.png");
        play_orange = new Texture("ConfigButton_play_orange.png");
        play_green = new Texture("ConfigButton_play_green.png");
        NOP = new Texture("NOF.png");
        slider1 = new Texture("slider.png");
        slider_f1 = new Texture("slider_faded.png");
        line1 = new Texture("line.png");
        player_btn = new Texture("player_btn.png");
        abc = new BitmapFont();
        btn_reverseR = new Rectangle(30*memento.minMulti,memento.HEIGHT-(btn_reverse.getHeight()+30)*memento.minMulti,btn_reverse.getWidth()*memento.minMulti,btn_reverse.getHeight()*memento.minMulti);

        line1R = new Rectangle(memento.WIDTH/2-line1.getWidth()/2*memento.minMulti-100*memento.minMulti,btn_reverseR.y+(-NOP.getHeight()-line1.getHeight()-10-slider1.getHeight())*memento.minMulti,    line1.getWidth()*memento.minMulti,line1.getHeight()*memento.minMulti);
        slider1R = new Rectangle(line1R.x-slider1.getWidth()/2*memento.minMulti,line1R.y-slider1.getHeight()/2*memento.minMulti+line1.getHeight()/2*memento.minMulti,slider1.getHeight()*memento.minMulti,slider1.getHeight()*memento.minMulti);//slider1.getWidth(),slider1.getHeight()
        slider_f1R = new Rectangle(line1R.x-slider1.getWidth()/2*memento.minMulti,line1R.y-slider1.getHeight()/2*memento.minMulti+line1.getHeight()/2*memento.minMulti,slider1.getHeight()*memento.minMulti,slider1.getHeight()*memento.minMulti);
        play_orangeR = new Rectangle(memento.WIDTH-(30+play_orange.getWidth())*memento.minMulti,30*memento.minMulti,play_orange.getWidth()*memento.minMulti,play_orange.getHeight()*memento.minMulti);
        play_greenR = new Rectangle(memento.WIDTH-(30+play_green.getWidth())*memento.minMulti,30*memento.minMulti,play_green.getWidth()*memento.minMulti,play_green.getHeight()*memento.minMulti);

        //btn_conCrR = new Rectangle(memento.WIDTH/2-btn_conCr.getWidth()/2*memento.minMulti,btn_reverseR.y+(-30-btn_conCr.getHeight())*memento.minMulti,btn_reverse.getWidth()*memento.minMulti,btn_reverse.getHeight()*memento.minMulti);
        maxHeight=slider1R.y-play_orangeR.y-play_orangeR.height-60*memento.minMulti;
        btnMulti=memento.minMulti;
        abc.getData().scaleX=3;
        abc.getData().scaleY=3;
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
        if(Gdx.input.isTouched()&&!memento.isTouched){


            if(memento.pos.x>line1R.x&&memento.pos.x<line1R.x+line1R.width&&memento.pos.y>line1R.y-slider_f1R.height/2+line1R.height/2&&memento.pos.y<line1R.y+line1R.height+slider_f1R.height/2-line1R.height/2)
                {slider_f1R.x=(line1R.x-slider1R.width/2)+67.5f*(int)((memento.pos.x-line1R.x+67.5f/2*memento.minMulti)/(67.5f*memento.minMulti))*memento.minMulti;//-slider1R.width/2 (memento.pos.x-line1R.x)/60
                memento.NOP=2+(int)((memento.pos.x-line1R.x+67.5f/2*memento.minMulti)/(67.5f*memento.minMulti));
                canBeLoad=false;}//line1.getWidth()/8=67.5f


        }

        if(Gdx.input.justTouched()&&!memento.isTouched){
            if(memento.pos.x>btn_reverseR.x&&memento.pos.x<btn_reverseR.x+btn_reverseR.width&&memento.pos.y>btn_reverseR.y&&memento.pos.y<btn_reverseR.y+btn_reverseR.height)btnNum=1;
            if(memento.pos.x>play_greenR.x&&memento.pos.x<play_greenR.x+play_greenR.width&&memento.pos.y>play_greenR.y&&memento.pos.y<play_greenR.y+play_greenR.height)btnNum=2;
            for(int i=0;i<memento.NOP;i++){
            if(memento.pos.x>30*btnMulti&&memento.pos.x<(30+player_btn.getWidth())*btnMulti&&memento.pos.y>slider1R.y-(30+(i+1)*player_btn.getHeight()+i*nx)*btnMulti&&memento.pos.y<slider1R.y-(30+(i)*player_btn.getHeight()+i*nx)*btnMulti){
            Gdx.input.getTextInput(memento.listener,"Dialog Title", "","");typeEnd=i;}}
            }


        if(memento.listener.text!=null&&memento.listener.text.length()>0){memento.rivals[typeEnd]=memento.listener.text;memento.listener.text=null;}//lastTypeEnd=typeEnd;}


        if(btnNum==1){if(Gdx.input.isTouched())memento.isTouched=true;gsm.set(new ContestChoose(gsm));}
        if(btnNum==2){if(Gdx.input.isTouched())memento.isTouched=true;memento.tournamentConfig=true;gsm.set(new GameConfig(gsm));}
        /*
        for(int i=2;i<memento.NOP+2;i++){
            if(btnNum==i){ System.out.println("!@#!@#!@#!@# "+btnNum);break;}
            //if(btnNum==i){
            if(typeEnd){memento.rivals[i-2]=memento.listener.text;break;}//}

        }*/
        //System.out.println(memento.listener.text);
        //if(btnNum==2){
        //    slider_f1R.x=(line1R.x-slider1R.width/2)+60*3*memento.minMulti;memento.ROWS=4;
        //    slider_f2R.x=(line2R.x-slider2R.width/2)+60*3*memento.minMulti;memento.COLS=4;}
        //if(btnNum!=0)System.out.println(btnNum);

        btnNum=0;

        if(!canBeLoad){canBeLoad=true;for(int i=0;i<memento.NOP;i++)if(memento.rivals[i]==null)canBeLoad=false;}
        /*
        if(lastNOP!=memento.NOP){lastNOP=memento.NOP;
            System.out.println(maxHeight);
            if(maxHeight>=(memento.NOP*NOP.getHeight()+(memento.NOP-1)*nx)*memento.minMulti)btnMulti=memento.minMulti;//else
            //btnMulti=maxHeight/(memento.NOP*NOP.getHeight()+(memento.NOP-1)*nx);
        }
*/

        //System.out.println(memento.NOP);
        sb.begin();
        sb.draw(bkg, 0, 0,0,0,bkg.getWidth(),bkg.getHeight(),memento.maxMulti,memento.maxMulti,0,0,0,bkg.getWidth(),bkg.getHeight(),false,false);
        sb.draw(btn_reverse, btn_reverseR.x, btn_reverseR.y,0,0,btn_reverse.getWidth(),btn_reverse.getHeight(),memento.minMulti,memento.minMulti,0,0,0,btn_reverse.getWidth(),btn_reverse.getHeight(),false,false);
        sb.draw(NOP, memento.WIDTH/2-NOP.getWidth()/2*memento.minMulti, btn_reverseR.y+(-30-NOP.getHeight())*memento.minMulti,0,0,NOP.getWidth(),NOP.getHeight(),memento.minMulti,memento.minMulti,0,0,0,NOP.getWidth(),NOP.getHeight(),false,false);
        sb.draw(line1, line1R.x, line1R.y,0,0,line1.getWidth(),line1.getHeight(),memento.minMulti,memento.minMulti,0,0,0,line1.getWidth(),line1.getHeight(),false,false);
        sb.draw(slider_f1, slider_f1R.x, slider_f1R.y,0,0,slider_f1.getWidth(),slider_f1.getHeight(),memento.minMulti,memento.minMulti,0,0,0,slider_f1.getWidth(),slider_f1.getHeight(),false,false);

        for(int i=0;i<memento.NOP;i++)
            sb.draw(player_btn, 30*memento.minMulti, slider1R.y-(30+(i+1)*player_btn.getHeight()+i*nx)*memento.minMulti,0,0,player_btn.getWidth(),player_btn.getHeight(),btnMulti,btnMulti,0,0,0,player_btn.getWidth(),player_btn.getHeight(),false,false);
        for(int i=0;i<memento.NOP;i++)
            if(memento.rivals[i]!=null)
            abc.draw(sb,memento.rivals[i],30*memento.minMulti, slider1R.y-(30+(i)*player_btn.getHeight()+i*nx)*memento.minMulti);

        if(canBeLoad)sb.draw(play_green, play_greenR.x, play_greenR.y,0,0,play_green.getWidth(),play_green.getHeight(),memento.minMulti,memento.minMulti,0,0,0,play_green.getWidth(),play_green.getHeight(),false,false);
        else{
            sb.draw(play_orange, play_orangeR.x, play_orangeR.y,0,0,play_orange.getWidth(),play_orange.getHeight(),memento.minMulti,memento.minMulti,0,0,0,play_orange.getWidth(),play_orange.getHeight(),false,false);}


            //sb.draw(btn_conCr, btn_conCrR.x, btn_conCrR.y,0,0,btn_conCr.getWidth(),btn_conCr.getHeight(),memento.minMulti,memento.minMulti,0,0,0,btn_conCr.getWidth(),btn_conCr.getHeight(),false,false);//-btn.getHeight()/2

        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
