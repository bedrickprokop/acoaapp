package br.com.acoaapp.main.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.acoaapp.R;
import br.com.acoaapp.main.NavDrawerActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btLogin = findViewById(R.id.bt_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidFields()) {

                    //TODO chamar serviço de autenticação
                    Intent intent = new Intent(LoginActivity.this, NavDrawerActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isValidFields() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty()) {
            etEmail.requestFocus();
            etEmail.setError(getString(R.string.act_login_ettext_requiredemail));
            return false;
        }
        if (password.isEmpty()) {
            etPassword.requestFocus();
            etPassword.setError(getString(R.string.act_login_ettext_requiredpassword));
            return false;
        }
        return true;
    }
}
