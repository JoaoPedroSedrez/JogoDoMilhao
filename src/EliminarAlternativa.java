import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EliminarAlternativa implements Ajuda {
    private Pergunta pergunta;

    public EliminarAlternativa(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    @Override
    public void usarAjuda() {
        char correta = pergunta.getRespostaCorretaLetra();
        List<Character> alternativasErradas = new ArrayList<>();

        for (char letra = 'a'; letra <= 'd'; letra++) {
            if (letra != correta) {
                alternativasErradas.add(letra);
            }
        }

        Collections.shuffle(alternativasErradas);
        char primeira = alternativasErradas.get(0);
        char segunda = alternativasErradas.get(1);

        System.out.println("Eliminando duas alternativas: " + primeira + " e " + segunda);
        System.out.println("Alternativas restantes:");
        for (int i = 0; i < pergunta.alternativas.length; i++) {
            char letra = (char) ('a' + i);
            if (letra == correta || letra == alternativasErradas.get(2)) {
                System.out.println(pergunta.alternativas[i]);
            }
        }
    }
}
