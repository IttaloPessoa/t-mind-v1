package br.com.ufpb.ittalopessoa.t_mind.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Random;

import br.com.ufpb.ittalopessoa.t_mind.R;
import br.com.ufpb.ittalopessoa.t_mind.TMindApplication;
import br.com.ufpb.ittalopessoa.t_mind.fragments.FragmentExerciceObjetive;
import br.com.ufpb.ittalopessoa.t_mind.fragments.FragmentExerciceOpen;
import br.com.ufpb.ittalopessoa.t_mind.model.Questao;
import br.com.ufpb.ittalopessoa.t_mind.util.Constants;

public class ExerciceActivity extends AppCompatActivity implements View.OnClickListener{

    private  TMindApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);

        View view = findViewById(R.id.close_button_exercice);
        view.setOnClickListener(this);

        application = (TMindApplication) getApplicationContext();
//        application.setQuestao(getQuestaoAleatoria());

        String TAG = application.getNivelSelecionado();

        Fragment fragment = null;

        if(TAG.equalsIgnoreCase("facil")) {
            fragment = new FragmentExerciceObjetive();
        }else if(TAG.equalsIgnoreCase("medio")) {
            fragment = new FragmentExerciceOpen();
        }else if(TAG.equalsIgnoreCase("dificil")) {
            fragment = new FragmentExerciceOpen();
        }


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.exercicio_container, fragment).commit();

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar_exercice);
        progressBar.setMax(Constants.MAX_QUESTIONS.getValor());
    }

    @Override
    public void onClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.exercice_alertDialog_title)
                .setMessage(R.string.exercice_alertDialog_message)
                .setPositiveButton(R.string.exercice_alertDialog_positiveButton, null)
                .setNegativeButton(R.string.exercice_alertDialog_negativeButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        application.setPontuaçãoTempNivel(0);
                        Intent intent = new Intent(ExerciceActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .create().show();
    }

    private Questao getQuestaoAleatoria(){
        Random random = new Random();
        int idxMax = application.getQuestoesPorNivel().size() - 1;
        int idx = random.nextInt(idxMax);
        return application.getQuestoesPorNivel().get(idx);
    }
}
