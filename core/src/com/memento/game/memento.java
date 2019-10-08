package com.memento.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class memento extends ApplicationAdapter {
    static int WIDTH,HEIGHT,COLS=1,ROWS=1,NOP=2,scoreAbs=0,RWIDTH,RHEIGHT,maxTile=60,firstPlayerInt,secondPlayerInt;
    static boolean yes=true,isTouched,player1win,tournamentConfig,tournamentPlay,tournamentEnd;
	static float minMulti,maxMulti;
	private OrthographicCamera camera;
	private Viewport viewport;
	SpriteBatch batch;
	BitmapFont abc;
	Texture img;
	static Texture[] allTxt;
	static Vector3 pos;
	GameStateManager gsm;
	static Music music;
	public static MyTextInputListener listener;
	static String[] rivals;
	static Integer[] Tscore;
	static Integer[] PlayCounter;
	@Override
	public void create () {
		pos=new Vector3();
		pos.x=0;
		pos.y=0;
		abc = new BitmapFont();
		music = Gdx.audio.newMusic(Gdx.files.internal("Video.mp3"));
		music.setLooping(true);

		WIDTH = 720;//1440x720
		HEIGHT = 1440;
		WIDTH = 1080;//
		HEIGHT = 1920;
		WIDTH = Gdx.graphics.getWidth();//720
		HEIGHT = Gdx.graphics.getHeight();//1440

		//if((float)WIDTH/1080>(float)HEIGHT/1920)multi=(float)WIDTH/1080; else multi=(float)HEIGHT/1920;//0.6666  0.75
		if((float)WIDTH/1080>(float)HEIGHT/1920){maxMulti=(float)WIDTH/1080; minMulti=(float)HEIGHT/1920;}
		else{maxMulti=(float)HEIGHT/1920; minMulti=(float)WIDTH/1080;}

		//0.6666  0.75
		//multi=0.7f;
		//System.out.println((float)(720%1080));
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new FitViewport(WIDTH, HEIGHT, camera);
		viewport.apply();
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		gsm = new GameStateManager();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		allTxt = new Texture[maxTile];
		for(int i=0;i<maxTile;i++)allTxt[i]=new Texture("tiles/"+(i+1)+".png");
		System.out.println("1");
		gsm.push(new Menu(gsm));
		System.out.println("3");
		rivals = new String[10];
		//rivals[0]="Dima";
        //rivals[1]="Amid";
		//rivals[2]="Third";
		//rivals[3]="Another";
		Tscore = new Integer[10];
		PlayCounter = new Integer[10];
		for(int i=0;i<10;i++)Tscore[i]=0;
		for(int i=0;i<10;i++)PlayCounter[i]=0;
		listener = new MyTextInputListener();


		//appListener = new ApplicationListener();
		//if(!music.isPlaying()) music.play();

	}

	@Override
	public void render () {
	    /*
		//if((float)1080/Gdx.graphics.getHeight()>=(float)1920/Gdx.graphics.getWidth()){pos.y=(int)1080-Gdx.input.getY()*1080/Gdx.graphics.getHeight();pos.x=(int)(Gdx.input.getX()-(Gdx.graphics.getWidth()-Gdx.graphics.getHeight()*1920/1080)/2)*1080/Gdx.graphics.getHeight();}
		//if((float)1080/Gdx.graphics.getHeight()<(float)1920/Gdx.graphics.getWidth()){pos.x=(int)Gdx.input.getX()*1920/Gdx.graphics.getWidth();pos.y=(int)1080-(Gdx.input.getY()-(Gdx.graphics.getHeight()-Gdx.graphics.getWidth()*1080/1920)/2)*1920/Gdx.graphics.getWidth();}
		//if((float)1080/Gdx.graphics.getHeight()>=(float)1920/Gdx.graphics.getWidth()){pos.y=(int)1080-Gdx.input.getY()*1080/Gdx.graphics.getHeight();pos.x=(int)(Gdx.input.getX()-(Gdx.graphics.getWidth()-Gdx.graphics.getHeight()*1920/1080)/2)*1080/Gdx.graphics.getHeight();}
		//if((float)1080/Gdx.graphics.getHeight()<(float)1920/Gdx.graphics.getWidth()){
			pos.x=(int)Gdx.input.getX()*WIDTH/Gdx.graphics.getWidth();
			//pos.y=(int)HEIGHT-(Gdx.input.getY()-(Gdx.graphics.getHeight()-Gdx.graphics.getWidth()*1080/1920)/2)*1920/Gdx.graphics.getWidth();
			pos.y=(int)HEIGHT-(Gdx.input.getY()-(Gdx.graphics.getHeight()-Gdx.graphics.getWidth()*HEIGHT/WIDTH)/2)*WIDTH/Gdx.graphics.getWidth();
		//}
		if(pos.x<0)pos.x=0;
		if(pos.x>1080)pos.x=1080;
		if(pos.y<0)pos.y=0;
		if(pos.y>1920)pos.y=1920;





		camera.update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();
		batch.draw(img, viewport.getScreenX(), viewport.getScreenY());
		abc.draw(batch,"X: "+pos.x,300,200);
		abc.draw(batch,"Y: "+pos.y,300,150);
		batch.end();
*/
	    //if((float)1080/Gdx.graphics.getHeight()>=(float)1920/Gdx.graphics.getWidth()){pos.y=(int)1080-Gdx.input.getY()*1080/Gdx.graphics.getHeight();pos.x=(int)(Gdx.input.getX()-(Gdx.graphics.getWidth()-Gdx.graphics.getHeight()*1920/1080)/2)*1080/Gdx.graphics.getHeight();}
		if((float)1080/Gdx.graphics.getHeight()<(float)1920/Gdx.graphics.getWidth())
		{
		pos.x=(int)Gdx.input.getX()*WIDTH/Gdx.graphics.getWidth();
		pos.y=(int)HEIGHT-(Gdx.input.getY()-(Gdx.graphics.getHeight()-Gdx.graphics.getWidth()*1080/1920)/2)*1920/Gdx.graphics.getWidth();
		pos.y=(int)HEIGHT-(Gdx.input.getY()-(Gdx.graphics.getHeight()-Gdx.graphics.getWidth()*HEIGHT/WIDTH)/2)*WIDTH/Gdx.graphics.getWidth();
		}
		if(pos.x<0)pos.x=0;
		if(pos.x>1080)pos.x=1080;
		if(pos.y<0)pos.y=0;
		if(pos.y>1920)pos.y=1920;

		//System.out.println((float)HEIGHT);
		//System.out.println((float)HEIGHT/1920);

		//System.out.println(Gdx.graphics.getWidth());

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		music.dispose();
	}
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	}
}
