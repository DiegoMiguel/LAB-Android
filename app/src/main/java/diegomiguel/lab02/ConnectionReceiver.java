package diegomiguel.lab02;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Lab 04
 */
public class ConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conexaoInternet = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo conexaoInfo = conexaoInternet.getActiveNetworkInfo();

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, criaNotificacao(context).build());
    }

    private NotificationCompat.Builder criaNotificacao(Context context) {
        Intent intentSite = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.subway.com/pt-BR"));
        PendingIntent intentNotificacao = PendingIntent.getActivity(context, 0, intentSite, 0);

        return  new NotificationCompat.Builder(context)
                .setContentTitle("SUBWAY")
                .setContentText("Acesse nossa página online!")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.subway_logo)
                .setContentIntent(intentNotificacao);
    }
}
