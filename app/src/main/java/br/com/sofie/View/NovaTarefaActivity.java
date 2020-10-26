package br.com.sofie.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import br.com.sofie.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NovaTarefaActivity extends AppCompatActivity {

    public String url;
    String nome = "";
    String email = "";
    String desc = "";
    String idTarefa = "";
    private EditText ed_email;
    private EditText ed_tituloTarefa;
    private EditText ed_descTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);

        ed_email = findViewById(R.id.editTextEmail);
        ed_tituloTarefa = findViewById(R.id.editTextTarefa);
        ed_descTarefa = findViewById(R.id.editTextDesc);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            nome = bundle.getString("NOME");
            email = bundle.getString("EMAIL");
            desc = bundle.getString("DESC");
            idTarefa = bundle.getString("ID");
            ed_email.setText(email);
            ed_tituloTarefa.setText(nome);
            ed_descTarefa.setText(desc);
        } else {
            ed_email.setText("");
            ed_tituloTarefa.setText("");
            ed_descTarefa.setText("");
            idTarefa = "";
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.excluir:
                //Excluir();
                //Toast.makeText(getApplicationContext(),"Excluído com sucesso",Toast.LENGTH_LONG).show();
                //documentação da API (DEL 05 - Remoção da tarefa) apresenta método de exclusão igual o de alteração (PUT). DELETE não encontrado.
                finish();
                return true;
            case R.id.gravar:
                if (VerificarCampos()) {
                    Alterar();
                    Toast.makeText(getApplicationContext(), "Gravado com sucesso", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Gravação Cancelada", Toast.LENGTH_LONG).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean VerificarCampos() {
        boolean camposOk = true;

        if (ed_email.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Email inválido", Toast.LENGTH_LONG).show();
            camposOk = false;
            return camposOk;
        }
        if (ed_tituloTarefa.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Nome da tarefa inválida", Toast.LENGTH_LONG).show();
            camposOk = false;
            return camposOk;
        }
        if (ed_descTarefa.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Descrição inválida", Toast.LENGTH_LONG).show();
            camposOk = false;
            return camposOk;
        }

        return camposOk;

    }

    private void Excluir() {
        String strBody = "";

        url = "https://9g1borgfz0.execute-api.sa-east-1.amazonaws.com/beta/task/" + idTarefa;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        strBody = "{\n  \"title\": \"" + ed_tituloTarefa.getText().toString().trim() + "\",\n  \"email\": \"" + ed_email.getText().toString().trim() +
                "\",\n  \"description\": \"" + ed_descTarefa.getText().toString().trim() + "\"\n}";
        RequestBody body = RequestBody.create(mediaType, strBody);
        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //final String myResponse = Objects.requireNonNull(response.body()).string();

                NovaTarefaActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        try {
//                            JSONObject json = new JSONObject(myResponse);
//
//                            String tarefa = json.getJSONObject("data").getString("title");
//                            String email = json.getJSONObject("data").getString("email");
//                            String descr = json.getJSONObject("data").getString("description");
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                });
            }
        });
    }

    private void Alterar() {
        String strBody = "";

        url = "https://9g1borgfz0.execute-api.sa-east-1.amazonaws.com/beta/task/" + idTarefa;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        strBody = "{\n  \"title\": \"" + ed_tituloTarefa.getText().toString().trim() + "\",\n  \"email\": \"" + ed_email.getText().toString().trim() +
                "\",\n  \"description\": \"" + ed_descTarefa.getText().toString().trim() + "\"\n}";
        RequestBody body = RequestBody.create(mediaType, strBody);
        Request request = new Request.Builder()
                .url(url)
                .method("PUT", body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //final String myResponse = Objects.requireNonNull(response.body()).string();

                NovaTarefaActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        try {
//                            JSONObject json = new JSONObject(myResponse);
//
//                            String tarefa = json.getJSONObject("data").getString("title");
//                            String email = json.getJSONObject("data").getString("email");
//                            String descr = json.getJSONObject("data").getString("description");
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                });
            }
        });
    }
}