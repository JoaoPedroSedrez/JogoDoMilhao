abstract class Pergunta {

    protected String enunciado;
    protected String[] alternativas;
    protected char respostaCorreta;

    public Pergunta(String enunciado, String[] alternativas, char respostaCorreta) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.respostaCorreta = respostaCorreta;
    }

    public abstract boolean responder(char resposta);

    public char getRespostaCorretaLetra() {
        return respostaCorreta;
    }
}
