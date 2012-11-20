package org.misty.rc.testwidget;

import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViews.RemoteView;

public class ExampleAppWidgetProvider extends AppWidgetProvider {
	private final String TAG = "ExampleWidget";
	private static final String ACTION_APP_INTERVAL = "org.misty.rc.ExampleAppWidget.INTERVAL";
	private static final long INTERVAL = 60 * 1000;

	private PendingIntent getAlertPendingIntent(Context context) {
		Intent alertIntent = new Intent(context, ExampleAppWidgetProvider.class);
		alertIntent.setAction(ACTION_APP_INTERVAL);
		
		return PendingIntent.getBroadcast(context, 0, alertIntent, 0);
	}
	
	private void setInterval(Context context, long interval) {
		PendingIntent operation = getAlertPendingIntent(context);
		AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		
		long now = System.currentTimeMillis();
		long oneHourAfter = ((long)(now / interval)) * interval + interval;
		am.set(AlarmManager.RTC, oneHourAfter, operation);
	}
	
	private void updateClock(Context context) {
		ComponentName cname = new ComponentName(context, ExampleAppWidgetProvider.class);
		RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.example_keyguard);
		long now = System.currentTimeMillis();
		Date date = new Date(now);
		
		DateFormat fmt;
		//time
		fmt = android.text.format.DateFormat.getTimeFormat(context);
		
		Pattern pt = Pattern.compile("^(\\D*)(\\d+\\:\\d+)(\\D*)$");
		Matcher match = pt.matcher(fmt.format(date));
		String prefix = "";
		String suffix = "";
		String TimeText = "";
		
		if(match.find()) {
			prefix = match.group(1).trim();
			suffix = match.group(2).trim();
			TimeText = match.group(3).trim();
		}
		
		//date
		fmt = android.text.format.DateFormat.getLongDateFormat(context);
		
		AppWidgetManager.getInstance(context).updateAppWidget(cname, rv);
	}
	
	@Override
	public void onAppWidgetOptionsChanged(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId,
			Bundle newOptions) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onAppWidgetOptionsChanged");
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId,
				newOptions);
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDeleted");
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDisabled");
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onEnabled");
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onReceive");
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onUpdate");
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
}
