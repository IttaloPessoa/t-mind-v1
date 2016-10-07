package br.com.ufpb.ittalopessoa.t_mind.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.ufpb.ittalopessoa.t_mind.R;
import br.com.ufpb.ittalopessoa.t_mind.activities.MainActivity;


public class FragmentExerciceObjetive extends FragmentExerciceBase {

    private LinearLayout mOption1;
    private TextView mTextOption1;

    private LinearLayout mOption2;
    private TextView mTextOption2;

    private LinearLayout mOption3;
    private TextView mTextOption3;

    private LinearLayout mOption4;
    private TextView mTextOption4;

    private Button mButton;

    private String textSelected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercice_objetive, container, false);

        //final Questao questao = application.getQuestao();

        application.incrementQuantExercicios();

        TextView mTitle = (TextView) view.findViewById(R.id.title_exer2);
        //mTitle.setText(questao.getTitle());

        TextView mSubtitle = (TextView) view.findViewById(R.id.subhead_exer2);
        //mSubtitle.setText(questao.getSubtitle());

        mTextOption1 = (TextView) view.findViewById(R.id.text_item1_exerc2);
        //mTextOption1.setText(questao.getAlternativas().get(0));
        mOption1 = (LinearLayout) view.findViewById(R.id.item1_exerc2);
        mOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOption1.setBackgroundResource(R.drawable.btn_dark_edge_red);
                mOption2.setBackgroundResource(R.drawable.raised_button_border_grey);
                mOption3.setBackgroundResource(R.drawable.raised_button_border_grey);
                mOption4.setBackgroundResource(R.drawable.raised_button_border_grey);
                textSelected = mTextOption1.getText().toString();
                mButton.setEnabled(true);
                mButton.setBackgroundResource(R.drawable.btn_dark_red);
            }
        });

        mTextOption2 = (TextView) view.findViewById(R.id.text_item2_exerc2);
        //mTextOption2.setText(questao.getAlternativas().get(1));
        mOption2 = (LinearLayout) view.findViewById(R.id.item2_exerc2);
        mOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOption2.setBackgroundResource(R.drawable.btn_dark_edge_red);
                mOption1.setBackgroundResource(R.drawable.raised_button_border_grey);
                mOption3.setBackgroundResource(R.drawable.raised_button_border_grey);
                mOption4.setBackgroundResource(R.drawable.raised_button_border_grey);
                textSelected = mTextOption2.getText().toString();
                mButton.setEnabled(true);
                mButton.setBackgroundResource(R.drawable.btn_dark_red);
            }
        });


        mTextOption3 = (TextView) view.findViewById(R.id.text_item3_exerc2);
        //mTextOption3.setText(questao.getAlternativas().get(2));
        mOption3 = (LinearLayout) view.findViewById(R.id.item3_exerc2);
        mOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOption3.setBackgroundResource(R.drawable.btn_dark_edge_red);
                mOption1.setBackgroundResource(R.drawable.raised_button_border_grey);
                mOption2.setBackgroundResource(R.drawable.raised_button_border_grey);
                mOption4.setBackgroundResource(R.drawable.raised_button_border_grey);
                textSelected = mTextOption3.getText().toString();
                mButton.setEnabled(true);
                mButton.setBackgroundResource(R.drawable.btn_dark_red);
            }
        });

        mTextOption4 = (TextView) view.findViewById(R.id.text_item4_exerc2);
        //mTextOption4.setText(questao.getAlternativas().get(3));
        mOption4 = (LinearLayout) view.findViewById(R.id.item4_exerc2);
        mOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOption4.setBackgroundResource(R.drawable.btn_dark_edge_red);
                mOption1.setBackgroundResource(R.drawable.raised_button_border_grey);
                mOption3.setBackgroundResource(R.drawable.raised_button_border_grey);
                mOption2.setBackgroundResource(R.drawable.raised_button_border_grey);
                textSelected = mTextOption4.getText().toString();
                mButton.setEnabled(true);
                mButton.setBackgroundResource(R.drawable.btn_dark_red);
            }
        });

        mButton = (Button) view.findViewById(R.id.btn_exerc_objetive);
        mButton.setEnabled(false);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textSelected.contains("8")){
                    pontuarSeForCorreto();
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Parabéns")
                            .setMessage("Resposta correta!")
                            .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .create().show();
                }else{
                    pontuarSeForIncorreto();
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Você errou")
                            .setMessage("Resposta incorreta!\n\nSolução correta: " + "c = 8")
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
}
