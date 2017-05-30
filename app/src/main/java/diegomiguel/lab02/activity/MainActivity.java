package diegomiguel.lab02.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import diegomiguel.lab02.DAO.BDController;
import diegomiguel.lab02.R;
import diegomiguel.lab02.model.Pedido;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.btnFavPrefs:
                loadPreference();
                break;
            case R.id.btnFavBD:
                loadBD();
                break;
            case R.id.btnNovoPedido:
                intent = new Intent(this, PedidoActivity.class);
                startActivity(intent);
                break;
            // Lab 06
            case R.id.btnMedia:
                intent = new Intent(this, Multimedia.class);
                startActivity(intent);
                break;
        }
    }

    private void loadBD() {
        try {
            BDController bdController = new BDController(this);
            Cursor cursor = bdController.getPedido();
            String[] listSalada = cursor.getString(2).split(",");

            Pedido pedido = new Pedido(cursor.getString(0),
                    Pedido.Recheio.valueOf(cursor.getString(1).toUpperCase()),
                    new ArrayList<>(Arrays.asList(listSalada)));

            Intent intentRealizarPedido = new Intent(this, PedidoConfirm.class);

            intentRealizarPedido.putExtra("pedido", pedido);

            startActivity(intentRealizarPedido);
        }catch (Exception e){
            Toast.makeText(this, "Faça um novo pedido e salve em BD\nAssim, você pode acessá-lo aqui", Toast.LENGTH_LONG).show();
        }
    }

    private void loadPreference() {
        SharedPreferences pref = getSharedPreferences("favorito", MODE_PRIVATE);

        if (pref.getAll().isEmpty()){
            Toast.makeText(this, "Faça um novo pedido e salve em Preferência\nAssim, você pode acessá-lo aqui", Toast.LENGTH_LONG).show();
        }
        else {
            Pedido pedido = new Pedido(pref.getString("pao", "pao"),
                    Pedido.Recheio.valueOf(pref.getString("recheio", "recheio").toUpperCase()),
                    new ArrayList(pref.getStringSet("salada", null)));

            Intent intentRealizarPedido = new Intent(this, PedidoConfirm.class);

            intentRealizarPedido.putExtra("pedido", pedido);

            startActivity(intentRealizarPedido);
        }
    }
}
