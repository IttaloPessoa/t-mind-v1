package br.com.ufpb.ittalopessoa.t_mind.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.ufpb.ittalopessoa.t_mind.R;
import br.com.ufpb.ittalopessoa.t_mind.activities.MainActivity;
import br.com.ufpb.ittalopessoa.t_mind.model.Usuario;

public class FragmentExerciceOpen extends FragmentExerciceBase {

    private String textSelected = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercice_open, container, false);

        //final Questao questao = application.getQuestao();

        application.incrementQuantExercicios();

        TextView mTitle = (TextView) view.findViewById(R.id.title_exer3);
        //mTitle.setText(questao.getTitle());

        TextView mSubtitle = (TextView) view.findViewById(R.id.subhead_exer3);
//        mSubtitle.setText(questao.getSubtitle());

        ((TextInputLayout) view.findViewById(R.id.inputlayout)).setTypeface(createRobotoRegular());

        final EditText mEditText = (EditText) view.findViewById(R.id.entrada_exerc3);
        mEditText.setTypeface(createRobotoRegular());

        Button mButton = (Button) view.findViewById(R.id.btn_exerc2);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textSelected = mEditText.getText().toString();
                if(textSelected.equalsIgnoreCase("43")){
                    pontuarSeForCorreto();
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Parabéns")
                            .setMessage("Resposta correta!")
                            .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Usuario usuario = application.getUsuarioActived();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .create().show();
                }else{
                    pontuarSeForIncorreto();
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Você errou")
                            .setMessage("Resposta incorreta!\n\nSolução correta: 43")
                            .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .create().show();
                }
            }
        });

        // Atualiza a barra de progresso de acordo com a qauntidade de questões realizadas
        View viewMenu = LayoutInflater.from(getActivity()).inflate(R.layout.activity_exercice, null, false);

        ProgressBar progressBarMain = (ProgressBar) viewMenu.findViewById(R.id.progressBar_exercice);
        progressBarMain.setProgress(application.getQuantExercicios());

        return view;
    }

    private Typeface createRobotoRegular() {
        return Typeface.createFromAsset(getActivity().getAssets(), "fonts/roboto_regular.ttf");
    }
}
