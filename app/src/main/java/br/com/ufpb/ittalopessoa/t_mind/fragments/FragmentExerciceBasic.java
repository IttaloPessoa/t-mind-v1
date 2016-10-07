package br.com.ufpb.ittalopessoa.t_mind.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.ufpb.ittalopessoa.t_mind.R;
import br.com.ufpb.ittalopessoa.t_mind.activities.ExerciceActivity;


public class FragmentExerciceBasic extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_basic, container, false);

        Button button = (Button) view.findViewById(R.id.start_basic);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), ExerciceActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
