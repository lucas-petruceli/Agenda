package pucminas.br.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListarAdapter extends RecyclerView.Adapter<ListarAdapter.MyViewHolder> {

    private Context context;
    private List<Contato> contatoList;

    public ListarAdapter(Context mContext, List<Contato> contatoList) {
        this.context = mContext;
        this.contatoList = contatoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_pessoa_agenda, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contato contato = contatoList.get(position);
        holder.idContato.setText(String.valueOf(contato.getID()));
        holder.nome.setText(contato.getNome());
        holder.nascimento.setText(contato.getNascimento());
        holder.telefone.setText(contato.getTelefone());
        holder.email.setText(contato.getEmail());
        holder.endereco.setText(contato.getEndereco());
    }

    @Override
    public int getItemCount() {
        return contatoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView idContato, nome, email, nascimento, telefone, endereco;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idContato = itemView.findViewById(R.id.idContato);
            nome = itemView.findViewById(R.id.nomeContato);
            email = itemView.findViewById(R.id.emailContato);
            nascimento = itemView.findViewById(R.id.nascimento);
            telefone = itemView.findViewById(R.id.telefoneContato);
            endereco = itemView.findViewById(R.id.enderecoContato);

        }
    }
}
