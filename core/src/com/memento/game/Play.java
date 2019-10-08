package com.memento.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static com.badlogic.gdx.math.MathUtils.random;
import static java.lang.StrictMath.abs;
import static java.util.Arrays.binarySearch;


public class Play extends State{



    void backFlipAnim(){
            if(!secondBackFlip)backAnimMult-=Gdx.graphics.getDeltaTime()*20*multi;
            if(backAnimMult<=0){secondBackFlip=true;backAnimMult=0;}
            if(secondBackFlip)backAnimMult+=Gdx.graphics.getDeltaTime()*20*multi;
            if(secondBackFlip&&backAnimMult>=multi){isBackAnim=false;secondBackFlip=false;backAnimMult=multi;}
    }

    void flipAnim(){
        if(isAnim1){
            if(!secondFlip1)animMult1-=Gdx.graphics.getDeltaTime()*10*multi;
            if(animMult1<=0){secondFlip1=true;animMult1=0;}
            if(secondFlip1)animMult1+=Gdx.graphics.getDeltaTime()*10*multi;
            if(secondFlip1&&animMult1>=multi){isAnim1=false;secondFlip1=false;animMult1=multi;}
        }
        if(isAnim2){
            if(!secondFlip2)animMult2-=Gdx.graphics.getDeltaTime()*10*multi;
            if(animMult2<=0){secondFlip2=true;animMult2=0;}
            if(secondFlip2)animMult2+=Gdx.graphics.getDeltaTime()*10*multi;
            if(secondFlip2&&animMult2>=multi){isAnim2=false;secondFlip2=false;animMult2=multi;}
        }
    }


