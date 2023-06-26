package fr.cgi.todoapp.service.impl;


import fr.cgi.todoapp.service.MyScriptsService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import org.entcore.common.sql.Sql;
import org.entcore.common.sql.SqlResult;

public class DefaultMyScriptsService implements MyScriptsService {


    @Override
    public Future<JsonArray> getScripts() {
        Promise<JsonArray> promise = Promise.promise();


        JsonArray values = new JsonArray();
        String query = "SELECT * FROM todoapp.scripts ";


        Sql.getInstance().prepared(query, values, SqlResult.validResultHandler(event -> {
            if (event.isLeft()) {
                promise.fail(event.left().getValue());
            } else {
                promise.complete(event.right().getValue());
            }
        }));

        return promise.future();
    }
/*
    @Override
    public Future<Scripts> createScript(Scripts script) {
        Promise<Scripts> promise = Promise.promise();

        String query = "INSERT INTO todoapp.scripts " +
                "(filename, passed) " +
                "VALUES (?, NOW())";

        JsonArray params = new JsonArray()
                .add(script.filename());

        Sql.getInstance().prepared(query, params, SqlResult.validUniqueResultHandler(event -> {
            if (event.isLeft()) {
                promise.fail(event.left().getValue());
            } else {
                JsonObject result = event.right().getValue();
                Scripts createdScript = new Scripts(result);
                promise.complete(createdScript);
            }
        }));

        return promise.future();
    }

*/
}
