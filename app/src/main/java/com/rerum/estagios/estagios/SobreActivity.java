package com.rerum.estagios.estagios;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SobreActivity extends AppCompatActivity {

    Button contatoBotao;
    TextView sobreMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.barra)));
        MudaFontTitulo();

    }

    private void MudaFontTitulo(){
        int titleId = getResources().getIdentifier("action_bar_title", "id",
                "android");
        TextView titulo_activity = new TextView(getApplicationContext());
        //TextView titulo_activity = (TextView) findViewById(titleId);
        Typeface face1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/lubalingraph.ttf");
        titulo_activity.setTypeface(face1);
        titulo_activity.setText("EstágioS");
        getSupportActionBar().setDisplayOptions(getSupportActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setCustomView(titulo_activity);
        OuveBotao();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }


    private void OuveBotao(){
        contatoBotao = (Button)findViewById(R.id.contatoSobre);
        sobreMensagem = (TextView)findViewById(R.id.sobreMensagem);
        Typeface face1= Typeface.createFromAsset(getAssets(), "fonts/lubalingraph.ttf");
        sobreMensagem.setTypeface(face1);
        contatoBotao.setTypeface(face1);


        contatoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:".concat("rerumstudio@gmail.com"))); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, "rerumstudio@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "FeedBack aplictivo EstágioS");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Não existe aplicação de email instalado em seu smartphone",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
