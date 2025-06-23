public class PerguntaSimples extends Pergunta{

    public PerguntaSimples(String enunciado, String[] alternativas, char respostaCorreta) {
        super(enunciado, alternativas, respostaCorreta);
    }

    @Override
    public boolean responder(char resposta) {
        return resposta == respostaCorreta;
    }
}
