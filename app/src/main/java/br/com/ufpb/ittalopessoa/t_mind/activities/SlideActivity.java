package br.com.ufpb.ittalopessoa.t_mind.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.viewpagerindicator.CirclePageIndicator;

import br.com.ufpb.ittalopessoa.t_mind.R;
import br.com.ufpb.ittalopessoa.t_mind.fragments.Slide01;
import br.com.ufpb.ittalopessoa.t_mind.fragments.Slide02;
import br.com.ufpb.ittalopessoa.t_mind.fragments.Slide03;

public class SlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        CirclePageIndicator pageIndicator = (CirclePageIndicator) findViewById(R.id.pageIndicator);

        if(viewPager != null){
            viewPager.setAdapter(pagerAdapter);
            viewPager.setPageTransformer(false, new FadePageTransformer());

            if(pageIndicator != null){
                pageIndicator.setViewPager(viewPager);
                pageIndicator.setCurrentItem(viewPager.getCurrentItem());
            }
        }

        View button1 = findViewById(R.id.btn_start);
        button1.setOnClickListener(new OnClickButton(LoginRegisterActivity.class, 0));

        View button2 = findViewById(R.id.btn_sing_in);
        button2.setOnClickListener(new OnClickButton(LoginRegisterActivity.class, 1));

    }

    private class OnClickButton implements View.OnClickListener {

        private Class aClass;
        private int type;

        public OnClickButton(Class aClass, int type) {
            this.aClass = aClass;
            this.type = type;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SlideActivity.this, aClass);
            intent.putExtra("type", type);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeCustomAnimation(SlideActivity.this, R.anim.anim_fade_in, R.anim.anim_fade_out);
            ActivityCompat.startActivity(SlideActivity.this, intent, optionsCompat.toBundle());
            finish();
        }
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final String[] titles = {"1","2","3"};

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            if(position == 0){
                fragment = new Slide01();
            }else if(position == 1){
                fragment = new Slide02();
            }else if(position == 2){
                fragment = new Slide03();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }

    private class FadePageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View view, float position) {
            view.setTranslationX(view.getWidth() * -position);

            if(position <= -1.0F || position >= 1.0F) {
                view.setAlpha(0.0F);
            } else if( position == 0.0F ) {
                view.setAlpha(1.0F);
            } else {
                // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                view.setAlpha(1.0F - Math.abs(position));
            }
        }
    }
}
