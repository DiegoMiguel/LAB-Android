package diegomiguel.lab02.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DIEGO on 16/05/2017.
 */

public class BDManager extends SQLiteOpenHelper {
    private static final String nomeBD = "subway.bd";
    public static final String tblPEDIDO = "pedido";
    public static final String colID = "id";
    public static final String colPAO = "pao";
    public static final String colRECHEIO = "recheio";
    public static final String colSALADA = "salada";
    public static final String colTIME = "timestamp";
    private static final int VERSAO = 1;


    public BDManager(Context context) {
        super(context, nomeBD, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * Neste projeto, criarei a tabela nesta classe, no entanto, frente à necessidade de criação
         * de várias classes, é indicado ter uma classe específica para manipulação de cada uma, para
         * esse projeto seria PedidoDAO.
         * vide: https://androiddevbr.wordpress.com/2013/02/19/sqlite-banco-de-dados-no-android/
         */
        String sqlTblPedido = "CREATE TABLE " + tblPEDIDO + "(" +
                colID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                colPAO + " TEXT," +
                colRECHEIO + " TEXT," +
                colSALADA + " TEXT," +
                colTIME + " DATETIME DEFAULT (datetime('now', 'localtime') ) NOT NULL)";

        db.execSQL(sqlTblPedido);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tblPEDIDO );
        onCreate(db);
    }
}
