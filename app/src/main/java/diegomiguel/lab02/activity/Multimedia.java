package diegomiguel.lab02.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import diegomiguel.lab02.R;

/**
 * Lab 06
 */
public class Multimedia extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);
    }

    public void onClick(View view) {
        Intent novaFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(novaFoto, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }
        Bundle bundle = data.getExtras();
        Bitmap foto = (Bitmap) bundle.get("data");

        ImageView imgFoto = (ImageView) findViewById(R.id.imgFoto);
        imgFoto.setImageBitmap(foto);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer = MediaPlayer.create(this, R.raw.eye_of_tiger);
        mediaPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.media_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            item.setIcon(android.R.drawable.ic_media_play);

            return true;
        }
        mediaPlayer.start();
        item.setIcon(android.R.drawable.ic_media_pause);

        return super.onOptionsItemSelected(item);
    }
}
