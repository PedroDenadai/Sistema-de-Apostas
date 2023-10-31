package Problema3;

import Tools.InOut;

import java.util.*;

public class Aposta {
    int idAposta;
    double valorAposta;

    List<Jogo> jogoList = new ArrayList<>(); // key: idJogo valor: valor da aposta

    public Aposta() {

    }

    public Aposta(int idAposta, double valorAposta) {
        this.idAposta = idAposta;
        this.valorAposta = valorAposta;
    }


    /**
     * Insere um jogo na lista de jogos da aposta se nao existir
     * @param jogo Jogo para ser inserido
     * @return True se der certo, false se nao incluir
     */
    boolean inserirJogo(Jogo jogo){
        if(this.jogoList.contains(jogo)){
            return false;
        }else{
            this.jogoList.add(jogo);
            return true;
        }
    }




    /**
     * Mostra todos os jogos que foram inclusos e os Padroes
     * @return String com todos os jogos
     */
    String mostrarJogos() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Jogo jogo : jogoList) {
            i++;
            sb.append("Jogo" + i + ": ").append(jogo.descritivo.get(0))
                    .append(" (").append(jogo.pesos.get(0)).append(") X ")
                    .append(jogo.descritivo.get(1))
                    .append(" (").append(jogo.pesos.get(1)).append(")\n");
        }
        return sb.toString();
    }

    /**
     * Recebe do usuário dois times e cria um novo objeto Jogo com os times e chama a funcao addTimes para gerar as Odds aleatoriamente
     */
    void addJogo(){
        String time1 = InOut.leString("Qual o Primeiro Time: ");
        String time2 = InOut.leString("Qual o Segundo Time: ");

        Jogo jogo = new Jogo();
        jogo.addTimes(time1, time2);
        this.inserirJogo(jogo);
    }

}
