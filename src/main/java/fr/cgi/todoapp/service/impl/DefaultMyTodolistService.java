package fr.cgi.todoapp.service.impl;


import fr.cgi.todoapp.service.MyTodolistService;
import fr.cgi.todoapp.models.Todo;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.entcore.common.sql.Sql;
import org.entcore.common.sql.SqlResult;
import java.util.List;
public class DefaultMyTodolistService implements MyTodolistService {

    @Override
    public Future<JsonArray> get(List<Integer> ids) {
        Promise<JsonArray> promise = Promise.promise();

        JsonArray values = new JsonArray();
        String query = "SELECT * FROM todoapp.todo_item ";

        if (ids != null && !ids.isEmpty()) {
            query += " WHERE id IN " + Sql.listPrepared(ids);
            values.addAll(new JsonArray(ids));
        }

        Sql.getInstance().prepared(query, values, SqlResult.validResultHandler(event -> {
            if (event.isLeft()) {
                promise.fail(event.left().getValue());
            } else {
                promise.complete(event.right().getValue());
            }
        }));

        return promise.future();
    }

    @Override
    public Future<JsonObject> create(Todo todo) {
        Promise<JsonObject> promise = Promise.promise();

        String query = "INSERT INTO todoapp.todo_item " +
                "(is_done, label)" +
                "VALUES (?, ?) RETURNING id";
        JsonArray values = new JsonArray()
                .add(todo.done())
                .add(todo.name());
        Sql.getInstance().prepared(query, values, SqlResult.validUniqueResultHandler(event -> {
            if (event.isLeft()) {
                promise.fail(event.left().getValue());
            } else {
                promise.complete(event.right().getValue());
            }
        }));
        return promise.future();
    }

    @Override
    public Future<JsonObject> update(Integer id, Todo todo) {
        Promise<JsonObject> promise = Promise.promise();
        String query = "UPDATE todoapp.todo_item " +
                "SET is_done = ?, label = ? WHERE id = ? RETURNING id";
        JsonArray values = new JsonArray()
                .add(todo.done())
                .add(todo.name())
                .add(id);
        Sql.getInstance().prepared(query, values, SqlResult.validUniqueResultHandler(event -> {
            if (event.isLeft()) {
                promise.fail(event.left().getValue());
            } else {
                promise.complete(event.right().getValue());
            }
        }));
        return promise.future();
    }

    @Override
    public Future<JsonObject> delete(Integer id) {
        Promise<JsonObject> promise = Promise.promise();
        String query = "DELETE FROM todoapp.todo_item WHERE id = ? ";
        JsonArray values = new JsonArray().add(id);
        Sql.getInstance().prepared(query, values, SqlResult.validUniqueResultHandler(event -> {
            if (event.isLeft()) {
                promise.fail(event.left().getValue());
            } else {
                promise.complete(event.right().getValue());
            }
        }));
        return promise.future();
    }


}
