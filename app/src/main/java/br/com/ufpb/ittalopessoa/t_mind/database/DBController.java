package br.com.ufpb.ittalopessoa.t_mind.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.ufpb.ittalopessoa.t_mind.model.Questao;
import br.com.ufpb.ittalopessoa.t_mind.model.Usuario;

public class DBController implements DBControllerMethods {

    private DBCore dbCore;
    private SQLiteDatabase db;

    public DBController(Context context) {
        this.dbCore = new DBCore(context);
    }

    // Usuário
    @Override
    public void adicionarUsuario(Usuario usuario) {
        db = dbCore.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", "" + usuario.getNome());
        values.put("sobrenome", "" + usuario.getSobrenome());
        values.put("username", "" + usuario.getUsername());
        values.put("senha", "" + usuario.getSenha());
        values.put("status", "" + String.valueOf(usuario.isStatus()));

        db.insert("usuario", null, values);

        db.close();
    }

    @Override
    public void atualizarUsuario(Usuario usuario){
        db = dbCore.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", "" + usuario.getNome());
        values.put("sobrenome", "" + usuario.getSobrenome());
        values.put("username", "" + usuario.getUsername());
        values.put("senha", "" + usuario.getSenha());
        values.put("status", "" + String.valueOf(usuario.isStatus()));

        db.update("usuario", values, "_id = ?", new String[]{"" + usuario.getId()});

        db.close();
    }

    @Override
    public Usuario buscarUsuarioPor(String coluna, String valor) {
        db = dbCore.getReadableDatabase();
        Usuario user = null;
        String[] coluns = new String[]{"_id", "nome", "sobrenome", "usuario", "senha", "status"};
        Cursor cursor = db.query("usuario", coluns, coluna + " = ?", new String[]{valor}, null, null, null, "1");
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            user = new Usuario();
            user.setId(cursor.getInt(0));
            user.setNome(cursor.getString(1));
            user.setSobrenome(cursor.getString(2));
            user.setUsername(cursor.getString(3));
            user.setSenha(cursor.getString(4));
            user.setStatus(Boolean.parseBoolean(cursor.getString(5)));
        }
        cursor.close();
        return user;
    }

    @Override
    public List<Usuario> getUsuarios() {
        db = dbCore.getReadableDatabase();
        List<Usuario> users = new ArrayList<>();
        Usuario user;
        String[] coluns = new String[]{"_id", "nome", "sobrenome", "usuario", "senha", "status"};
        Cursor cursor = db.query("usuario", coluns, null, null, null, null, "name ASC");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                user = new Usuario();
                user.setId(cursor.getInt(0));
                user.setNome(cursor.getString(1));
                user.setSobrenome(cursor.getString(2));
                user.setUsername(cursor.getString(3));
                user.setSenha(cursor.getString(4));
                user.setStatus(Boolean.parseBoolean(cursor.getString(5)));
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    public List<Questao> getQuestoesPorNivel(String nivel) {
        db = dbCore.getReadableDatabase();
        List<Questao> questoes = new ArrayList<>();
        String[] coluns = new String[]{"_id", "nivel", "tipo", "title", "subtitle", "resposta"};
        Cursor cursor = db.query("pergunta", coluns, "nivel = ?", new String[]{nivel}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Questao questao = new Questao();
            questao.setId(cursor.getInt(0));
            questao.setNivel(cursor.getString(1));
            questao.setTipo(cursor.getString(2));
            questao.setTitle(cursor.getString(3));
            questao.setSubtitle(cursor.getString(4));
            questao.setResposta(cursor.getString(5));
            questao.setAlternativas(getAlternativas(questao.getId()));
        }
        cursor.close();
        return questoes;
    }

    private List<String> getAlternativas(int id) {
        db = dbCore.getReadableDatabase();
        List<String> alternativas = new ArrayList<>();
        String[] coluns = new String[]{"_id", "idPergunta", "texto"};
        Cursor cursor = db.query("alternativas", coluns, "idPergunta = ?", new String[]{"" + id}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            alternativas.add(cursor.getString(2));
        }
        cursor.close();
        return alternativas;
    }

    public void onTerminateDataBase(){
        dbCore.close();
    }
}
