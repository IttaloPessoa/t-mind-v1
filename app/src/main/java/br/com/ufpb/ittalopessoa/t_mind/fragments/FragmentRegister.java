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
import android.widget.TextView;

import br.com.ufpb.ittalopessoa.t_mind.R;
import br.com.ufpb.ittalopessoa.t_mind.TMindApplication;
import br.com.ufpb.ittalopessoa.t_mind.activities.MainActivity;
import br.com.ufpb.ittalopessoa.t_mind.model.Usuario;

public class FragmentRegister extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {

    private RegisterAsyncTask mAuth = null;

    private EditText mName;
    private EditText mLastname;
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        ((TextInputLayout) view.findViewById(R.id.textInputName)).setTypeface(createRobotoRegular());
        ((TextInputLayout) view.findViewById(R.id.textInputLastName)).setTypeface(createRobotoRegular());
        ((TextInputLayout) view.findViewById(R.id.textInputMail)).setTypeface(createRobotoRegular());
        ((TextInputLayout) view.findViewById(R.id.textInputSenha)).setTypeface(createRobotoRegular());

        mName = (EditText) view.findViewById(R.id.textName);;
        mLastname = (EditText) view.findViewById(R.id.textSobrenome);;
        mLogin = (EditText) view.findViewById(R.id.textMail);
        mLogin.setTypeface(createRobotoRegular());

        mPassword = (EditText) view.findViewById(R.id.senha_text);
        mPassword.setTypeface(createRobotoRegular());
        mPassword.setOnEditorActionListener(this);

        Button mButtonLogin = (Button) view.findViewById(R.id.email_register_button);
        mButtonLogin.setTypeface(createRobotoMedium());
        mButtonLogin.setOnClickListener(this);

        mProgress = view.findViewById(R.id.progress_register);
        mLayout = view.findViewById(R.id.register_form);

        return view;
    }

    private Typeface createRobotoRegular() {
        return Typeface.createFromAsset(getActivity().getAssets(), "fonts/roboto_regular.ttf");
    }

    private Typeface createRobotoMedium() {
        return Typeface.createFromAsset(getActivity().getAssets(), "fonts/roboto_medium.ttf");
    }

    private void attemptRegister() {

        if (mAuth != null) {
            mAuth = null;
        }

        mName.setError(null);
        mLastname.setError(null);
        mLogin.setError(null);
        mPassword.setError(null);

        String nome = mName.getText().toString();
        String sobrenome = mLastname.getText().toString();
        String email = mLogin.getText().toString();
        String senha = mPassword.getText().toString();

        View focusView = null;
        boolean cancel = false;

        if (TextUtils.isEmpty(nome)) {
            mName.setError(getString(R.string.error_field_required));
            focusView = mLogin;
            cancel = true;
        }

        if (TextUtils.isEmpty(sobrenome)) {
            mLastname.setError(getString(R.string.error_field_required));
            focusView = mLogin;
            cancel = true;
        }

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
            mAuth = new RegisterAsyncTask(nome, sobrenome, email, senha);
            mAuth.execute((Void) null);
        }

    }

    private boolean isValidPassword(String password) {
        return password.length() >= 4 && password.length() <= 16;
    }

    @Override
    public void onClick(View view) {
        attemptRegister();
    }

    @Override
    public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
        if (id == R.id.register || id == EditorInfo.IME_NULL) {
            attemptRegister();
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

    private class RegisterAsyncTask extends AsyncTask<Void, Void, Boolean> {

        private final int TIME = 1500;

        private String nome;
        private String sobrenome;
        private String username;
        private String senha;
        private Usuario temp;

        RegisterAsyncTask(String nome, String sobrenome, String username, String senha) {
            this.nome = nome;
            this.sobrenome = sobrenome;
            this.username = username;
            this.senha = senha;
            this.temp = null;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                Thread.sleep(TIME);
                //temp = application.buscarUsuarioPor("usuario", username);
                temp = application.getUsuarioTemporario();
                if(temp == null){
                    return true;
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
                temp = new Usuario();
                temp.setNome(nome);
                temp.setSobrenome(sobrenome);
                temp.setUsername(username);
                temp.setSenha(senha);
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
