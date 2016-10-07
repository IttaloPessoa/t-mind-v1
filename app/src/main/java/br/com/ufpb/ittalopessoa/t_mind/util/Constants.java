package br.com.ufpb.ittalopessoa.t_mind.util;

public enum Constants {

    MAX_QUESTIONS(1), //Maximo de questões por nível

    // Máximo de pontos por nível
    MAX_PONTS_BASIC(10),
    MAX_PONTS_INTER(15),
    MAX_PONTS_ADVANCED(20),

    // Máximo de penalização por nível
    ERROR_QUESTION_BASIC(7),
    ERROR_QUESTION_INTER(9),
    ERROR_QUESTION_ADVANCED(10),;


    private final int valor;

    Constants(int valorOpcao){
        valor = valorOpcao;
    }
    public int getValor(){
        return valor;
    }

}
