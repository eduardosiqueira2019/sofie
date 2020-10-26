package br.com.sofie.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    @SerializedName("data")
    @Expose
    private List<Tarefa> data = null;

    /**
     * No args constructor for use in serialization
     */
    public Example() {
    }

    /**
     * @param data
     */
    public Example(List<Tarefa> data) {
        super();
        this.data = data;
    }

    public List<Tarefa> getData() {
        return data;
    }

    public void setData(List<Tarefa> data) {
        this.data = data;
    }
}
