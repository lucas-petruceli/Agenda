package pucminas.br.agenda;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class AtualizarFragment extends Fragment {


    private EditText editNome;
    private EditText editEmail;
    private EditText editNascimento;
    private EditText editTelefone;
    private EditText editEndereco;
    private EditText buscarId;
    private Button btnBusar;
    private Button btnAtualizar;
    private Button btnApagar;

    public AtualizarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_atualizar, container, false);

        editNome = rootView.findViewById(R.id.name);
        editEmail = rootView.findViewById(R.id.email);
        editNascimento = rootView.findViewById(R.id.birthday);
        editTelefone = rootView.findViewById(R.id.phone);
        editEndereco = rootView.findViewById(R.id.adress);
        buscarId = rootView.findViewById(R.id.edtIdentificador);
        btnBusar = rootView.findViewById(R.id.btn_buscar);
        btnAtualizar = rootView.findViewById(R.id.btn_atualizar);
        btnApagar = rootView.findViewById(R.id.btn_deletar);

        btnBusar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!buscarId.getText().toString().equals("")) {
                    int id = Integer.parseInt(buscarId.getText().toString());
                    Contato contato = recuperaDados(id);
                    if (contato != null) {
                        editNome.setText(contato.getNome());
                        editEmail.setText(contato.getEmail());
                        editNascimento.setText(contato.getNascimento());
                        editTelefone.setText(contato.getTelefone());
                        editEndereco.setText(contato.getEndereco());
                    } else {
                        Toast.makeText(getContext().getApplicationContext(), "Contato não encontrado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!buscarId.getText().toString().equals("")) {

                    String nome = editNome.getText().toString();
                    String email = editEmail.getText().toString();
                    String nascimento = editNascimento.getText().toString();
                    String telefone = editTelefone.getText().toString();
                    String endereco = editEndereco.getText().toString();
                    int id = Integer.parseInt(buscarId.getText().toString());
                    Contato contato = new Contato(nome, email, nascimento, telefone, endereco);
                    contato.setID(id);

                    atulizarDados(contato);

                    Toast.makeText(getContext().getApplicationContext(), "Contato atualizado com sucesso", Toast.LENGTH_SHORT).show();

                    editNome.setText("");
                    editEmail.setText("");
                    editNascimento.setText("");
                    editTelefone.setText("");
                    editEndereco.setText("");
                }
            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!buscarId.getText().toString().equals("")) {
                    int id = Integer.parseInt(buscarId.getText().toString());
                    apagarDados(id);
                    Toast.makeText(getContext().getApplicationContext(), "Contato apagado com sucesso", Toast.LENGTH_SHORT).show();
                    buscarId.setText("");
                    editNome.setText("");
                    editEmail.setText("");
                    editNascimento.setText("");
                    editTelefone.setText("");
                    editEndereco.setText("");
                }
            }
        });
        return rootView;
    }

    private void apagarDados(int id) {
        try {
            SQLiteDatabase database = getContext().getApplicationContext().openOrCreateDatabase("bancoAgenda", Context.MODE_PRIVATE, null);
            String delete = "DELETE FROM Contato "
                    + "WHERE id = " + id;
            database.execSQL(delete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void atulizarDados(Contato contato) {
        try {
            SQLiteDatabase database = getContext().getApplicationContext().openOrCreateDatabase("bancoAgenda", Context.MODE_PRIVATE, null);
            String update = "UPDATE Contato " + "SET nome = '" + contato.getNome()
                    + "', " + "email = '" + contato.getEmail()
                    + "', " + "nascimento = '" + contato.getNascimento()
                    + "', " + "telefone = '" + contato.getTelefone()
                    + "', " + "endereco = '" + contato.getEndereco()
                    + "' " + "WHERE id =" + contato.getID();
            database.execSQL(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Contato recuperaDados(int id) {
        Contato contato = null;

        try {
            SQLiteDatabase database = getContext().getApplicationContext().openOrCreateDatabase("bancoAgenda", Context.MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT id, nome, email, nascimento, endereco, telefone FROM Contato WHERE id = " + String.valueOf(id), null);

            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceEmail = cursor.getColumnIndex("email");
            int indiceNascimento = cursor.getColumnIndex("nascimento");
            int indiceEndereco = cursor.getColumnIndex("endereco");
            int indiceTelefone = cursor.getColumnIndex("telefone");

            cursor.moveToFirst();

            while (cursor != null) {
                contato = new Contato(cursor.getString(indiceNome), cursor.getString(indiceEmail), cursor.getString(indiceNascimento), cursor.getString(indiceTelefone), cursor.getString(indiceEndereco));
                contato.setID(Integer.parseInt(cursor.getString(indiceId)));
                cursor.moveToNext(); //move para o próximo registro
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contato;
    }
}
