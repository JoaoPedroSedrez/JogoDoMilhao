import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JogoDoMilhao {
    private BancoDePerguntas banco;
    private boolean usouPular = false;
    private boolean usouEliminar = false;
    private boolean usouUniversitarios = false;
    private boolean usouEspecialista = false;
    private int perguntasRespondidas = 0;


    public JogoDoMilhao() {
        banco = new BancoDePerguntas();
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite seu nome: ");
        String nomeJogador = sc.nextLine();

        try {
            banco.carregarPerguntas("C:\\Users\\sedre\\IdeaProjects\\JogoDoMilhao\\src\\perguntas.txt");
            int pontos = 0;
            int dinheiro = 0;

            int[] valoresPremios = {
                    500, 1000, 2000, 3000, 5000,
                    10000, 15000, 20000, 30000, 50000,
                    100000, 150000, 300000, 500000, 1000000
            };

            for (int i = 0; i < banco.getPerguntas().size(); i++) {
                Pergunta p = banco.getPerguntas().get(i);
                System.out.println("\nPergunta " + (i + 1) + ": " + p.enunciado);
                for (String alt : p.alternativas) {
                    System.out.println(alt);
                }

                while (perguntasRespondidas <= 15) {
                    System.out.print("\nDigite a letra da resposta (a/b/c/d) ou:\n");
                    if (!usouPular) System.out.println("[1] Usar ajuda - Pular Pergunta");
                    if (!usouEliminar) System.out.println("[2] Usar ajuda - Eliminar duas alternativas");
                    if (!usouUniversitarios) System.out.println("[3] Usar ajuda - Ajuda dos Universitários");
                    if (!usouEspecialista) System.out.println("[4] Usar ajuda - Ligar para o Especialista");

                    System.out.print("Escolha: ");
                    String input = sc.nextLine().toLowerCase();

                    if (input.equals("1") && !usouPular) {
                        new PularPergunta().usarAjuda();
                        usouPular = true;
                        break; // ✅ volta para o for, próxima pergunta
                    } else if (input.equals("2") && !usouEliminar) {
                        new EliminarAlternativa(p).usarAjuda();
                        usouEliminar = true;
                    }
                    else if (input.equals("3") && !usouUniversitarios){
                        new AjudaUniversitarios(p).usarAjuda();
                        usouUniversitarios = true;
                    }
                    else if (input.equals("4") && !usouEspecialista){
                        new AjudaEspecialista(p).usarAjuda();
                        usouEspecialista = true;
                    }
                    else if (input.length() == 1 && "abcd".contains(input)) {
                        char resp = input.charAt(0);
                        if (p.responder(resp)) {
                            pontos++;
                            dinheiro = valoresPremios[pontos - 1];
                            System.out.println("Certa resposta!");
                            perguntasRespondidas += 1;
                        } else {
                            System.out.println("Resposta errada!");
                            System.out.println("A resposta correta era: " + p.getRespostaCorretaLetra());
                            i = banco.getPerguntas().size(); // força saída do for
                        }
                        break; // sai do while e vai pra próxima pergunta ou fim do jogo
                    } else {
                        System.out.println("Entrada inválida. Tente novamente.");
                    }
                }
            }


            System.out.println("\nJogo encerrado!");
            System.out.println("Você acertou " + pontos + " pergunta(s).");
            System.out.println("Prêmio acumulado: R$ " + dinheiro);

            salvarRanking(nomeJogador, pontos, dinheiro);
        } catch (IOException e) {
            System.out.println("Erro ao carregar perguntas: " + e.getMessage());
        }
    }

    public void salvarRanking(String nome, int pontos, int dinheiro) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("ranking.txt", true));
        bw.write("Jogador " + nome + " - Pontos: " + pontos + " - Dinheiro: R$ " + dinheiro + "\n");
        bw.close();
    }
}
