package br.com.sofie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.sofie.Model.Tarefa;
import br.com.sofie.R;
import br.com.sofie.View.NovaTarefaActivity;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder> {

    private List<Tarefa> listaTarefas;
    private TextView nomeTarefa;
    private TextView emailTarefa;
    private TextView descTarefa;
    private TextView idTarefa;
    private String paramNomeTarefa = "";
    private String paramEmailTarefa = "";
    private String paramDescTarefa = "";
    private String paramIDTarefa = "";

    public TarefaAdapter(List<Tarefa> listaTarefas) {
        this.listaTarefas = listaTarefas;
    }

    @NonNull
    @Override
    public TarefaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_lista_item_tarefa,viewGroup,false);
        nomeTarefa = view.findViewById(R.id.textViewTarefa);
        emailTarefa = view.findViewById(R.id.textViewEmail);
        descTarefa = view.findViewById(R.id.textViewDescr);
        idTarefa = view.findViewById(R.id.textViewID);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaAdapter.ViewHolder holder, final int position) {
        Tarefa tarefa = listaTarefas.get(position);
        holder.bind(tarefa);


        holder.tarefaTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    paramNomeTarefa = listaTarefas.get(position).getTitle().trim();
                } catch (NullPointerException erro) {
                    erro.getMessage();
                }
                try {
                    paramEmailTarefa = listaTarefas.get(position).getEmail().trim();
                } catch (NullPointerException erro) {
                    erro.getMessage();
                }
                try {
                    paramDescTarefa = listaTarefas.get(position).getDescription().trim();
                } catch (NullPointerException erro) {
                    erro.getMessage();
                }
                try {
                    paramIDTarefa = listaTarefas.get(position).getId().trim();
                } catch (NullPointerException erro) {
                    erro.getMessage();
                }
                Intent intent = new Intent(v.getContext(), NovaTarefaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("NOME",paramNomeTarefa);
                bundle.putString("EMAIL",paramEmailTarefa);
                bundle.putString("DESC",paramDescTarefa);
                bundle.putString("ID",paramIDTarefa);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaTarefas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tarefaTitulo;
        TextView emailTarefa;
        TextView descrTarefa;
        TextView tarefaID;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tarefaTitulo = itemView.findViewById(R.id.textViewTarefa);
            emailTarefa = itemView.findViewById(R.id.textViewEmail);
            descrTarefa = itemView.findViewById(R.id.textViewDescr);
            tarefaID = itemView.findViewById(R.id.textViewID);
        }
        public void bind(Tarefa tarefa){
            tarefaTitulo.setText(tarefa.getTitle());
            emailTarefa.setText(tarefa.getEmail());
            descrTarefa.setText(tarefa.getDescription());
            tarefaID.setText(tarefa.getId());
        }

    }
}
