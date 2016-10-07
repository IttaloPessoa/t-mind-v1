package br.com.ufpb.ittalopessoa.t_mind.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.ufpb.ittalopessoa.t_mind.R;


public class FragmentExerciceAdvanced extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_advanced, container, false);

        Button button = (Button) view.findViewById(R.id.start_advanced);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
