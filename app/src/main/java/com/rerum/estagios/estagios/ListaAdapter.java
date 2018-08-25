package com.rerum.estagios.estagios;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Javac on 31/03/2016.
 */
public class ListaAdapter extends SimpleAdapter {

    private Context mContext;
    public LayoutInflater inflater=null;
    public ListaAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mContext = context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_item, null);
        Typeface listaFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/lubalingraph.ttf");

        HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
        TextView cargoLayout = (TextView)vi.findViewById(R.id.Cargo);
        String cargoVaga = (String) data.get("nome_cargo");
        cargoLayout.setTypeface(listaFont);
        cargoLayout.setText(cargoVaga);

        TextView empresaLayout = (TextView)vi.findViewById(R.id.nomeEmpresaCidade);
        String nomeDaEmpresa = (String) data.get("NomeCompletoEmpresaCidade");
        empresaLayout.setTypeface(listaFont);
        empresaLayout.setText(nomeDaEmpresa);

        TextView valorLayout = (TextView)vi.findViewById(R.id.salario);
        String valorVaga = (String) data.get("valor");
        valorLayout.setTypeface(listaFont);
        valorLayout.setText(valorVaga);

        TextView descricaoLayout = (TextView)vi.findViewById(R.id.descricao);
        String descricaoVaga = (String) data.get("descricao");
        descricaoLayout.setText(descricaoVaga);

        TextView telefoneLayout = (TextView)vi.findViewById(R.id.telefone);
        String telefoneVaga = (String) data.get("contato_telefone");
        telefoneLayout.setText(telefoneVaga);

        TextView emailLayout = (TextView)vi.findViewById(R.id.email);
        String emailVaga = (String) data.get("contato_email");
        emailLayout.setText(emailVaga);

        TextView periodoLayout = (TextView)vi.findViewById(R.id.periodoLetivo);
        String periodoVaga = (String) data.get("periodo_minimo");
        periodoLayout.setText(periodoVaga);

        TextView requisitosLayout = (TextView)vi.findViewById(R.id.requisitos);
        String requisitosVaga = (String) data.get("requisitos");
        requisitosLayout.setText(requisitosVaga);

        TextView cidadeLayout = (TextView)vi.findViewById(R.id.cidade);
        String cidadeVaga = (String) data.get("cidade");
        cidadeLayout.setText(cidadeVaga);

        TextView estadoLayout = (TextView)vi.findViewById(R.id.estado);
        String estadoVaga = (String) data.get("estado");
        estadoLayout.setText(estadoVaga);

        TextView siteLayout = (TextView)vi.findViewById(R.id.site);
        String siteVaga = (String) data.get("site");
        siteLayout.setText(siteVaga);

        TextView imagemLayout = (TextView)vi.findViewById(R.id.logo_caminho);
        String imagemVaga = (String) data.get("logo_imagem");
        imagemLayout.setText(imagemVaga);

        TextView NomeEmpresaNoTelefoneLayout = (TextView)vi.findViewById(R.id.nomeEmpresaNoTelefone);
        String NomeEmpresaNoTelefoneVaga = (String) data.get("nome_empresa");
        NomeEmpresaNoTelefoneLayout.setText(NomeEmpresaNoTelefoneVaga);

        ImageView image=(ImageView)vi.findViewById(R.id.logoempresa);
        String image_url = (String) data.get("logo_imagem");
        Picasso.with(mContext).load(image_url).into(image);
        return vi;
    }
}
