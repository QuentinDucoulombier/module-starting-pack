package fr.cgi.todoapp.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

public interface MyScriptsService {

    /*Todo: verifier s'il faut utiliser une List ?*/
    Future<JsonArray> getScripts();

    //Future<Scripts> createScript(Scripts script);

}
