package diegomiguel.lab02.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DIEGO on 14/05/2017.
 * Lab 05
 */

public class Pedido implements Serializable{

    public enum Recheio {
        SALAME("Salame", 1.00), CARNE("Carne", 2.00),
        FRANGO("Frango", 3.00), PERU("Peru", 4.00), ATUM("Atum", 5.00);

        private String nome;
        private double preco;

        Recheio(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }

        public static ArrayList<String> getList() {
            ArrayList<String> recheios = new ArrayList<String>();
            for (Recheio recheio :
                    Recheio.values()) {
                recheios.add(recheio.nome);
            }
            return recheios;
        }

        public double getPreco() {
            return preco;
        }

        public String getNome() {
            return nome;
        }
    }

    public enum Salada {
        TOMATE("Tomate"), ALFACE("Alface"), CEBOLA("Cebola"),
        MOSTARDA("Mostarda"), MAIONESE("Maionese"), CHIPOTLE("Chipotle");

        // O preco já está exposto na activity PedidoConfirm
        public static final double PRECO = 1.00;
        private String nome;

        Salada(String nome) {
            this.nome = nome;
        }

        public static ArrayList<String> getList() {
            ArrayList<String> salads = new ArrayList<String>();
            for (Salada salada :
                    Salada.values()) {
                salads.add(salada.nome);
            }
            return salads;
        }

        public String getNome() {
            return nome;
        }
    }

    public static final double PRECO_DO_PAO = 5.00;
    private String pao;
    private Recheio recheio;
    private ArrayList<String> salada;

    public Pedido(String pao, Recheio recheio, ArrayList<String> salada) {
        this.pao = pao;
        this.recheio = recheio;
        this.salada = salada;
    }

    public String getPao() {
        return pao;
    }

    public Recheio getRecheio() {
        return recheio;
    }

    public ArrayList<String> getSalada() {
        return salada;
    }

    public double Total() {
        return PRECO_DO_PAO + recheio.getPreco() + salada.size();
    }
}
