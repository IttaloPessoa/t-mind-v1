package br.com.ufpb.ittalopessoa.t_mind.database;

import java.util.ArrayList;
import java.util.List;

public class DBCode {

    public List<String> createTables() {

        List<String> tables = new ArrayList<>();

        tables.add("usuario (_id integer primary key autoincrement," +
                "nome varchar, sobrenome varchar, usuario varchar(20) not null, senha varchar(16) not null," +
                "status varchar(5) not null)");

        tables.add("pergunta (_id integer primary key," +
                "nivel vachar, tipo varchar," +
                "title varchar, subtitle varchar," +
                "resposta varchar)");

        tables.add("alternativas (_id integer primary key autoincrement," +
                "idPergunta integer, texto varchar," +
                "FOREIGN KEY(idPergunta) REFERENCES pergunta(_id))");

        return tables;

    }

    public List<String> createInserts() {

        List<String> inserts = new ArrayList<>();

        //Questão 01 - Básico/Objetiva
        inserts.add("pergunta (_id, nivel, tipo, title, subtitle, resposta) " +
                "VALUES (1, 'basico', 'objetiva', 'Considerando que cada letra pode assumir um determinado valor. " +
                "Qual será o valor final de “c” no problema a seguir?'," +
                "'a = 3\n" +
                "b = 5\n" +
                "a = b - a\n" +
                "c = a + b','8')");

        inserts.add("alternativas (idPergunta, texto) VALUES (1,'c = 9')");
        inserts.add("alternativas (idPergunta, texto) VALUES (1,'c = 8')");
        inserts.add("alternativas (idPergunta, texto) VALUES (1,'c = 10')");
        inserts.add("alternativas (idPergunta, texto) VALUES (1,'c = 5')");

        //Questão 02 - Básico/Objetiva
        inserts.add("pergunta (_id, nivel, tipo, title, subtitle, resposta) " +
                "VALUES (2, 'basico', 'objetiva', 'Qual a saída para o problema abaixo?'," +
                "'a = 16\n" +
                "b = 50\n" +
                "c = b - a\n" +
                "d = c\n" +
                "a = b\n" +
                "c = c - (a + b)','-66')");

        inserts.add("alternativas (idPergunta, texto) VALUES (2,'c = 66')");
        inserts.add("alternativas (idPergunta, texto) VALUES (2,'c = -66')");
        inserts.add("alternativas (idPergunta, texto) VALUES (2,'c = 36')");
        inserts.add("alternativas (idPergunta, texto) VALUES (2,'c = -36')");

        //Questão 03 - Básico/Aberta
        inserts.add("pergunta (_id, nivel, tipo, title, subtitle, resposta) " +
                "VALUES (3, 'basico', 'aberta', 'Considerando que cada letra pode assumir um determinado valor." +
                "Qual o será o valor final de “d” no problema a seguir?'," +
                "'a = 1\n" +
                "b = 6\n" +
                "c = b + a\n" +
                "d = a + b * c','43')");

        //Questão 04 - Intermediario/Aberta
        inserts.add("pergunta (_id, nivel, tipo, title, subtitle, resposta) " +
                "VALUES (4, 'intermediario', 'aberta', 'Com base no exemplo a seguir, qual será o valor final armazenado em resultado?'," +
                "'numero1 = 0\n" +
                "numero2 = -1\n" +
                "se numero1 for maior que número 2, resultado é -1\n" +
                "caso contrário, resultado é 0','-1')");

        //Questão 05 Intermediário/Aberta
        inserts.add("pergunta (_id, nivel, tipo, title, subtitle, resposta) " +
                "VALUES (5, 'basico', 'objetiva', 'Ajude a Roberta informando qual será sua média bimestral em Geografia com a seguinte situação:'," +
                "'nota1 = 9,3\n" +
                "nota2 = 7.7\n" +
                "nota3 = 7\n" +
                "media = (nota1+nota2+nota3)/3','8')");

        //Questão 06 Intermediário/Objetiva
        inserts.add("pergunta (_id, nivel, tipo, title, subtitle, resposta) " +
                "VALUES (6, 'basico', 'objetiva', 'Com os números e o esquema a seguir, quais números podemos afirmar que são múltiplos de 3?'," +
                "'valores = 17, 3, 41, 4, 5, 62\n" +
                "múltiplo (qualquer valor/3,onde o resto da divisão é zero)','3')");

        inserts.add("alternativas (idPergunta, texto) VALUES (6,'c = todos')");
        inserts.add("alternativas (idPergunta, texto) VALUES (6,'c = 3')");
        inserts.add("alternativas (idPergunta, texto) VALUES (6,'c = 17')");
        inserts.add("alternativas (idPergunta, texto) VALUES (6,'c = 62')");

        // Questão 07 Díficil/Objetiva
        inserts.add("pergunta (_id, nivel, tipo, title, subtitle, resposta) " +
                "VALUES (7, 'basico', 'objetiva', 'Com base no exemplo a seguir, qual será o valor final armazenado em resultado?'," +
                "'numero1 = 2.3\n" +
                "numero2 = 3/2\n" +
                "numero3 = 4/2\n" +
                "se numero1 for maior que número2 e numéro 3, resultado é 2.3 \n" +
                "se numero1 for menor que número2 e maior numéro3, resultado é 2 \n" +
                "se numero2 for maior que número1 e menor que número3, resulado é 1 \n" +
                "se numero3 for maior que número1 e maior que número2, resulado é 0 \n" +
                "caso contrário, resultado é 0','0')");

        inserts.add("alternativas (idPergunta, texto) VALUES (7, 'Resultado = 0')");
        inserts.add("alternativas (idPergunta, texto) VALUES (7, 'Resultado = 1')");
        inserts.add("alternativas (idPergunta, texto) VALUES (7, 'Resultado = 2.3')");
        inserts.add("alternativas (idPergunta, texto) VALUES (7, 'Resultado = 2')");

        //Questão 08 Difícil/Objetiva
        inserts.add("pergunta (_id, nivel, tipo, title, subtitle, resposta) " +
                "VALUES (8, 'basico', 'objetiva', 'Considerando que cada letra pode assumir um determinado valor. Qual(s) linha apresentam erro?'," +
                "'linha 1: a = 3\n" +
                "linha 2: b = 1\n" +
                "linha 3: d = 3-a\n" +
                "linha 4: c = a/d\n" +
                "linha 5: e = c-a','Linha 4')");

        inserts.add("alternativas (idPergunta, texto) VALUES (8, 'Linha 1')");
        inserts.add("alternativas (idPergunta, texto) VALUES (8, 'Linha 3')");
        inserts.add("alternativas (idPergunta, texto) VALUES (8, 'Linha 4')");
        inserts.add("alternativas (idPergunta, texto) VALUES (8, 'Linha 5')");

        return inserts;
    }
}
