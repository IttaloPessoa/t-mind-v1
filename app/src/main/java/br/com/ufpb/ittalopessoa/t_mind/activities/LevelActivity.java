package br.com.ufpb.ittalopessoa.t_mind.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.ufpb.ittalopessoa.t_mind.R;
import br.com.ufpb.ittalopessoa.t_mind.TMindApplication;
import br.com.ufpb.ittalopessoa.t_mind.fragments.FragmentExerciceAdvanced;
import br.com.ufpb.ittalopessoa.t_mind.fragments.FragmentExerciceBasic;
import br.com.ufpb.ittalopessoa.t_mind.fragments.FragmentExerciceIntermediate;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        TMindApplication application = (TMindApplication) getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_level);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String TAG = application.getNivelSelecionado();

        Fragment fragment = null;

        if(TAG.equalsIgnoreCase("facil")) {
            fragment = new FragmentExerciceBasic();
        }else if(TAG.equalsIgnoreCase("medio")) {
            fragment = new FragmentExerciceIntermediate();
        }else if(TAG.equalsIgnoreCase("dificil")) {
            fragment = new FragmentExerciceAdvanced();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_level, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            Intent intent = new Intent(LevelActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
