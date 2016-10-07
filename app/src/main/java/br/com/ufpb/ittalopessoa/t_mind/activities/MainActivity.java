package br.com.ufpb.ittalopessoa.t_mind.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import br.com.ufpb.ittalopessoa.t_mind.R;
import br.com.ufpb.ittalopessoa.t_mind.TMindApplication;
import br.com.ufpb.ittalopessoa.t_mind.model.Usuario;

public class MainActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener, AccountHeader.OnAccountHeaderListener {

    private TMindApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        application = (TMindApplication) getApplicationContext();
        Usuario usuario = application.getUsuarioActived();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        new DrawerBuilder().withActivity(this)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.START)
                .withToolbar(toolbar)
                //.withAccountHeader(accountHeader)
                .withItemAnimator(new DefaultItemAnimator())
                .withSelectedItem(-1)
                .addDrawerItems(

                        new PrimaryDrawerItem().withName("Manual")
                                .withIcon(R.drawable.ic_account)
                                .withTag("manual"),

                        new DividerDrawerItem(),

                        new PrimaryDrawerItem().withName("Ranking")
                                .withIcon(R.drawable.ic_trophy)
                                .withTag("ranking"),

                        new DividerDrawerItem(),

                        new PrimaryDrawerItem().withName("Sobre")
                                .withIcon(R.drawable.ic_help_circle)
                                .withTag("sobre")
                )
                .withOnDrawerItemClickListener(this)
                .build();

        View mEasyLevel = findViewById(R.id.button_basic);
        mEasyLevel.setOnClickListener(new OnClickMenu("facil"));

        View mMediumLevel = findViewById(R.id.button_medium);
        mMediumLevel.setOnClickListener(new OnClickMenu("medio"));

        View mHardLevel = findViewById(R.id.button_hard);
        mHardLevel.setOnClickListener(new OnClickMenu("dificil"));

    }

    private Typeface createRobotoMedium() {
        return Typeface.createFromAsset(getAssets(), "fonts/roboto_medium.ttf");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

        Intent intent;
        String TAG = drawerItem.getTag().toString();

        switch (TAG) {
            case "manual":
                Snackbar.make(view, "Em breve", Snackbar.LENGTH_LONG).show();
                //intent = new Intent(this, null);
                //startActivity(intent);
                return true;
            case "ranking":
                intent = new Intent(this, RankingActivity.class);
                startActivity(intent);
                return true;
            case "sobre":
                Snackbar.make(view, "Em breve", Snackbar.LENGTH_LONG).show();
                //intent = new Intent(this, null);
                //startActivity(intent);
                return true;
        }

        return true;
    }

    @Override
    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
        long id = profile.getIdentifier();

        if (id == 100) {
            return true;
        } else if (id == 101) {
            Snackbar.make(view, "Em breve", Snackbar.LENGTH_LONG).show();
            return true;
        }

        return false;
    }

    private class OnClickMenu implements View.OnClickListener {

        private String TAG;

        OnClickMenu(String TAG) {
            this.TAG = TAG;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, LevelActivity.class);
            application.setNivelSelecionado(TAG);
            startActivity(intent);
            finish();
        }
    }
}
