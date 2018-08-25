package com.rerum.estagios.estagios;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Javac on 09/04/2016.
 */


public class CidadeFragment extends DialogFragment {


    Context ContextoDaActivity;
    EditText cidadeFragment;
    TextView tituloCidadeFragmento;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        // Infla o layout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View customView = inflater.inflate(R.layout.fragment_cidade, null);
        Typeface novafonte = Typeface.createFromAsset(getActivity().getAssets(), "fonts/lubalingraph.ttf");
        cidadeFragment  = (EditText) customView.findViewById(R.id.cidadeFragment);
        tituloCidadeFragmento = (TextView) customView.findViewById(R.id.tituloCidadeFragment);
        tituloCidadeFragmento.setTypeface(novafonte);

        // infla e seta o layout para o dialog
        builder.setView(customView)
                // Adiciona as ações do botoes;
                .setPositiveButton("Pesquisar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ContextoDaActivity = getActivity(); // Grava o contexto atual da atividade
                        String cidade = cidadeFragment.getText().toString();
                        if (!cidade.toString().equals("")) {
                            if (cidade.trim().length() > 0) {
                                cidade = cidade.replace(" ","%20");
                                Intent in = new Intent(ContextoDaActivity, PesquisaActivity.class);
                                 String area = ((PrincipalActivity)getActivity()).primeiroParametro;
                                Bundle bundle = new Bundle();
                                bundle.putString("linkPesquisaParametroCompleto", area + "&cidade=" + cidade);
                                in.putExtras(bundle);
                                startActivity(in);
                            }else{
                                Toast.makeText(ContextoDaActivity,"Você precisa informar a cidade",Toast.LENGTH_SHORT).show();
                            }
                            // se for ok
                        }else{
                            Toast.makeText(ContextoDaActivity,"Você precisa informar a cidade",Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        return builder.create();



    }


}
