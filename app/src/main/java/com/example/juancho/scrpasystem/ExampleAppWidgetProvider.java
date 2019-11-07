package com.example.juancho.scrpasystem;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
public class ExampleAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        //button dw widget
            for (int appWidgetId : appWidgetIds) {

                //programacion del botton
                Intent intent = new Intent(context, Inicio.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);


                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget);

                // vinculacion del botton
                views.setOnClickPendingIntent(R.id.btnwidget, pendingIntent);


                appWidgetManager.updateAppWidget(appWidgetId, views);


            }
    }
}
