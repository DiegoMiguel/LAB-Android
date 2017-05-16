package diegomiguel.lab02.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import diegomiguel.lab02.R;
import diegomiguel.lab02.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    /**
     * Lab 03
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getFragmentManager().beginTransaction()
                            .replace(android.R.id.content, new SettingsFragment())
                            .commit();
    }
}
