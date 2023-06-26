package fr.cgi.todoapp.controller;

import fr.cgi.todoapp.service.MyScriptsService;
import fr.cgi.todoapp.service.impl.DefaultMyScriptsService;
import fr.wseduc.rs.ApiDoc;
import fr.wseduc.rs.Get;
import fr.wseduc.security.ActionType;
import fr.wseduc.security.SecuredAction;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import org.entcore.common.controller.ControllerHelper;

public class MyScriptsController extends ControllerHelper {

    private MyScriptsService scriptsService;

    public MyScriptsController() {
        scriptsService = new DefaultMyScriptsService();
    }

    @Get("/scripts")
    @ApiDoc("Get all scripts")
    @SecuredAction(value = "", type = ActionType.AUTHENTICATED)
    public void getAllScripts(HttpServerRequest request) {
        scriptsService.getScripts()
                .onSuccess(res -> {
                    JsonObject object = new JsonObject()
                            .put("result", res)
                            .put("status", "ok");
                    renderJson(request, object);
                })
                .onFailure(err -> {
                    JsonObject object = new JsonObject()
                            .put("reason", err.getMessage())
                            .put("status", "ko");
                    renderError(request, object);
                });
    }

    /*TODO:finir @post et apres c'est pas mal
    @Post("/scripts")
    @ApiDoc("Create a new script")
    @SecuredAction(value = "", type = ActionType.AUTHENTICATED)
    public void createScript(HttpServerRequest request) {
        RequestUtils.bodyToJson(request, body -> {
            Scripts script = new Scripts(body);
            scriptsService.createScript(script)
                    .onSuccess(res -> {
                        JsonObject response = new JsonObject()
                                .put("status", "ok")
                                .put("result", res);
                        renderJson(request, response);
                    })
                    .onFailure(err -> {
                        JsonObject response = new JsonObject()
                                .put("status", "ko")
                                .put("reason", err.getMessage());
                        renderError(request, response);
                    });
        });
    }*/

}
