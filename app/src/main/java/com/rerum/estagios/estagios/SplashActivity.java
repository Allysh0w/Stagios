package com.rerum.estagios.estagios;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class SplashActivity extends AppCompatActivity {

    TextView bemvindo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        bemvindo  = (TextView)findViewById(R.id.bemvindo);
        Typeface novafonte = Typeface.createFromAsset(getAssets(), "fonts/lubalingraph.ttf");
        bemvindo.setTypeface(novafonte);
        // Espera 3 segundos para sair do splash e entrar na atividade principal.
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashActivity.this,PrincipalActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
