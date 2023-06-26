package fr.cgi.todoapp.models;
import io.vertx.core.json.JsonObject;

public class Todo {
    private String id;
    private String name;
    private final Boolean isDone;

    public Todo(JsonObject todo) {
        this.id = todo.getString("id");
        this.name = todo.getString("name");
        this.isDone = todo.getBoolean("isDone", false);
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Boolean done() {
        return isDone;
    }

    public Todo setName(String name) {
        this.name = name;
        return this;
    }

    public Todo setId(String id) {
        this.id = id;
        return this;
    }

}
