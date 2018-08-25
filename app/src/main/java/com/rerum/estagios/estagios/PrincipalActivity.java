package com.rerum.estagios.estagios;


import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class PrincipalActivity extends AppCompatActivity {
    String primeiroParametro;
    // Declaração de títulos e ícones para o navigation drawer
    //Os ícones e os títulos são salvos em um array
    private AdView mAdView;

    String TITLES[] = {"TI","Exatas","Humanas","Saúde","Sobre"};
    int ICONS[] = {R.drawable.computacao,R.drawable.exatas,R.drawable.humanas1,R.drawable.saude,R.drawable.info};

    //Criação das string e dos resources do header view

    String NAME = "EstágioS";
    String EMAIL = "Encontre seu estágio aqui...";
    //int PROFILE = R.drawable.eu;
    int PROFILE = 0; // Caso queira habilitar foto de perfil no menu Drawer.


    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                if (Drawer.isDrawerOpen(mRecyclerView)){ // Testa se o drawer ta ativo ativo

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Drawer.closeDrawers();
                        }
                    }, 0);
                }else{
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Drawer.openDrawer(Gravity.LEFT);
                        }
                    }, 0);

                }
                break;
        }
        return true;
    }

    ///

    Button botaopesquisa;
    EditText cargoOuEmpresa,cidade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.barra)));
        MudaFontTitulo();
        OuveClickDoBotao();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_drawer);
        InicializaCamposdeBuscas();

        Typeface face1= Typeface.createFromAsset(getAssets(), "fonts/lubalingraph.ttf");
        botaopesquisa.setTypeface(face1);


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE,getApplicationContext());       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture


        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
        ////////////

        final GestureDetector mGestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });


        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Drawer.closeDrawers();
                        }
                    }, 0);

                    //Toast.makeText(getApplicationContext(), "O item clicado foi: " + recyclerView.getChildPosition(child), Toast.LENGTH_SHORT).show();
                    if(recyclerView.getChildPosition(child) == 5){
                        Intent intent = new Intent(PrincipalActivity.this, SobreActivity.class);
                        startActivity(intent);
                    }else if(recyclerView.getChildPosition(child) == 1){
                        primeiroParametro = "http://estagios.esy.es/estagioS/giveresponsearea.php?areacargo=ti";
                        CidadeFragment cidade = new CidadeFragment();
                        cidade.show(getFragmentManager(), "Cidade");


                    }else if(recyclerView.getChildPosition(child) == 2){
                        primeiroParametro = "http://estagios.esy.es/estagioS/giveresponsearea.php?areacargo=exatas";
                        CidadeFragment cidade = new CidadeFragment();
                        cidade.show(getFragmentManager(), "Cidade");

                    }else if(recyclerView.getChildPosition(child) == 3){
                        primeiroParametro = "http://estagios.esy.es/estagioS/giveresponsearea.php?areacargo=humanas";
                        CidadeFragment cidade = new CidadeFragment();
                        cidade.show(getFragmentManager(), "Cidade");

                    }else if(recyclerView.getChildPosition(child) == 4){
                        primeiroParametro = "http://estagios.esy.es/estagioS/giveresponsearea.php?areacargo=saude";
                        CidadeFragment cidade = new CidadeFragment();
                        cidade.show(getFragmentManager(), "Cidade");

                    }

                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        ////////////
        mLayoutManager = new LinearLayoutManager(this);                 // Cria o layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);                 // Configura olayout manager
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer recebe a referencia do layout


    }

    private void InicializaCamposdeBuscas(){

        cargoOuEmpresa = (EditText)findViewById(R.id.cargo_ou_empresa_pesquisa);
        cidade         = (EditText)findViewById(R.id.cidade_pesquisa);
    }


    private void CarregaBannerEmThread(){
        // Propaganda AdMob
      /*  new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
               CarregaBanner();
            }
        });*/

    }

    private void CarregaBanner(){
        //Propaganda AdMob
        /*mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);*/
    }


    private void OuveClickDoBotao(){
        botaopesquisa = (Button)findViewById(R.id.botaopesquisavaga);

        botaopesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(cargoOuEmpresa.getText().toString().equals("") || cidade.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(), "Você deve preencher todos os campos",Toast.LENGTH_SHORT).show();

                }else {

                    Intent in = new Intent(getApplicationContext(), PesquisaActivity.class);
                    Bundle bundle = new Bundle();
                    String enviaParametro ="http://estagios.esy.es/estagioS/giveresponse.php?cargo="+cargoOuEmpresa.getText().toString() + "&cidade=" + cidade.getText().toString();
                    enviaParametro = enviaParametro.replace(" ","%20");
                    bundle.putString("linkPesquisaParametroCompleto",enviaParametro);
                    in.putExtras(bundle);
                    startActivity(in);

                }

            }
        });

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
        getSupportActionBar().setCustomView(titulo_activity);


    }


    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}





