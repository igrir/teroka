package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.tanyoo.teroka.activities.BertarungActivity;
import com.tanyoo.teroka.entities.EBarDistance;
import com.tanyoo.teroka.entities.EBarHealth;
import com.tanyoo.teroka.entities.EBarHealthMusuh;
import com.tanyoo.teroka.entities.EButtonClient;
import com.tanyoo.teroka.entities.EButtonHost;
import com.tanyoo.teroka.entities.EDistance;
import com.tanyoo.teroka.entities.EGameView;
import com.tanyoo.teroka.entities.EHealthMusuh;
import com.tanyoo.teroka.entities.EHealthPoint;
import com.tanyoo.teroka.entities.EInfobar;
import com.tanyoo.teroka.entities.EKarakter;
import com.tanyoo.teroka.lib.GameView;

public class Bertarung extends GameView {

	private Paint cat = new Paint();
	
	public EKarakter ekarakter1, ekarakter2;
	
	public EInfobar einfo;
	public EBarDistance ebardistance;
	public EDistance edistance;
	public EHealthPoint ehealthpoint;
	public EBarHealth ebarhealth;
	public EGameView egameview;
	public EBarHealthMusuh ebarhealthmusuh;
	public EHealthMusuh ehealthmusuh;
	EButtonHost ebuttonhost;
	EButtonClient ebuttonclient;
	
	public Bertarung(Context context) {
		super(context);
		
		einfo = new EInfobar(getResources());
		ebardistance = new EBarDistance(getResources());
		edistance = new EDistance(getResources());
		ehealthpoint = new EHealthPoint(getResources());
		ebarhealth = new EBarHealth(getResources());
		egameview = new EGameView(getResources());
		ehealthmusuh = new EHealthMusuh(getResources());
		ebarhealthmusuh = new EBarHealthMusuh(getResources());
		ekarakter1 = new EKarakter(getResources());
		ekarakter2 = new EKarakter(getResources());
		ebuttonhost = new EButtonHost(getResources());
		ebuttonclient = new EButtonClient(getResources());
		
		addEntityCollection(einfo,
				  ebardistance,
				  edistance,
				  ehealthpoint,
				  ebarhealth,
				  egameview,
				  ehealthmusuh,
				  ebarhealthmusuh,
				  ekarakter1,
				  ebuttonhost,
				  ebuttonclient,	
				  ekarakter2);
	}
	
	
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		einfo.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(10));
		ebardistance.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		ebarhealth.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		edistance.resizeImage((int)getPercentWidth(15),(int)getPercentHeight(9));
		egameview.resizeImage((int)getPercentWidth(200),(int)getPercentHeight(25));
		ehealthpoint.resizeImage((int)getPercentWidth(15),(int)getPercentHeight(9));
		ebarhealthmusuh.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		ehealthmusuh.resizeImage((int)getPercentWidth(15),(int)getPercentHeight(9));
		ekarakter1.createSprites((int)getPercentWidth(10),(int)getPercentHeight(10));
		ekarakter2.createSprites((int)getPercentWidth(10),(int)getPercentHeight(10));
		ebuttonhost.resizeImage((int)getPercentWidth(42), (int)getPercentHeight(10));
		ebuttonclient.resizeImage((int)getPercentWidth(42), (int)getPercentHeight(10));
		
		//posisi
		einfo.setPosition(getPercentWidth(0), getPercentHeight(0));
		egameview.setPosition(getPercentWidth(0), getPercentHeight(10));
		ehealthpoint.setPosition(getPercentWidth(8), getPercentHeight(36));
		ehealthmusuh.setPosition(getPercentWidth(8), getPercentHeight(46));
		edistance.setPosition(getPercentWidth(8), getPercentHeight(56));
		ebarhealth.setPosition(getPercentWidth(27), getPercentHeight(36));
		ebarhealthmusuh.setPosition(getPercentWidth(27), getPercentHeight(46));
		ebardistance.setPosition(getPercentWidth(27), getPercentHeight(56));
		ekarakter1.setPosition(getPercentWidth(40), getPercentHeight(20));
		ekarakter2.setPosition(getPercentWidth(60), getPercentHeight(20));
		ebuttonhost.setPosition(getPercentWidth(29), getPercentHeight(70));
		ebuttonclient.setPosition(getPercentWidth(29), getPercentHeight(85));
		ready = true;
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
	}
	
}
