package br.com.ufpb.ittalopessoa.t_mind.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.ufpb.ittalopessoa.t_mind.R;
import br.com.ufpb.ittalopessoa.t_mind.TMindApplication;
import br.com.ufpb.ittalopessoa.t_mind.activities.MainActivity;
import br.com.ufpb.ittalopessoa.t_mind.model.Usuario;
import br.com.ufpb.ittalopessoa.t_mind.util.CircleTransform;

public class FragmentLogin extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {

    private LoginAsyncTask mAuth = null;

    private EditText mLogin;
    private EditText mPassword;

    private View mProgress;
    private View mLayout;

    private TMindApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (TMindApplication) getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ImageView imageLogin = (ImageView) view.findViewById(R.id.imageLogin);

        Picasso.with(getActivity()).load(R.drawable.ic_people_shadow).transform(new CircleTransform()).into(imageLogin);

        ((TextInputLayout) view.findViewById(R.id.textInputEmail)).setTypeface(createRobotoRegular());
        ((TextInputLayout) view.findViewById(R.id.textInputSenha)).setTypeface(createRobotoRegular());

        mLogin = (EditText) view.findViewById(R.id.email);
        mLogin.setTypeface(createRobotoRegular());

        mPassword = (EditText) view.findViewById(R.id.password);
        mPassword.setTypeface(createRobotoRegular());
        mPassword.setOnEditorActionListener(this);

        Button mButtonLogin = (Button) view.findViewById(R.id.email_sign_in_button);
        mButtonLogin.setTypeface(createRobotoMedium());
        mButtonLogin.setOnClickListener(this);

        mProgress = view.findViewById(R.id.progress_login);
        mLayout = view.findViewById(R.id.email_login_form);

        return view;
    }

    private Typeface createRobotoRegular() {
        return Typeface.createFromAsset(getActivity().getAssets(), "fonts/roboto_regular.ttf");
    }

    private Typeface createRobotoMedium() {
        return Typeface.createFromAsset(getActivity().getAssets(), "fonts/roboto_medium.ttf");
    }

    private void attemptLogin() {

        if (mAuth != null) {
            mAuth = null;
        }

        mLogin.setError(null);
        mPassword.setError(null);

        String email = mLogin.getText().toString();
        String senha = mPassword.getText().toString();

        View focusView = null;
        boolean cancel = false;

        if (TextUtils.isEmpty(email)) {
            mLogin.setError(getString(R.string.error_field_required));
            focusView = mLogin;
            cancel = true;
        }

        if (TextUtils.isEmpty(senha)) {
            mPassword.setError(getString(R.string.error_field_required));
            focusView = mPassword;
            cancel = true;
        } else if (!isValidPassword(senha)) {
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            mAuth = new LoginAsyncTask(email, senha);
            mAuth.execute((Void) null);
        }

    }

    private boolean isValidPassword(String password) {
        return password.length() >= 4 && password.length() <= 16;
    }

    @Override
    public void onClick(View view) {
        attemptLogin();
    }

    @Override
    public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
        if (id == R.id.login || id == EditorInfo.IME_NULL) {
            attemptLogin();
            return true;
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            mLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLayout.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {

            mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            mLayout.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private class LoginAsyncTask extends AsyncTask<Void, Void, Boolean> {

        private final int TIME = 1500;

        private String username;
        private String senha;
        private Usuario temp;

        LoginAsyncTask(String username, String senha) {
            this.username = username;
            this.senha = senha;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                Thread.sleep(TIME);
                //temp = application.buscarUsuarioPor("usuario", username);
                temp = application.getUsuarioTemporario();
                if(temp != null){
                    return temp.getSenha().equals(senha);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean sucess) {
            onCancelled();

            if(sucess){
                temp.setStatus(true);
                application.adicionarUsuario(temp);
                application.setUsuarioActived(temp);

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }else{
                if(temp == null){
                    mLogin.setError(getString(R.string.no_register_email));
                    mLogin.requestFocus();
                }else{
                    mPassword.setError(getString(R.string.error_incorrect_password));
                    mPassword.requestFocus();
                }
            }
        }

        @Override
        protected void onCancelled() {
            mAuth = null;
            showProgress(false);
        }
    }

}
