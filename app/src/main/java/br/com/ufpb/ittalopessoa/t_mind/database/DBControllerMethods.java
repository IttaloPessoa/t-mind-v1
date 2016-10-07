package br.com.ufpb.ittalopessoa.t_mind.database;

import java.util.List;

import br.com.ufpb.ittalopessoa.t_mind.model.Usuario;

public interface DBControllerMethods {

    // Usuario
    void adicionarUsuario(Usuario usuario);
    void atualizarUsuario(Usuario usuario);
    Usuario buscarUsuarioPor(String coluna, String valor);
    List<Usuario> getUsuarios();

}
