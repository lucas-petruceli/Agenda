package pucminas.br.agenda;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ListarFragment extends Fragment {

    private static final int MODE_PRIVATE = 0;
    private RecyclerView recyclerView;
    private ListarAdapter listarAdapter;
    private Button btnAtualizar;

    public ListarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_listar, container, false);


        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        recyclerView = rootView.findViewById(R.id.rv_listarCadastrados);

        listarAdapter = new ListarAdapter(getContext().getApplicationContext(), recuperarContatos());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(listarAdapter);

        btnAtualizar = rootView.findViewById(R.id.btn_atualizar);
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarAdapter = new ListarAdapter(getContext().getApplicationContext(), recuperarContatos());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(listarAdapter);
            }
        });

        return rootView;
    }

    private List<Contato> recuperarContatos() {
        Contato contato;
        List<Contato> contatos = new ArrayList<Contato>();
        try {

            SQLiteDatabase database = getContext().getApplicationContext().openOrCreateDatabase("bancoAgenda", MODE_PRIVATE, null);

            //recuperar dados da tabela
            Cursor cursor = database.rawQuery("SELECT id, nome, email, nascimento, telefone, endereco FROM Contato", null);

            //recuperando o índice das colunas (o primeiro campo é 0, o segundo é 1)
            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceEmail = cursor.getColumnIndex("email");
            int indiceNascimento = cursor.getColumnIndex("nascimento");
            int indiceEndereco = cursor.getColumnIndex("endereco");
            int indiceTelefone = cursor.getColumnIndex("telefone");

            //o cursos se move do primeiro item ao último a medida em que a leitura ocorre
            cursor.moveToFirst();
            while (cursor != null) {
                contato = new Contato(cursor.getString(indiceNome), cursor.getString(indiceEmail), cursor.getString(indiceNascimento), cursor.getString(indiceTelefone), cursor.getString(indiceEndereco));
                contato.setID(Integer.parseInt(cursor.getString(indiceId)));
                contatos.add(contato);
                contato = null;
                cursor.moveToNext(); //move para o próximo registro
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contatos;
    }
}
