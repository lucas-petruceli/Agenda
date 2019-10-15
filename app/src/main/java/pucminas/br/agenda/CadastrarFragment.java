package pucminas.br.agenda;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadastrarFragment extends Fragment {

    private EditText nameContato;
    private EditText emailContato;
    private EditText nascimentoContato;
    private EditText enderecoContato;
    private EditText telefoneContato;

    public CadastrarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cadastrar, container, false);

        nameContato = rootView.findViewById(R.id.name);
        emailContato = rootView.findViewById(R.id.email);
        enderecoContato = rootView.findViewById(R.id.adress);
        nascimentoContato = rootView.findViewById(R.id.birthday);
        telefoneContato = rootView.findViewById(R.id.phone);
        Button btnCadastrar = rootView.findViewById(R.id.btn_cadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = nameContato.getText().toString();
                String email = emailContato.getText().toString();
                String nascimento = nascimentoContato.getText().toString();
                String endereco = enderecoContato.getText().toString();
                String telefone = telefoneContato.getText().toString();
                if (!nome.equals("") && !email.equals("") && !nascimento.equals("") && !endereco.equals("") && !telefone.equals("")) {
                    Contato contato = new Contato(nome, email, nascimento, telefone, endereco);
                    cadastrarContato(contato);
                    Toast.makeText(getContext().getApplicationContext(), "CADASTRO REALIZADO COM SUCESSO", Toast.LENGTH_SHORT).show();
                    nameContato.setText("");
                    emailContato.setText("");
                    nascimentoContato.setText("");
                    telefoneContato.setText("");
                    enderecoContato.setText("");
                }
            }
        });

        return rootView;
    }

    private void cadastrarContato(Contato contato) {

        try {
            SQLiteDatabase dataBase = getContext().getApplicationContext().openOrCreateDatabase("bancoAgenda", Context.MODE_PRIVATE, null);
            dataBase.execSQL(" CREATE TABLE IF NOT EXISTS Contato(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, email VARCHAR, nascimento VARCHAR, telefone VARCHAR, endereco VARCHAR) ");
            String insert = "INSERT INTO Contato "
                    + "(nome, email, nascimento, telefone, endereco) VALUES "
                    + "('" + contato.getNome()
                    + "','" + contato.getEmail()
                    + "','" + contato.getNascimento()
                    + "','" + contato.getTelefone()
                    + "','" + contato.getEndereco() + "')";

            dataBase.execSQL(insert);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
