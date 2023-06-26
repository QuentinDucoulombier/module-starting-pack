package fr.cgi.todoapp.service;

import fr.cgi.todoapp.models.Todo;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface MyTodolistService{

    Future<JsonArray> get(List<Integer> ids);

    Future<JsonObject> create(Todo todo);

    Future<JsonObject> update(Integer id, Todo todo);

    Future<JsonObject> delete(Integer id);

}