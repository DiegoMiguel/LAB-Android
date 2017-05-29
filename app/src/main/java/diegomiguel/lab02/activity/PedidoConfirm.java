package diegomiguel.lab02.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Set;

import diegomiguel.lab02.DAO.BDController;
import diegomiguel.lab02.DAO.BDManager;
import diegomiguel.lab02.R;
import diegomiguel.lab02.model.Pedido;

public class PedidoConfirm extends AppCompatActivity {

    Pedido _pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_confirm);

        TextView txtPao = (TextView) findViewById(R.id.txtPao);
        TextView txtPaoPreco = (TextView) findViewById(R.id.txtPaoPreco);
        TextView txtRecheio = (TextView) findViewById(R.id.txtRecheio);
        TextView txtRecheioPreco = (TextView) findViewById(R.id.txtRecheioPreco);
        GridView gdSalad = (GridView) findViewById(R.id.gdSalad);
        TextView txtPrecoTotal = (TextView) findViewById(R.id.txtPrecoTotal);

        _pedido = (Pedido) getIntent().getSerializableExtra("pedido");

        txtPao.setText(_pedido.getPao());
        txtPaoPreco.setText("R$" + String.valueOf(_pedido.PRECO_DO_PAO));
        txtRecheio.setText(_pedido.getRecheio().getNome());
        txtRecheioPreco.setText(NumberFormat.getCurrencyInstance().format(_pedido.getRecheio().getPreco()));

        ArrayAdapter<String> gridAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        _pedido.getSalada());
        gdSalad.setAdapter(gridAdapter);

        txtPrecoTotal.setText(NumberFormat.getCurrencyInstance().format(_pedido.Total()));
    }

    // Lab 05
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnSavePrefs:
                saveInPreference();
                break;
            case R.id.btnSaveBD:
                saveOnBD();
                break;
            case R.id.btnNovoPedido:
                Intent intentNovoPedido = new Intent(this, PedidoActivity.class);
                startActivity(intentNovoPedido);
                finish();
                break;
        }

    }

    private void saveOnBD() {
        BDController bdController = new BDController(getApplicationContext());

        boolean sucesso = bdController.inserirDados(_pedido.getPao(),
                                                    _pedido.getRecheio().getNome(),
                                                    _pedido.getSalada());

        if (sucesso){
            Toast.makeText(this, "Dados salvos!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Um erro ocorreu, tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveInPreference() {
        SharedPreferences pref = getSharedPreferences("favorito", MODE_PRIVATE);

        Set<String> setSalad = new HashSet<String>(_pedido.getSalada());

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("pao",_pedido.getPao());
        editor.putString("recheio",_pedido.getRecheio().getNome());
        editor.putStringSet("salada", setSalad);

        if (editor.commit()){
            Toast.makeText(this, "Sanduíche Salvo!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Algo deu errado,\nTente novamente.", Toast.LENGTH_SHORT).show();
        }
    }
    // Lab 05

}
