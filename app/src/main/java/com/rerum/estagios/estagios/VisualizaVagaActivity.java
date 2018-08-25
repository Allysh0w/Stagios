package com.rerum.estagios.estagios;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

public class VisualizaVagaActivity extends AppCompatActivity {



    TextView descricaoVaga,titulo_descricao,titulo_contato;
    TextView periodoLetivoVaga,titulo_periodo;
    TextView requisitosVaga, titulo_requisito;
    TextView valorPropostoVaga,titulo_valor;
    Button contatoEmailVaga;
    Button contatoTelefoneVaga;
    Button contatoSite;
    TextView tituloAnuncioVaga;
    TextView cargoVaga;
    ImageView imagemLogoVaga;
    //InterstitialAd mInterstitialAd;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza_vaga);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        InicializaInformacoes();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.barra)));
        MudaFontTitulo();
        //InicializaInterstitialBanner(); //Banner interstitial desativado

    }


   /* private void InicializaInterstitialBanner(){

        /*new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

            }
        });
        */

        /*mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_banner));

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });


    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }*/


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



    private void InicializaInformacoes(){

        descricaoVaga       = (TextView)findViewById(R.id.info_descricao);
        cargoVaga       = (TextView)findViewById(R.id.info_cargo);
        periodoLetivoVaga   = (TextView)findViewById(R.id.info_periodoMinimo);
        requisitosVaga      = (TextView)findViewById(R.id.info_requisitos);
        valorPropostoVaga   = (TextView)findViewById(R.id.info_valorProposto);
        contatoEmailVaga    = (Button)findViewById(R.id.info_contatoEmail);
        contatoTelefoneVaga = (Button)findViewById(R.id.info_contatoTelefone);
        contatoSite         = (Button)findViewById(R.id.info_contatoSite);
        tituloAnuncioVaga   = (TextView)findViewById(R.id.nome_empresa_vaga);
        imagemLogoVaga      = (ImageView)findViewById(R.id.logoVisualizaVaga);
        titulo_descricao   = (TextView)findViewById(R.id.titulo_descricao);
        titulo_contato     = (TextView)findViewById(R.id.titulo_contato);
        titulo_periodo     = (TextView)findViewById(R.id.titulo_periodo);
        titulo_requisito   = (TextView)findViewById(R.id.titulo_requisito);
        titulo_valor       = (TextView)findViewById(R.id.titulo_valor);

        final Bundle extras = getIntent().getExtras();
        cargoVaga.setText(extras.getString("cargoVaga"));
        url = extras.getString("siteVaga");
        descricaoVaga.setText(extras.getString("descricaoVaga"));
        requisitosVaga.setText(extras.getString("requisitoVaga"));
        periodoLetivoVaga.setText(extras.getString("periodoLetivoVaga"));
        valorPropostoVaga.setText(extras.getString("salarioVaga"));
        contatoTelefoneVaga.setText("Ligar para " + extras.getString("nomeEmpresaNoTelefone"));
        contatoEmailVaga.setText("Enviar seu currículo por email.");
        tituloAnuncioVaga.setText(extras.getString("nomeEmpresaVaga"));
        Picasso.with(this).load(extras.getString("imagemVaga")).into(imagemLogoVaga);


        Typeface novafonte = Typeface.createFromAsset(getAssets(), "fonts/lubalingraph.ttf");
        descricaoVaga.setTypeface(novafonte);
        requisitosVaga.setTypeface(novafonte);
        periodoLetivoVaga.setTypeface(novafonte);
        valorPropostoVaga.setTypeface(novafonte);
        contatoEmailVaga.setTypeface(novafonte);
        contatoTelefoneVaga.setTypeface(novafonte);
        contatoSite.setTypeface(novafonte);
        tituloAnuncioVaga.setTypeface(novafonte);
        periodoLetivoVaga.setTypeface(novafonte);
        titulo_contato.setTypeface(novafonte);
        titulo_valor.setTypeface(novafonte);
        tituloAnuncioVaga.setTypeface(novafonte);
        titulo_descricao.setTypeface(novafonte);
        titulo_requisito.setTypeface(novafonte);
        titulo_periodo.setTypeface(novafonte);
        titulo_contato.setTypeface(novafonte);









        contatoTelefoneVaga.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String numero_telefone = extras.getString("telefoneVaga");

                //Chama o telefone e disca o número
                Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:".concat(numero_telefone)));
                try {
                    startActivity(in);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "Não foi encontrado aplicação para efetuar chamadas nesse dispositivo.", Toast.LENGTH_SHORT).show();
                }

            }

        });


        contatoEmailVaga.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String emailEmpresa = extras.getString("emailVaga");


                try {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:".concat(emailEmpresa))); // only email apps should handle this
                    intent.putExtra(Intent.EXTRA_EMAIL, emailEmpresa);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Envio de currículo");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Não existe aplicação de email instalado em seu smartphone", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Não foi possível acessar o email.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        contatoSite.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Não foi possível acessar o site.", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }


}