    int rnd,rowi,colj,margin=15,posRow1,posCol1,posRow2,posCol2,p1_score,p2_score,marginY,marginX,delCol,delRow,lastPosRow1,lastPosCol1,lastPosRow2,lastPosCol2;
    float tileTime,realHeight,realWidth,multi,animMult1,animMult2,backAnimMult;
    Texture[][] tiles;
    Texture pl1,pl2,tshirt,pl1_f,pl2_f,bkg;
    BitmapFont abc;
    ArrayList<Integer> doublesL;
    boolean buildingGrip=true,haveInt,firstTouch,firstPlayer=true,incorrect,correct,emptyTiles,isAnim1,isAnim2,secondFlip1,secondFlip2,isBackAnim,secondBackFlip;
    public Play(GameStateManager gsm){
        super(gsm);
        doublesL = new ArrayList();

        abc = new BitmapFont();
        //img = new Texture("badlogic.jpg");
        bkg = new Texture("MenuBackground.png");
        pl1 = new Texture("1player.png");
        pl2 = new Texture("2player.png");
        pl1_f = new Texture("1player_faded.png");
        pl2_f = new Texture("2player_faded.png");
        tshirt = new Texture("tshirt.png");
        tiles = new Texture[memento.COLS][memento.ROWS];

        for(int i=0;i<memento.COLS;i++)for(int j=0;j<memento.ROWS;j++)
            if(tiles[i][j]==null) {//TimeUtils.nanoTime()-tileTime>1000000000
                //tileTime= TimeUtils.nanoTime();
                while (true) {
                    rnd = random(0, memento.maxTile-1);
                    //for(int in=0;in<20;in++)
                    for(int in:doublesL)
                        if(in==rnd) haveInt=true;
                    if(!haveInt){doublesL.add(rnd);break;}
                    haveInt=false;
                }
                System.out.println(rnd);
                tiles[i][j] = new memento().allTxt[rnd];
                while (true) {
                    rowi = random(0, memento.COLS - 1);
                    colj = random(0, memento.ROWS - 1);
                    if (tiles[rowi][colj] == null) {
                        tiles[rowi][colj] = new memento().allTxt[rnd];
                        break;
                    }
                }
            }
        realHeight=(memento.HEIGHT-300*memento.minMulti-60*memento.minMulti-(memento.ROWS-1)*margin*memento.minMulti)/memento.ROWS;
        realWidth=(memento.WIDTH-60*memento.minMulti-(memento.COLS-1)*margin*memento.minMulti)/memento.COLS;
        //if(realHeight>realWidth)multi=(realWidth/150);else multi=(realHeight/150);
        if(realHeight>realWidth)multi=(realWidth/150);else multi=(realHeight/150);
        //System.out.println((memento.HEIGHT-300-60-(memento.ROWS-1)*5)+" "+realHeight);
        //System.out.println((memento.WIDTH-60-(memento.COLS-1)*5)+" "+realWidth);
        marginX=(int)(memento.WIDTH-margin*(memento.COLS-1)*memento.minMulti-150*multi*memento.COLS)/2;
        marginY=(int)(memento.HEIGHT-300*memento.minMulti-margin*(memento.ROWS-1)*memento.minMulti-150*multi*memento.ROWS)/2;
        System.out.println("COLS "+memento.COLS);
        System.out.println("ROWS "+memento.ROWS);
        System.out.println("multi "+multi);
        System.out.println(memento.WIDTH);
        System.out.println(5*(memento.COLS-1)-150*multi*memento.COLS);
        System.out.println(marginX);
        abc.getData().scaleX=10;
        abc.getData().scaleY=10;
        //abc.setColor(Color.;
        abc.setColor(new Color(0x1a1a1aff));//c27013
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
        //System.out.println("AAAAAAAAA");
        emptyTiles=true;
        if(isAnim1||isAnim2)flipAnim();
        if(isBackAnim)backFlipAnim();
        if(Gdx.input.justTouched()&&!memento.isTouched&&!isBackAnim&&!incorrect)//&&TimeUtils.nanoTime()-tileTime>1000000000*10&&!correct
            for(int i=0;i<memento.COLS;i++)for(int j=0;j<memento.ROWS;j++)
                if(memento.pos.x>marginX+i*150*multi+i*margin*memento.minMulti&&memento.pos.x<marginX+i*150*multi+i*margin*memento.minMulti+150*multi&&memento.pos.y>marginY+j*150*multi+j*margin*memento.minMulti&&memento.pos.y<marginY+j*150*multi+j*margin*memento.minMulti+150*multi){
                    if(tiles[i][j]!=null){
                        if(correct){correct=false;tiles[posCol1][posRow1]=null;tiles[posCol2][posRow2]=null;
                            if(firstPlayer)p1_score+=1;else p2_score+=1;
                        }
                    if(!firstTouch){animMult1=multi;firstTouch=true;posCol1=i;posRow1=j;isAnim1=true;animMult1=multi;}else//!secondTouch&&
                    if(firstTouch&&!(posCol1==i&&posRow1==j)){animMult2=multi;posCol2=i;posRow2=j;firstTouch=false;
                        isAnim2=true;animMult2=multi;
                        tileTime = TimeUtils.nanoTime();
                        if(tiles[posCol1][posRow1]!=tiles[posCol2][posRow2]){
                            incorrect=true;firstPlayer=!firstPlayer;lastPosCol1=posCol1;lastPosRow1=posRow1;lastPosCol2=posCol2;lastPosRow2=posRow2;
                        } else correct=true;
                    }}

                    /*
                    else if(secondTouch){secondTouch=false;
                        tileTime = TimeUtils.nanoTime();
                        if(tiles[posCol1][posRow1]!=tiles[posCol2][posRow2]){
                        incorrect=true;
                    }//firstPlayer=!firstPlayer;
                         else  correct=true;
                    //if(!firstTouch){}
                }*/
                    //System.out.println("firstTouch "+firstTouch);
                    //System.out.println("secondTouch "+secondTouch);
                    //System.out.println();


                }


        if(Gdx.input.isKeyJustPressed(Input.Keys.A))gsm.set(new Menu(gsm));
        //System.out.println(marginX+0*150*multi+0*margin*memento.minMulti);
        //TimeUtils.nanoTime()-tileTime>1000000000
        //tileTime= TimeUtils.nanoTime();
        if(TimeUtils.nanoTime()-tileTime>1000000000*0.6){
            if(incorrect){incorrect=false;
            isBackAnim=true;
            backAnimMult=multi;
            }
            //incorrect=false;
        if(correct){correct=false;tiles[posCol1][posRow1]=null;tiles[posCol2][posRow2]=null;
        if(firstPlayer)p1_score+=1;else p2_score+=1;
        //System.out.println("p1_score "+p1_score);System.out.println("p2_score "+p2_score);
        }}
        sb.begin();
        sb.draw(bkg, 0, 0,0,0,bkg.getWidth(),bkg.getHeight(),memento.maxMulti,memento.maxMulti,0,0,0,bkg.getWidth(),bkg.getHeight(),false,false);
        //sb.draw(img, 300, 300);
        if(firstPlayer){
            sb.draw(pl1, 0, memento.HEIGHT-pl1.getHeight()*memento.minMulti,0,0,pl1.getWidth(),pl1.getHeight(),memento.minMulti,memento.minMulti,0,0,0,pl1.getWidth(),pl1.getHeight(),false,false);
            sb.draw(pl2_f, memento.WIDTH-pl2_f.getWidth()*memento.minMulti, memento.HEIGHT-pl2_f.getHeight()*memento.minMulti,0,0,pl2_f.getWidth(),pl2_f.getHeight(),memento.minMulti,memento.minMulti,0,0,0,pl2_f.getWidth(),pl2_f.getHeight(),false,false);
        }
        else{
            sb.draw(pl1_f, 0, memento.HEIGHT-pl1_f.getHeight()*memento.minMulti,0,0,pl1_f.getWidth(),pl1_f.getHeight(),memento.minMulti,memento.minMulti,0,0,0,pl1_f.getWidth(),pl1_f.getHeight(),false,false);
            sb.draw(pl2, memento.WIDTH-pl2.getWidth()*memento.minMulti, memento.HEIGHT-pl2.getHeight()*memento.minMulti,0,0,pl2.getWidth(),pl2.getHeight(),memento.minMulti,memento.minMulti,0,0,0,pl2.getWidth(),pl2.getHeight(),false,false);
        }
        /*
        for(int i=0;i<memento.ROWS;i++)//for(int j=0;j<memento.COLS;j++)
            //if(tiles[i][j]==null)
            sb.draw(memento.allTxt[i],150*i,0);*/
        //marginX

        for(int i=0;i<memento.COLS;i++)for(int j=0;j<memento.ROWS;j++)
            if(tiles[i][j]!=null){
                //System.out.println("!@##@!");
            if(posCol1==i&&posRow1==j&&firstTouch){if(isAnim1&&!secondFlip1)
                sb.draw(tshirt, marginX+i*150*multi+i*margin*memento.minMulti-(tiles[i][j].getWidth()-tiles[i][j].getWidth()*multi)/2, marginY+j*150*multi+j*margin*memento.minMulti,tiles[i][j].getWidth()/2,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),animMult1,multi,0,0,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),false,false);else
                sb.draw(tiles[i][j], marginX+i*150*multi+i*margin*memento.minMulti-(tiles[i][j].getWidth()-tiles[i][j].getWidth()*multi)/2, marginY+j*150*multi+j*margin*memento.minMulti,tiles[i][j].getWidth()/2,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),animMult1,multi,0,0,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),false,false);}

            else if(((posCol1==i&&posRow1==j)||(posCol2==i&&posRow2==j))&&!firstTouch&&(incorrect||correct)){
                if(posCol1==i&&posRow1==j)if(isAnim1&&!secondFlip1)
                    sb.draw(tshirt, marginX+i*150*multi+i*margin*memento.minMulti-(tiles[i][j].getWidth()-tiles[i][j].getWidth()*multi)/2, marginY+j*150*multi+j*margin*memento.minMulti,tiles[i][j].getWidth()/2,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),animMult1,multi,0,0,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),false,false);else
                    sb.draw(tiles[i][j], marginX+i*150*multi+i*margin*memento.minMulti-(tiles[i][j].getWidth()-tiles[i][j].getWidth()*multi)/2, marginY+j*150*multi+j*margin*memento.minMulti,tiles[i][j].getWidth()/2,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),animMult1,multi,0,0,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),false,false);
                if(posCol2==i&&posRow2==j)if(isAnim2&&!secondFlip2)
                    sb.draw(tshirt, marginX+i*150*multi+i*margin*memento.minMulti-(tiles[i][j].getWidth()-tiles[i][j].getWidth()*multi)/2, marginY+j*150*multi+j*margin*memento.minMulti,tiles[i][j].getWidth()/2,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),animMult2,multi,0,0,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),false,false);else
                    sb.draw(tiles[i][j], marginX+i*150*multi+i*margin*memento.minMulti-(tiles[i][j].getWidth()-tiles[i][j].getWidth()*multi)/2, marginY+j*150*multi+j*margin*memento.minMulti,tiles[i][j].getWidth()/2,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),animMult2,multi,0,0,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),false,false);}else



            if(((lastPosCol1==i&&lastPosRow1==j)||(lastPosCol2==i&&lastPosRow2==j))&&isBackAnim){
                if(secondBackFlip)
                    sb.draw(tshirt, marginX+i*150*multi+i*margin*memento.minMulti-(tiles[i][j].getWidth()-tiles[i][j].getWidth()*multi)/2, marginY+j*150*multi+j*margin*memento.minMulti,tiles[i][j].getWidth()/2,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),backAnimMult,multi,0,0,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),false,false);else
                    sb.draw(tiles[i][j], marginX+i*150*multi+i*margin*memento.minMulti-(tiles[i][j].getWidth()-tiles[i][j].getWidth()*multi)/2, marginY+j*150*multi+j*margin*memento.minMulti,tiles[i][j].getWidth()/2,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),backAnimMult,multi,0,0,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),false,false);}

            else sb.draw(tshirt, marginX+i*150*multi+i*margin*memento.minMulti-(tiles[i][j].getWidth()-tiles[i][j].getWidth()*multi)/2, marginY+j*150*multi+j*margin*memento.minMulti,tiles[i][j].getWidth()/2,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),multi,multi,0,0,0,tiles[i][j].getWidth(),tiles[i][j].getHeight(),false,false);
                emptyTiles=false;
            }
        //abc.draw(sb," "+p1_score,pl1_f.getWidth()*memento.minMulti,memento.HEIGHT);
        //abc.draw(sb," "+p2_score,memento.WIDTH-pl2_f.getWidth()*memento.minMulti*2,memento.HEIGHT);
        //abc.draw(sb,"1",memento.WIDTH/2-memento.WIDTH/10,memento.HEIGHT);
        abc.draw(sb,""+p1_score,memento.WIDTH/2-memento.WIDTH/10,memento.HEIGHT,0,0,false);
        abc.draw(sb,""+p2_score,memento.WIDTH/2+memento.WIDTH/10,memento.HEIGHT);
        //abc.draw(sb,"4",memento.WIDTH/2+memento.WIDTH/10,memento.HEIGHT,0,memento.WIDTH/2,false);
        //sb.draw(memento.allTxt[1], 150, 150);
        sb.end();

        if(emptyTiles&&!memento.tournamentPlay){if(p1_score>p2_score)memento.player1win=true;if(p1_score<p2_score)memento.player1win=false;memento.scoreAbs=abs(p1_score-p2_score);if(Gdx.input.isTouched())memento.isTouched=true;gsm.set(new EndGame(gsm));}
        else if(emptyTiles&&memento.tournamentPlay){memento.PlayCounter[memento.firstPlayerInt]++;memento.PlayCounter[memento.secondPlayerInt]++;
            if(p1_score>p2_score){memento.Tscore[memento.firstPlayerInt]++;memento.player1win=true;}if(p1_score<p2_score){memento.Tscore[memento.secondPlayerInt]++;memento.player1win=false;}memento.scoreAbs=abs(p1_score-p2_score);if(Gdx.input.isTouched())memento.isTouched=true;gsm.set(new EndGame(gsm));}
        //gsm.set(new Menu(gsm));
        //gsm.pop();
    }

    @Override
    protected void dispose() {

    }
}
