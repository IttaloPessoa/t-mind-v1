package br.com.ufpb.ittalopessoa.t_mind;

import android.app.Application;

import java.util.List;

import br.com.ufpb.ittalopessoa.t_mind.database.DBController;
import br.com.ufpb.ittalopessoa.t_mind.database.DBControllerMethods;
import br.com.ufpb.ittalopessoa.t_mind.model.Questao;
import br.com.ufpb.ittalopessoa.t_mind.model.Usuario;
import br.com.ufpb.ittalopessoa.t_mind.util.Constants;

public class TMindApplication extends Application implements DBControllerMethods{

    private DBController controller;
    private Usuario usuarioActived;

    private String nivelSelecionado = ""; // Nivel selecionado
    private int quantExercicios = 1; // Quantidade de exercicios atual no nível
    private int pontuaçãoTempNivel = 0; // Pontuação temporária do nível

    private Questao questao;
    private Usuario usuarioTemporario; // Usuário temporario enquanto nao tem banco

    @Override
    public void onCreate() {
        super.onCreate();
        controller = new DBController(getBaseContext());
        createUsuarioPadrao();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        controller.onTerminateDataBase();
    }

    private void createUsuarioPadrao(){
        usuarioTemporario = new Usuario();
        usuarioTemporario.setNome("Ittalo");
        usuarioTemporario.setSobrenome("Pessoa");
        usuarioTemporario.setSenha("12345");
        usuarioTemporario.setUsername("ittalopessoa");
        usuarioTemporario.setStatus(false);
    }

    public Usuario getUsuarioTemporario() {
        return usuarioTemporario;
    }

    public void setUsuarioTemporario(Usuario usuarioTemporario) {
        this.usuarioTemporario = usuarioTemporario;
    }

    // Relacionados a usuário
    @Override
    public void adicionarUsuario(Usuario usuario) {
        //controller.adicionarUsuario(usuario);
        usuarioActived = usuario;
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        //controller.atualizarUsuario(usuario);
        usuarioActived = usuario;
    }

    @Override
    public Usuario buscarUsuarioPor(String coluna, String valor) {
        return controller.buscarUsuarioPor(coluna, valor);
    }

    // Relacionados a questões
    @Override
    public List<Usuario> getUsuarios() {
        return controller.getUsuarios();
    }

    public List<Questao> getQuestoesPorNivel() {
        return controller.getQuestoesPorNivel(nivelSelecionado);
    }

    // Relacionados ao Controller do Banco de Dados
    public DBController getController() {
        return controller;
    }

    public void setController(DBController controller) {
        this.controller = controller;
    }

    // Relacionados a lógia em tempo de execução
    public Usuario getUsuarioActived() {
        return usuarioActived;
    }

    public void setUsuarioActived(Usuario usuarioActived) {
        this.usuarioActived = usuarioActived;
    }

    public String getNivelSelecionado() {
        return nivelSelecionado;
    }

    public void setNivelSelecionado(String nivelSelecionado) {
        this.nivelSelecionado = nivelSelecionado;
    }

    public void incrementQuantExercicios() { // Incrementa a quantidade de exercicios atual
        this.quantExercicios++;
    }

    public int getQuantExercicios() {
        return quantExercicios;
    }

    public void resetQuantMaximaExercicios() { //Reseta a quantidade de maxima
        this.quantExercicios = 0;
    }

    public boolean validarQuantExercicio(){ // Verifica se a quantidade de exercicios atual já ultrapassou a maxima permitida
        return this.quantExercicios == Constants.MAX_QUESTIONS.getValor();
    }

    public int getPontuaçãoTempNivel() {
        return pontuaçãoTempNivel;
    }

    public void incrementPontuacaoTemNivel(int valor) {
        this.pontuaçãoTempNivel += valor;
    }

    public void setPontuaçãoTempNivel(int pontuaçãoTempNivel) {
        this.pontuaçãoTempNivel = pontuaçãoTempNivel;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }
}
