package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.tanyoo.teroka.activities.BertarungActivity;
import com.tanyoo.teroka.entities.EBarEmpty;
import com.tanyoo.teroka.entities.EBarHealth;
import com.tanyoo.teroka.entities.EBarHealthMusuh;
import com.tanyoo.teroka.entities.EButtonClient;
import com.tanyoo.teroka.entities.EButtonHost;
import com.tanyoo.teroka.entities.EGameView;
import com.tanyoo.teroka.entities.EHealthMusuh;
import com.tanyoo.teroka.entities.EHealthPoint;
import com.tanyoo.teroka.entities.EInfobar;
import com.tanyoo.teroka.entities.EKarakter;
import com.tanyoo.teroka.entities.ELose;
import com.tanyoo.teroka.entities.EPlatform;
import com.tanyoo.teroka.entities.EWin;
import com.tanyoo.teroka.lib.GameView;

public class Bertarung extends GameView {

	private Paint cat = new Paint();
	
	public EKarakter ekarakter1, ekarakter2;
	
	public EInfobar einfo;
	
	public EHealthPoint ehealthpoint;
	public EBarHealth ebarhealth;
	public EGameView egameview;
	public EBarHealthMusuh ebarhealthmusuh;
	public EHealthMusuh ehealthmusuh;
	public EButtonHost ebuttonhost;
	public EButtonClient ebuttonclient;
	public EWin ewin;
	public ELose elose;
	public EPlatform eplatform;
	
	
	public EBarEmpty ebaremptyMusuh;
	public EBarEmpty ebaremptyPlayer;
	
	public String statusBerdiriMusuh;
	public String healthMusuh;
	public String seranganMusuh;
	
	public String statusBerdiri;
	public String health;
	public String serangan;
	
