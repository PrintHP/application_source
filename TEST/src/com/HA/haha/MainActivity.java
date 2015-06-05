package com.HA.haha;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	CalendarView cv;
	int count = 0;
	
	class MyCalendarListenr implements CalendarView.OnDateChangeListener {

		@Override
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth) {
			Toast.makeText(getApplicationContext(), dayOfMonth +"¿œ", Toast.LENGTH_LONG).show();
		}
		
	}
	
	MyCalendarListenr l  = new MyCalendarListenr();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button buttonNotify = (Button)findViewById(R.id.buttonNotify);
        
        cv = (CalendarView)findViewById(R.id.calendarView1);
        
        cv.setOnDateChangeListener(l);
//        cv.

        buttonNotify.setOnClickListener(buttonNotifyClickListener);
    }

    OnClickListener buttonNotifyClickListener = new OnClickListener() {
 


    	
        @Override
        public void onClick(View v) {


        	
            EditText editTextContentText = (EditText)findViewById(R.id.editTextContentText);
            

            
            Notification.Builder notiBuilder = new Notification.Builder(getApplicationContext());
            notiBuilder.setSmallIcon(R.drawable.ic_launcher);
            notiBuilder.setContentText(editTextContentText.getText().toString());
            
            Notification noti = notiBuilder.build();
            
            noti.flags = Notification.FLAG_NO_CLEAR;


            NotificationManager notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notiManager.notify(count++, noti);
                      
            
            
        }

    };

}



