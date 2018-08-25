package com.rerum.estagios.estagios;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PesquisaActivity extends ListActivity {

    ListView list;
    private ProgressDialog pDialog;

    // URL para pegar os dados JSON
    //private static String url = "http://api.androidhive.info/contacts/";
    private  String url; //= "http://10.10.150.101/estagioS/giveresponse.php?cargo=Programador%20PHPaaaaaaaaaa&cidade=Teresina";

    private static final String TAG_VETOR                = "vagas";
    private static final String TAG_CARGO                = "nome_cargo";
    private static final String TAG_NOME_EMPRESA         = "nome_empresa";
    private static final String TAG_IMAGEM               = "logo_imagem";
    private static final String TAG_DESCRICAO_VAGA       = "descricao";
    private static final String TAG_PERIODO_LETIVO       = "periodo_minimo";
    private static final String TAG_REQUISITOS           = "requisitos";
    private static final String TAG_VALOR                = "valor";
    private static final String TAG_CIDADE               = "cidade";
    private static final String TAG_ESTADO               = "estado";
    private static final String TAG_CONTATO_TELEFONE     = "contato_telefone";
    private static final String TAG_CONTATO_EMAIL        = "contato_email";
    private static final String TAG_SITE                 = "site";



    // dados JSONArray
    JSONArray dados = null;

    // Hashmap para ListView
    ArrayList<HashMap<String, String>> listadeVagas;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.barra)));
        MudaFontTitulo();

        final Bundle extras = getIntent().getExtras();

        url =  extras.getString("linkPesquisaParametroCompleto");

        listadeVagas = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        // Escuta o click no item da lista
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Capturando os valores o objeto selecionado no ListItem
                String cargo = ((TextView) view.findViewById(R.id.Cargo)).getText().toString();
                String salario = ((TextView) view.findViewById(R.id.salario)).getText().toString();
                String descricao = ((TextView) view.findViewById(R.id.descricao)).getText().toString();
                String requisitos = ((TextView) view.findViewById(R.id.requisitos)).getText().toString();
                String telefone = ((TextView) view.findViewById(R.id.telefone)).getText().toString();
                String email = ((TextView) view.findViewById(R.id.email)).getText().toString();
                String periodoLetivo = ((TextView) view.findViewById(R.id.periodoLetivo)).getText().toString();
                String nomeEmpresa = ((TextView) view.findViewById(R.id.nomeEmpresaCidade)).getText().toString();
                String cidade = ((TextView) view.findViewById(R.id.cidade)).getText().toString();
                String estado = ((TextView) view.findViewById(R.id.estado)).getText().toString();
                String nomeEmpresaNoTelefone = ((TextView) view.findViewById(R.id.nomeEmpresaNoTelefone)).getText().toString();
                String site = ((TextView) view.findViewById(R.id.site)).getText().toString();
                String imagem_logo = ((TextView) view.findViewById(R.id.logo_caminho)).getText().toString();


                //Salva as variáveis em memoria e passa para outra activity
                // Inicia a Activity VisualizaVaga
                Intent in = new Intent(getApplicationContext(), VisualizaVagaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("cargoVaga", cargo);
                bundle.putString("salarioVaga", salario);
                bundle.putString("imagemVaga", imagem_logo);
                bundle.putString("descricaoVaga", descricao);
                bundle.putString("requisitoVaga", requisitos);
                bundle.putString("telefoneVaga", telefone);
                bundle.putString("emailVaga", email);
                bundle.putString("periodoLetivoVaga", periodoLetivo);
                bundle.putString("nomeEmpresaVaga", nomeEmpresa);
                bundle.putString("cidadeVaga", cidade);
                bundle.putString("estadoVaga", estado);
                bundle.putString("nomeEmpresaNoTelefone", nomeEmpresaNoTelefone);
                bundle.putString("siteVaga", site);
                in.putExtras(bundle);
                //Toast.makeText(getApplicationContext(), "aa " + nomeEmpresa, Toast.LENGTH_SHORT).show();
                startActivity(in);

            }
        });


        //Chamando a async task para capturar o json
        new PovoaLista().execute();
    }



    private void MudaFontTitulo(){

        // Colocar a fonte na action bar
        /*int titleId = getResources().getIdentifier("action_bar_title", "id",
                "android");
        TextView titulo_activity = (TextView) findViewById(titleId);
        titulo_activity.setTextColor(getResources().getColor(R.color.branco));
        Typeface face1= Typeface.createFromAsset(getAssets(), "fonts/lubalingraph.ttf");
        titulo_activity.setTypeface(face1);*/
        TextView titulo_activity = new TextView(getApplicationContext());
        //TextView titulo_activity = (TextView) findViewById(titleId);
        Typeface face1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/lubalingraph.ttf");
        titulo_activity.setTypeface(face1);
        titulo_activity.setText("     EstágioS");
        titulo_activity.setGravity(Gravity.CENTER);
        ActionBar ab = getActionBar();

        ab.setDisplayOptions(ab.DISPLAY_SHOW_CUSTOM);
        ab.setCustomView(titulo_activity);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

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

    /**
     * Async task class para capturar o json por chamada http
     * */
    private class PovoaLista extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Mostra o Progress Dialog
            pDialog = new ProgressDialog(PesquisaActivity.this);
            pDialog.setMessage("Pesquisando...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            try {
                ServiceHandler sh = new ServiceHandler();

                // Making a request to url and getting response
                String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

                //Log.d("Response: ", "> " + jsonStr);
                try {
                    if (jsonStr.equals("")) {
                        runOnUiThread(new Runnable() {

                            public void run() {

                                Toast.makeText(getApplicationContext(), "Nenhum dado foi encontrado.", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                } catch (Exception e) {

                    runOnUiThread(new Runnable() {

                        public void run() {

                            Toast.makeText(getApplicationContext(), "Não foi possível conectar ao serviço.", Toast.LENGTH_SHORT).show();

                        }
                    });
                }

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Capturando JSON Array
                        dados = jsonObj.getJSONArray(TAG_VETOR);
                        Log.d("TESTE", dados.toString());
                        // looping através de todos os dados do JSON
                        for (int i = 0; i < dados.length(); i++) {
                            JSONObject c = dados.getJSONObject(i);

                            String imagem_logo = "http://estagios.esy.es/estagioS/" + c.getString(TAG_IMAGEM);
                            String cargo = c.getString(TAG_CARGO);
                            String email = c.getString(TAG_CONTATO_EMAIL);
                            String telefone = c.getString(TAG_CONTATO_TELEFONE);
                            String requisitos = c.getString(TAG_REQUISITOS);
                            String valor = c.getString(TAG_VALOR);
                            String descricao = c.getString(TAG_DESCRICAO_VAGA);
                            String periodoLetivo = c.getString(TAG_PERIODO_LETIVO);
                            String estado = c.getString(TAG_ESTADO);
                            String cidade = c.getString(TAG_CIDADE);
                            String nomeEmpresa = c.getString(TAG_NOME_EMPRESA) + ", " + cidade + " - " + estado;
                            String nomeEmpresaNoTelefone = c.getString(TAG_NOME_EMPRESA);
                            String site = c.getString(TAG_SITE);

                            //Hashmap temporário para simples anuncio de vaga
                            HashMap<String, String> vagaDisponivel = new HashMap<String, String>();

                            //adicionando cada nó filho para o HashMap chave => valor | key => value
                            vagaDisponivel.put(TAG_IMAGEM, imagem_logo);
                            vagaDisponivel.put(TAG_CARGO, cargo);
                            vagaDisponivel.put("NomeCompletoEmpresaCidade", nomeEmpresa);
                            vagaDisponivel.put(TAG_CONTATO_EMAIL, email);
                            vagaDisponivel.put(TAG_CONTATO_TELEFONE, telefone);
                            vagaDisponivel.put(TAG_REQUISITOS, requisitos);
                            vagaDisponivel.put(TAG_VALOR, valor);
                            vagaDisponivel.put(TAG_DESCRICAO_VAGA, descricao);
                            vagaDisponivel.put(TAG_PERIODO_LETIVO, periodoLetivo);
                            vagaDisponivel.put(TAG_ESTADO, estado);
                            vagaDisponivel.put(TAG_CIDADE, cidade);
                            vagaDisponivel.put(TAG_SITE, site);
                            vagaDisponivel.put(TAG_NOME_EMPRESA, nomeEmpresaNoTelefone);

                            //adicionando as informações para a lista
                            listadeVagas.add(vagaDisponivel);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("A classe ServiceHandler", "Não pode capturar nenhuma dado da url");
                }


            }catch (Exception e){
                runOnUiThread(new Runnable() {

                    public void run() {

                        Toast.makeText(getApplicationContext(), "Nenhum dado encontrado", Toast.LENGTH_SHORT).show();

                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Atualiza os dados do JSON dentro do ListView
             * */

            ListAdapter adaptador = new ListaAdapter(getApplicationContext(),listadeVagas,R.layout.list_item,
                    new String[] {TAG_CARGO, TAG_NOME_EMPRESA      , TAG_VALOR   , TAG_IMAGEM      ,TAG_IMAGEM         ,TAG_SITE ,TAG_CIDADE   ,TAG_CONTATO_TELEFONE, TAG_CONTATO_EMAIL,TAG_DESCRICAO_VAGA,TAG_ESTADO   ,TAG_PERIODO_LETIVO  ,TAG_REQUISITOS },
                    new int[] {  R.id.Cargo, R.id.nomeEmpresaCidade, R.id.salario, R.id.logoempresa, R.id.logo_caminho ,R.id.site, R.id.cidade ,R.id.telefone       , R.id.email       , R.id.descricao   , R.id.estado , R.id.periodoLetivo , R.id.requisitos });

            //list=(ListView)findViewById(R.id.list_item);
            //list.setAdapter(adaptador);

            setListAdapter(adaptador);
            /*ListAdapter adapter = new SimpleAdapter(
                    PesquisaActivity.this, listadeVagas, R.layout.list_item,
                    new String[] { TAG_CARGO, TAG_NOME_EMPRESA , TAG_VALOR, TAG_IMAGEM },
                    new int[] { R.id.Cargo, R.id.nomeEmpresaCidade, R.id.salario, R.id.logoempresa });


            setListAdapter(adapter);*/

        }

    }

}