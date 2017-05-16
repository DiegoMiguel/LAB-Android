package diegomiguel.lab02.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import diegomiguel.lab02.R;
import diegomiguel.lab02.model.Pedido;

public class PedidoActivity extends AppCompatActivity {

    RadioGroup radioGPaes;
    Spinner spinRecheios;
    GridView gdSalad;
    TextView txtEstabelecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        // tratando escolha do pao
        radioGPaes = (RadioGroup) findViewById(R.id.rgPaes);

        // escolha dos recheios
        spinRecheios = (Spinner) findViewById(R.id.spinRecheios);

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>
                                            (this,
                                             android.R.layout.simple_spinner_dropdown_item,
                                             Pedido.Recheio.getList());
        spinRecheios.setAdapter(spinAdapter);

        // Escolha da salada e dos molhos
        gdSalad = (GridView) findViewById(R.id.gdSalad);
        ArrayAdapter<String> gridAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_multiple_choice, Pedido.Salada.getList());
        gdSalad.setAdapter(gridAdapter);

        // Preferencia de estabelecimento do usuário
        // Lab 03
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        txtEstabelecimento = (TextView) findViewById(R.id.txtEstabelecimento);
        txtEstabelecimento.setText(preferencias.getString("listPrefEstabelecimento", "Default"));
    }

    public void btnConfirmarClick(View viewButton){
        //Obtendo a opção do Pao
        RadioButton paoSelecionado = (RadioButton) radioGPaes.findViewById(
                                                    radioGPaes.getCheckedRadioButtonId());

        String strRecheio = (String) spinRecheios.getSelectedItem();

        // Obtendo a opção da Salada e dos molhos
        ArrayList<String> saladChoice = recuperarSalada();

        Intent intentRealizarPedido = new Intent(this, PedidoConfirm.class);

        intentRealizarPedido.putExtra("pedido", new Pedido(paoSelecionado.getText().toString(),
                Pedido.Recheio.valueOf(strRecheio.toUpperCase()), saladChoice));

        startActivity(intentRealizarPedido);
        finish();
    }

    private ArrayList<String> recuperarSalada() {
        //Retorna um mapa (int, bool) com as posições dos itens marcados
        SparseBooleanArray mapEscolhidos = gdSalad.getCheckedItemPositions();

        ArrayList<String> listEscolhidos = new ArrayList<>();
        Pedido.Salada[] salads = Pedido.Salada.values();

        //itera sobre o mapa para descobrir as posições dos itens escolhidos
        for (int index=0; index < mapEscolhidos.size(); index++){
            if (mapEscolhidos.valueAt(index)){ // Checa se a opção realmente foi escolhida
                                                // Se o usuário voltar e desmarcar um item,
                                                // ele continuará na lista com o valor false
                int posicaoEscolha = mapEscolhidos.keyAt(index);

                listEscolhidos.add(salads[posicaoEscolha].getNome());
            }
        }
        return listEscolhidos;
    }

    // Lab 03
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menuPreferencias){
            Intent escolherPreferencias = new Intent(this, SettingsActivity.class);
            startActivityForResult(escolherPreferencias, 0);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        txtEstabelecimento.setText(preferencias.getString("listPrefEstabelecimento", "Default"));
    }// Lab 03
}
