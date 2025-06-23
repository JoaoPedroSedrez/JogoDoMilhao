import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BancoDePerguntas {
    private List<Pergunta> perguntas = new ArrayList<>();

    public void carregarPerguntas(String arquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",", 6);
            String enunciado = partes[0];
            String[] alternativas = {partes[1], partes[2], partes[3], partes[4]};
            char resposta = partes[5].trim().charAt(0);
            perguntas.add(new PerguntaSimples(enunciado, alternativas, resposta));
        }
        br.close();
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;

    }
}
