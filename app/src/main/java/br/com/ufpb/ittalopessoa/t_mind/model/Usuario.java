package br.com.ufpb.ittalopessoa.t_mind.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int _id;
    private String nome;
    private String sobrenome;
    private String username;
    private String senha;

    private boolean status;

    public Usuario() {
        this.status = false;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String toStringName(){
        return this.nome + " " + this.sobrenome;
    }
}
