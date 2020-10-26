package br.com.sofie.Interface;

import java.util.List;

import br.com.sofie.Model.Tarefa;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("task")
    Call<List<Tarefa>> getPosts();
}
