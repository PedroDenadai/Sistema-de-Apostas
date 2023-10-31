package Problema3;

import Tools.InOut;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogo {
    int idJogo;
    List<String> descritivo = new ArrayList<>();
    List<Double> pesos = new ArrayList<Double>();
    String campeonato;
    double apostaMax;
    double premioMax;

    int ganhador; // se o primeiro ganhar será 0 se o segundo ganhar será 1

    public Jogo(int id, String campeonato, double apostaMax){
        this.idJogo = id;
        this.campeonato = campeonato;
        this.apostaMax = apostaMax;
    }

    public Jogo() {

    }

    /**
     * Adiciona os dois times na lista de times e gera os pesos para cada time aleatoriamente
     * @param time1 Time 1
     * @param time2 Time 2
     */
    void addTimes(String time1, String time2) {
        Random random = new Random();
        double p1 = Math.round((random.nextDouble() * 4) * 100.0) / 100;
        double p2 = Math.round((random.nextDouble() * 4) * 100.0) / 100;
        this.descritivo.add(time1);
        this.pesos.add(p1 + 1);
        this.descritivo.add(time2);
        this.pesos.add(p2 + 1);
    }


    /**
     * Mostra os times e recebe do usuário o time escolhido para a aposta
     * @return O idex do time escolhido
     */
    int timeAposta(){
        int time = InOut.leInt("Qual time voce vai apostar\nTime 0:"+ this.descritivo.get(0) + " (" + this.pesos.get(0) + ") "+ " X Time 1:" + this.descritivo.get(1) + " (" + this.pesos.get(1) + "): ");
        return time;
    }


    /**
     * Mostra os times do jogo e seus pesos e pede para o usuário o valor da aposta
     * @return O valor da aposta
     */
    double valorAposta(){
        double aposta = InOut.leDouble(this.descritivo.get(0) + " (" + this.pesos.get(0) + ") "+ " X " + this.descritivo.get(1) + " (" + this.pesos.get(1) + ") \nInsira o valor de Aposta: ");
        return aposta;
    }




}
