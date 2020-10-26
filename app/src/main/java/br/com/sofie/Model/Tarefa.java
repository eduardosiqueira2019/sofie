package br.com.sofie.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Tarefa {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("when")
    @Expose
    private String when;

    @SerializedName("title")
    @Expose
    private String title;

    public Tarefa() {
    }

    public Tarefa(String id, String description, String email, String when, String title) {
        this.id = id;
        this.description = description;
        this.email = email;
        this.when = when;
        this.title = title;
    }

    public Tarefa(String email, String title,String description,String id) {
        this.email = email;
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
