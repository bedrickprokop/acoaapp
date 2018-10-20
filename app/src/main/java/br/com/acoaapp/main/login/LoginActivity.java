package br.com.acoaapp.main.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.acoaapp.R;
import br.com.acoaapp.main.NavDrawerActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCredencialsValid()) {
                    //TODO chamar serviço de autenticação
                    Intent intent = new Intent(LoginActivity.this, NavDrawerActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isCredencialsValid() {
        return true;
    }
}
