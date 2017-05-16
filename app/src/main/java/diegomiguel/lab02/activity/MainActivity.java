package diegomiguel.lab02.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import diegomiguel.lab02.DAO.BDController;
import diegomiguel.lab02.R;
import diegomiguel.lab02.model.Pedido;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList a = Pedido.Salada.getList();

        String s = "";
        for (int id = 0; id < a.size(); id++){
            s += a.get(id) + ",";
        }

        String[] array = s.split(",");
        String t = "";
        for (String b :
                array) {
            t += b;
        }

        Toast.makeText(this, t + (a.size()==array.length), Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnFavPrefs:
                loadPreference();
                break;
            case R.id.btnFavBD:
                loadBD();
                break;
            case R.id.btnNovoPedido:
                Intent intentNovoPedido = new Intent(this, PedidoActivity.class);
                startActivity(intentNovoPedido);
                break;
        }
    }

    private void loadBD() {
        BDController bdController = new BDController(this);

        Cursor cursor = bdController.getPedidos();

        Toast.makeText(this, Arrays.toString(cursor.getColumnNames()), Toast.LENGTH_SHORT).show();
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
