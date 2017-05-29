package diegomiguel.lab02.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by DIEGO on 16/05/2017.
 */

public class BDController {
    private SQLiteDatabase db;
    private BDManager bdManager;
    private Cursor pedidos;

    public BDController(Context context) {
        bdManager = new BDManager(context);
    }

    public boolean inserirDados(String pao, String recheio, ArrayList<String> salada) {
        db = bdManager.getWritableDatabase();

        ContentValues items = new ContentValues();
        items.put(BDManager.colPAO, pao);
        items.put(BDManager.colRECHEIO, recheio);
        items.put(BDManager.colSALADA, stringFromListToBD(salada));


        long result = db.insert(BDManager.tblPEDIDO, null, items);

        db.close();

        return result != -1;
    }

    private String stringFromListToBD(ArrayList<String> salada) {
        String saladaBD = "";
        for (int index = 0; index < salada.size(); index++){
            saladaBD += salada.get(index) + ",";
        }
        return saladaBD;
    }

    public Cursor getPedido() {

        db = bdManager.getReadableDatabase();

        String sql = "SELECT pao, recheio, salada FROM pedido ORDER BY id DESC LIMIT 1";

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        else {
            return null;
        }
        db.close();
        return cursor;
    }
}