	public Bertarung(Context context) {
		super(context);
		
		einfo = new EInfobar(getResources());
		ehealthpoint = new EHealthPoint(getResources());
		ebarhealth = new EBarHealth(getResources());
		egameview = new EGameView(getResources());
		ehealthmusuh = new EHealthMusuh(getResources());
		ebarhealthmusuh = new EBarHealthMusuh(getResources());
		ekarakter1 = new EKarakter(getResources());
		ekarakter2 = new EKarakter(getResources());
		ebuttonhost = new EButtonHost(getResources());
		ebuttonclient = new EButtonClient(getResources());
		ebaremptyMusuh = new EBarEmpty(getResources());
		ebaremptyPlayer = new EBarEmpty(getResources());
		ewin = new EWin(getResources());
		elose = new ELose(getResources());
		eplatform = new EPlatform(getResources());
		
		addEntityCollection(einfo,
				  ehealthpoint,
				  ebarhealth,
				  egameview,
				  ehealthmusuh,
				  ebarhealthmusuh,
				  ekarakter1,
				  ebuttonhost,
				  ebuttonclient,	
				  ekarakter2,
				  ebaremptyMusuh,
				  ebaremptyPlayer,
				  ewin,
				  elose,
				  eplatform);
	}
	
	
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		einfo.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(10));
		ebarhealth.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		ebaremptyPlayer.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		egameview.resizeImage((int)getPercentWidth(200),(int)getPercentHeight(25));
		ehealthpoint.resizeImage((int)getPercentWidth(15),(int)getPercentHeight(9));
		ebarhealthmusuh.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		ebaremptyMusuh.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		ehealthmusuh.resizeImage((int)getPercentWidth(15),(int)getPercentHeight(9));
		ekarakter1.createSprites((int)getPercentWidth(10),(int)getPercentHeight(10));
		ekarakter2.createSprites((int)getPercentWidth(10),(int)getPercentHeight(10));
		ebuttonhost.resizeImage((int)getPercentWidth(42), (int)getPercentHeight(10));
		ebuttonclient.resizeImage((int)getPercentWidth(42), (int)getPercentHeight(10));
		ewin.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(25));
		elose.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(25));
		eplatform.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(5));
		
		//posisi
		einfo.setPosition(getPercentWidth(0), getPercentHeight(0));
		egameview.setPosition(getPercentWidth(0), getPercentHeight(10));
		ehealthpoint.setPosition(getPercentWidth(8), getPercentHeight(36));
		ehealthmusuh.setPosition(getPercentWidth(8), getPercentHeight(46));
		ebarhealth.setPosition(getPercentWidth(27), getPercentHeight(36));
		ebarhealthmusuh.setPosition(getPercentWidth(27), getPercentHeight(46));
		ebaremptyPlayer.setPosition(ebarhealth.x+ebarhealth.width, getPercentHeight(36));
		ebaremptyMusuh.setPosition(ebarhealthmusuh.x+ebarhealthmusuh.width, getPercentHeight(46));
		ekarakter1.setPosition(getPercentWidth(40), getPercentHeight(21));
		ekarakter2.setPosition(getPercentWidth(60), getPercentHeight(21));
		ebuttonhost.setPosition(getPercentWidth(29), getPercentHeight(70));
		ebuttonclient.setPosition(getPercentWidth(29), getPercentHeight(85));
		ewin.setPosition(getPercentWidth(100), getPercentHeight(10));
		elose.setPosition(getPercentWidth(100), getPercentHeight(10));
		eplatform.setPosition(getPercentWidth(0), getPercentHeight(30));
		
		ready = true;
	}
	
	public void showEWin(boolean show){
		if (show) {
			ewin.setX(0);
		}else{
			ewin.setX(getPercentWidth(100));
		}
	}
	
	public void showELose(boolean show){
		if (show) {
			elose.setX(0);
		}else{
			elose.setX(getPercentWidth(100));
		}
	}
	
	public void setBarHealthMusuh(float percentHealth){
		float percentX = getPercentWidth(27) + ebarhealth.width*(percentHealth/100);
		ebaremptyMusuh.setX(percentX);
	}
	
	public void setBarHealthPlayer(float percentHealth){
		float percentX = getPercentWidth(27) + ebarhealth.width*(percentHealth/100);
		ebaremptyPlayer.setX(percentX);
	}
	
	@Override
	public void onDraw(Canvas c)
	{		
		Log.i("viewBertarung", "Tampilkan bertarung");
		super.onDraw(c);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

		//run di controller
		this.context.run();
		
	}
	
	public void hideMenu(){
		ebuttonclient.setX(getPercentWidth(100));
		ebuttonhost.setX(getPercentWidth(100));
	}
	
	
	
	@Override
	public void onMove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDown() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				if (ebuttonclient.isHit(posXDown, posYDown)) {
					((BertarungActivity)(this.context)).tombolClient();
				}
				if (ebuttonhost.isHit(posXDown, posYDown)) {
					((BertarungActivity)(this.context)).tombolHost();
				}

	}

	@Override
	public void onUp() {
	
	}
	
	@Override
	public void draw(Canvas c, Paint cat) {
		// TODO Auto-generated method stub
		cat.setColor(Color.WHITE);
		c.drawRect(0, 0, c.getWidth(), c.getHeight(), cat);
		
		// teks posisi kursor
		//String posisi = "x: " + String.valueOf(posXDown) + " , y:" + String.valueOf(posYDown);
		
		
		cat.setTextSize(getPercentFontSize(100));
		cat.setColor(Color.BLACK);
		c.drawText("Ini menu utama", 30, 50, cat);
		
		//		einfo.draw(c, cat);
		//		egameview.draw(c, cat);
		//		edistance.draw(c, cat);
		//		ehealthpoint.draw(c, cat);
		//		ebardistance.draw(c, cat);
		//		ebarhealth.draw(c, cat);
		//		estars.draw(c, cat);
		//		esteps.draw(c, cat);
		//		eshop.draw(c, cat);
		//		ecalore.draw(c, cat);
		//		epotion.draw(c, cat);
		//		elevel.draw(c, cat);
		
		drawEntityCollection(c, cat);
		
		
		//potion
		String str_musuh = "berdiri_musuh: "+this.statusBerdiriMusuh +
						   "serangan_musuh: "+this.seranganMusuh +
						   "health_musuh: "+this.healthMusuh; 
		String str_kita = "berdiri: "+this.statusBerdiri+
						   "serangan: "+this.serangan+
						   "health: "+this.health;
		
//		cat.setTextSize(getPercentFontSize(80));
//		c.drawText(str_musuh , getPercentHeight(0), getPercentWidth(50), cat);
//		c.drawText(str_kita , getPercentHeight(0), getPercentWidth(80), cat);
	}
	
	public void setMusuh(String berdiri, String serangan, String health){
		this.statusBerdiriMusuh = berdiri;
		this.seranganMusuh = serangan;
		this.healthMusuh = health;
	}
	
	public void setKita(String berdiri, String serangan, String health){
		this.statusBerdiri = berdiri;
		this.serangan = serangan;
		this.health = health;
	}
	
}
