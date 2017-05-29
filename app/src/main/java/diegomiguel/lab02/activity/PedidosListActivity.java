package diegomiguel.lab02.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import diegomiguel.lab02.DAO.BDController;
import diegomiguel.lab02.R;

public class PedidosListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_list);

        BDController bdController = new BDController(this);

        Cursor cursor = bdController.getPedido();

        /*String[] coluna = new String[] {BDManager.colID};
        int[] idViews = new int[] {R.id.idLivro, R.id.nomeLivro};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this, R.layout.activity_settings,
                                                                cursor, coluna, idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);*/
    }
}
