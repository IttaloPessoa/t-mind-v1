package br.com.ufpb.ittalopessoa.t_mind.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import br.com.ufpb.ittalopessoa.t_mind.R;

public class Slide01 extends Fragment {

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide_01, container, false);

        textView = (TextView) view.findViewById(R.id.subtitle_slide1);
        YoYo.with(Techniques.Pulse).duration(1000).delay(500).playOn(textView);

        return view;
    }
}
