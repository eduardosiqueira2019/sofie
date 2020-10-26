package br.com.sofie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.sofie.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override public void run() {
                irParaTarefas();
            }
        }, 2000);
    }

    private void irParaTarefas() {
        Intent intent = new Intent(this,TarefasActivity.class);
        startActivity(intent);
    }
}