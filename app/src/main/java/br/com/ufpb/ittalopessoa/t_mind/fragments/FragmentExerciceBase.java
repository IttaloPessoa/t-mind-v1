package br.com.ufpb.ittalopessoa.t_mind.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.Random;

import br.com.ufpb.ittalopessoa.t_mind.R;
import br.com.ufpb.ittalopessoa.t_mind.TMindApplication;
import br.com.ufpb.ittalopessoa.t_mind.activities.MainActivity;
import br.com.ufpb.ittalopessoa.t_mind.model.Questao;
import br.com.ufpb.ittalopessoa.t_mind.util.Constants;

public class FragmentExerciceBase extends Fragment implements DialogInterface.OnClickListener{

    protected TMindApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (TMindApplication) getActivity().getApplicationContext();
    }

    public Questao getQuestaoAleatoria(){
        Random random = new Random();
        int idxMax = application.getQuestoesPorNivel().size() - 1;
        int idx = random.nextInt(idxMax);
        return application.getQuestoesPorNivel().get(idx);
    }

    public void pontuarSeForCorreto(){ // Se a resposta for correta ele fornece a pontuação completa
        String TAG = application.getNivelSelecionado();

        if(TAG.equalsIgnoreCase("facil")){
            application.incrementPontuacaoTemNivel(Constants.MAX_PONTS_BASIC.getValor());
        }else if(TAG.equalsIgnoreCase("medio")){
            application.incrementPontuacaoTemNivel(Constants.MAX_PONTS_INTER.getValor());
        }else if(TAG.equalsIgnoreCase("dificil")){
            application.incrementPontuacaoTemNivel(Constants.MAX_PONTS_ADVANCED.getValor());
        }
    }

    public void pontuarSeForIncorreto(){ // Se a resposta for incorreta ele fornece uma pontuação parcial
        String TAG = application.getNivelSelecionado();

        if(TAG.equalsIgnoreCase("facil")){ // 30% do valor total
            application.incrementPontuacaoTemNivel(Constants.ERROR_QUESTION_BASIC.getValor());
        }else if(TAG.equalsIgnoreCase("medio")){ // 40% do valor total
            application.incrementPontuacaoTemNivel(Constants.ERROR_QUESTION_INTER.getValor());
        }else if(TAG.equalsIgnoreCase("dificil")){ // 50% do valor total
            application.incrementPontuacaoTemNivel(Constants.ERROR_QUESTION_ADVANCED.getValor());
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(!application.validarQuantExercicio()){
            Questao questao = getQuestaoAleatoria();
            application.setQuestao(questao);

            Fragment fragment = null;

            if(application.getQuestao().getTipo().equalsIgnoreCase("objetiva")){
                fragment = new FragmentExerciceObjetive();
            }else if(application.getQuestao().getTipo().equalsIgnoreCase("aberta")){
                fragment = null;
            }

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_level, fragment).commit();
        }else{
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

}
