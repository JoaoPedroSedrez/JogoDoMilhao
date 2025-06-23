import java.util.Random;

public class AjudaEspecialista implements Ajuda {
    private Pergunta pergunta;

    public AjudaEspecialista(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    @Override
    public void usarAjuda() {
        System.out.println("Ligando para o especialista...");

        Random rand = new Random();
        char respostaSugerida;
        if (rand.nextInt(100) < 75) { // 75% de chance de ser a certa
            respostaSugerida = pergunta.getRespostaCorretaLetra();
        } else {
            do {
                respostaSugerida = (char) ('a' + rand.nextInt(4));
            } while (respostaSugerida == pergunta.getRespostaCorretaLetra());
        }

        System.out.println("\"Olha, não tenho certeza, mas acho que a resposta certa é a letra " + respostaSugerida + ".\"");
    }
}
