package br.com.ufpb.ittalopessoa.t_mind.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import br.com.ufpb.ittalopessoa.t_mind.R;
import br.com.ufpb.ittalopessoa.t_mind.TMindApplication;
import br.com.ufpb.ittalopessoa.t_mind.model.Usuario;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        hideNavigationBar();

        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(findViewById(R.id.image_splash));
        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(findViewById(R.id.image_app));

        new SplashAsync().execute((Void) null);
    }

    // Ocultar a statusBar e actionBar
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private class SplashAsync extends AsyncTask<Void, Void, Boolean> {

        private final int TIME = 1500;
        private TMindApplication application;
        private Usuario usuario;

        SplashAsync() {
            this.application = (TMindApplication) getApplicationContext();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                Thread.sleep(TIME);
                usuario = application.buscarUsuarioPor("status", "true");
                return usuario != null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean sucess) {

            Intent intent = null;

            if(sucess){
                intent = new Intent(SplashActivity.this, MainActivity.class);
            }else{
                intent = new Intent(SplashActivity.this, SlideActivity.class);
            }
            startActivity(intent);
            finish();
        }
    }
}
