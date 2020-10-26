package br.com.sofie.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.sofie.Adapter.TarefaAdapter;
import br.com.sofie.Model.Tarefa;
import br.com.sofie.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TarefasActivity extends AppCompatActivity {

    public String url = "https://9g1borgfz0.execute-api.sa-east-1.amazonaws.com/beta/task";
    private RecyclerView recyclerViewTarefas;
    private TarefaAdapter adapter;
    private List<Tarefa> listaDeTarefas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NovaTarefaActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        LimpaLista();
        run();
    }

    private void LimpaLista() {
        listaDeTarefas.clear();
    }

    private void run() {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String myResponse = Objects.requireNonNull(response.body()).string();


                TarefasActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(myResponse);

                            JSONArray jArray = json.getJSONArray("data");
                            String tarefa;
                            String email;
                            String descr;
                            String id;
                            for (int i = 0; i < jArray.length(); i++) {
                                tarefa = jArray.getJSONObject(i).getString("title");
                                email = jArray.getJSONObject(i).getString("email");
                                descr = jArray.getJSONObject(i).getString("description");
                                id = jArray.getJSONObject(i).getString("_id");

                                listaDeTarefas.add(new Tarefa(email, tarefa, descr,id));

                            }
                            recyclerViewTarefas = findViewById(R.id.recyclerTarefas);
                            adapter = new TarefaAdapter(listaDeTarefas);
                            recyclerViewTarefas.setAdapter(adapter);
                            recyclerViewTarefas.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }


}