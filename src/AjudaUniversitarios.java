import java.util.*;

public class AjudaUniversitarios implements Ajuda {
    private Pergunta pergunta;

    public AjudaUniversitarios(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    @Override
    public void usarAjuda() {
        System.out.println("Consultando os universitários...");

        Map<Character, Integer> votos = new LinkedHashMap<>();
        Random rand = new Random();

        // Resposta correta recebe uma base maior
        int corretaBonus = rand.nextInt(41) + 40; // entre 40 e 80
        int restantes = 100 - corretaBonus;

        List<Character> erradas = new ArrayList<>();
        for (char letra = 'a'; letra <= 'd'; letra++) {
            if (letra != pergunta.getRespostaCorretaLetra()) {
                erradas.add(letra);
            }
        }

        Collections.shuffle(erradas);

        int r1 = rand.nextInt(restantes + 1);
        int r2 = rand.nextInt(restantes - r1 + 1);
        int r3 = restantes - r1 - r2;

        votos.put(pergunta.getRespostaCorretaLetra(), corretaBonus);
        votos.put(erradas.get(0), r1);
        votos.put(erradas.get(1), r2);
        votos.put(erradas.get(2), r3);

        for (char letra = 'a'; letra <= 'd'; letra++) {
            System.out.println(letra + ") " + votos.get(letra) + "%");
        }
    }
}
