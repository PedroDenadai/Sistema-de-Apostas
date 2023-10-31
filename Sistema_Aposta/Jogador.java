package Problema3;


import Tools.InOut;

import java.util.*;


public class Jogador {
    int idJogador;
    String nome;
    String sobrenome;
    String apelido;
    String cpf;
    String nacionalidade;
    Date nascimento;
    int idade;

    Credito credito;

    List<Aposta> apostaList = new ArrayList<>();

    /**
     * Construtor 1
     * @param id
     * @param nome
     * @param cpf
     * @param nasc
     */
    public Jogador(int id, String nome, String cpf, Date nasc){
        this.idJogador = id;
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nasc;
        this.credito = new Credito(0, "R$");
    }

    /**
     * Construtor 2
     * @param nome
     * @param idade
     * @param idJogador
     * @param credito
     */
    public Jogador(String nome, int idade, int idJogador, Credito credito){
        this.nome = nome;
        this.idade = idade;
        this.idJogador = idJogador;
        this.credito = credito;
    }

    /**
     * Ve se a Aposta ja esta na lista de apostas do jogador ,se nao insere.
     * @param a Aposta para inserir na lista
     * @return Retorna true se der certo, false se nao
     */
    boolean inserirAposta(Aposta a){
        if(this.apostaList.contains(a)){
            return false;
        }else{
            this.apostaList.add(a);
            return true;
        }
    }

    /**
     * Saca o dinheiro do saldo
     * @param valor Valor a ser retirado, precisa ser menor ou igual ao saldo
     */
    void sacar(double valor){
        if(valor <= this.credito.saldo){
            this.credito.saldo -= valor;
        }

    }

    /**
     * Deposita ao saldo do jogador o valor
     * @param valor Valor que o jogador quer depositar
     */
    void depositar(double valor){
        this.credito.saldo += valor;
    }

    /**
     * Mostra alguns dados do Jogador
     * Nome, Idade, Saldo da conta
     */
    void mostrarDados(){
        InOut.MsgDeInformacao("Dados","Nome: " + this.nome + "\nIdade: " + this.idade + "\nSaldo: " + this.credito.moeda + this.credito.saldo);
    }


    /**
     * Esse método irá receber do usuário o jogo que ele quer apostar, mostrando todos os jogos para ele via a funcao Mostrar Jogos da Aposta
     * @param a Obejto aposta
     * @return Essa funcao retorna o index que vai ser usado para achar o jogo escolhi na Lista de Jogos da Aposta passada
     */
    int jogoAposta(Aposta a){
        int jogo = InOut.leInt("Escolha um dos Jogos\n"+ a.mostrarJogos());
        return jogo-1;
    }

    /**
     * Recebe um input do usuário via InOut e direciona a aplicacao de acordo com a escolha
     * 0 - Depositar
     * 1 - Sacar
     * 2 - Apostar
     * 3 - Inserir Jogo
     * Qualquer Outra - Sair do Menu
     * @param aposta Objeto Aposta precisa ser passado, para saber quando escolher apostar qual aposta será seguida
     */
    void menu(Aposta aposta) {
        int option = InOut.leInt(this.nome + "\nSeu saldo é:" + this.credito.saldo + "\nDepositar (0) \n Sacar (1) \n Apostar (2)\nInserir Jogo(3)\nSair (Qualquer Outra)");

        if (option == 0) {
            double valor_deposito = InOut.leDouble("Seu saldo é:" + this.credito.saldo + "\nValor do Depósito: ");
            this.depositar(valor_deposito);
            this.menu(aposta);
        } else if (option == 1) {
            double valor_saque = InOut.leDouble("Seu saldo é:" + this.credito.saldo + "\nValor do Saque: ");
            this.sacar(valor_saque);
            this.menu(aposta);
        } else if (option == 2) {
            int jogo_aposta = this.jogoAposta(aposta);
            this.aposta(jogo_aposta, aposta);
        } else if (option == 3) {
            aposta.addJogo();
            this.menu(aposta);
        } else {
            // Opção inválida, encerrar o programa ou tratar de outra forma, se necessário.
            System.out.println("Opção inválida. Encerrando o programa.");
        }
    }


    /**
     * Algoritimo de aposta da BET, recebe o Jogo da aposta e colota do usuário o time que será apsotado pela funcao timeAposta().
     * Depois pega o valor da aposta e gera aleatoriamente o ganhador e multiplica o peso do time pelo valor se o ganhador for o time escolhido
     * @param jogoAposta Index do jgoo escolhido
     * @param a Aposta do Jogo
     */
    void aposta(int jogoAposta, Aposta a){
        Jogo jogo = a.jogoList.get(jogoAposta);
        int time_aposta = jogo.timeAposta();
        boolean flag = true;
        double valor_aposta = 0;
        while (flag) {
            valor_aposta = InOut.leDouble("Saldo: "+this.credito.saldo +"\n" +  jogo.descritivo.get(0) + " (" + jogo.pesos.get(0) + ") "+ " X " + jogo.descritivo.get(1) + " (" + jogo.pesos.get(1) + ") \nInsira o valor de Aposta: ");
            if (valor_aposta > this.credito.saldo) {
                InOut.MsgDeAviso("Problema com Credito", "Voce nao tem saldo suficiente");
            } else {
                flag = false;
            }
        }

        Random random = new Random();
        jogo.ganhador = random.nextInt(2);
        if(jogo.ganhador == time_aposta){
            this.credito.saldo += valor_aposta * jogo.pesos.get(time_aposta);
            InOut.MsgDeInformacao("Vencedor", "Voce ganhou na sua aposta!!!!\n Seu saldo agora é: " + this.credito.saldo);
        }else{
            this.credito.saldo -= valor_aposta;
            InOut.MsgDeInformacao("Perdedor", "Voce perdeu na sua aposta!!!!\n Seu saldo agora é: " + this.credito.saldo);
        }


    }







}