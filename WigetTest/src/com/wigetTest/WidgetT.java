package com.wigetTest;



import java.io.IOException;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import android.widget.RemoteViews;

public class WidgetT extends AppWidgetProvider {
	
	static final String SLIENT = "com.mamp.SLIENT";
	static final String VIBRATE = "com.mamp.VIBRATE";
	static final String RING = "com.mamp.RING";
	
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		Log.d("***********************","in Receive");
		
		AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

		String tmp = intent.getAction();
		if ( tmp != null) {
			Log.d("***********************", intent.getAction());
			if(tmp.equals(RING)) {
				am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				
//				SoundPool sp = new SoundPool( 10, AudioManager.STREAM_MUSIC, 0) ;
//				AssetManager as = context.getAssets();
//				AssetFileDescriptor des= null;
//				try {
//					des = as.openFd("explosion.ogg");
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				int s1 = sp.load(des, 1);
//				sp.play( s1, 1, 1, 0, 0, 1);
//				sp.release();
				
			}
			if(tmp.equals(VIBRATE)) {
				am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
				Vibrator vr = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
				vr.vibrate(1000);
			}
			if(tmp.equals(SLIENT)) {
				am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			}
			
		}
		

	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		
		RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.wiget);
		AppWidgetManager am = AppWidgetManager.getInstance(context);
		ComponentName thiswid = new ComponentName(context, WidgetT.class);
		int [] appwid = am.getAppWidgetIds(thiswid);
		
		Intent i = new Intent(RING);
		
		PendingIntent ring = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
		view.setOnClickPendingIntent(R.id.button1,ring);
		
		Intent i2 = new Intent(VIBRATE,Uri.EMPTY, context,  WidgetT.class);
		PendingIntent vibrate = PendingIntent.getBroadcast(context, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
		view.setOnClickPendingIntent(R.id.button2, vibrate);
		
		Intent i3 = new Intent(SLIENT,Uri.EMPTY, context,  WidgetT.class);
		PendingIntent slient = PendingIntent.getBroadcast(context, 0, i3, PendingIntent.FLAG_UPDATE_CURRENT);
		view.setOnClickPendingIntent(R.id.button3, slient);
		
		for (int j = 0 ; j< appwid.length; j++ ) {
			am.updateAppWidget(appwid[j], view);
		}
		
	}

}
