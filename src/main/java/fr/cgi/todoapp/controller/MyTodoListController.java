package fr.cgi.todoapp.controller;

import fr.cgi.todoapp.models.Todo;
import fr.cgi.todoapp.service.MyTodolistService;
import fr.cgi.todoapp.service.impl.DefaultMyTodolistService;
import fr.wseduc.rs.*;
import fr.wseduc.security.ActionType;
import fr.wseduc.security.SecuredAction;
import fr.wseduc.webutils.request.RequestUtils;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import org.entcore.common.controller.ControllerHelper;

import java.util.List;
import java.util.stream.Collectors;

public class MyTodoListController extends ControllerHelper {

    private MyTodolistService todolistService;

    public MyTodoListController() {
        todolistService = new DefaultMyTodolistService();
    }

    @Get("")
    @ApiDoc("Render view")
    @SecuredAction(value = "todolist.view", type = ActionType.WORKFLOW)
    public void render(HttpServerRequest request) {
        renderView(request);
    }

    @Get("/todolists")
    @ApiDoc("List all todolists")
    @SecuredAction(value = "", type = ActionType.AUTHENTICATED)
    public void getTodolists(HttpServerRequest request) {
        List<Integer> ids = request.params().getAll("id")
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        todolistService.get(ids)
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

    @Post("/todolists")
    @ApiDoc("Create a todolist")
    @SecuredAction(value = "", type = ActionType.AUTHENTICATED)
    public void createTodolist(HttpServerRequest request) {
        RequestUtils.bodyToJson(request, body -> {
            Todo todo = new Todo(body);
            todolistService.create(todo)
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
        });
    }

    @Put("/todolists/:id")
    @ApiDoc("Update given todolist")
    @SecuredAction(value = "", type = ActionType.AUTHENTICATED)
    public void updateTodolist(HttpServerRequest request) {
        Integer id = Integer.parseInt(request.params().get("id"));
        RequestUtils.bodyToJson(request, body -> {
            Todo todo = new Todo(body);
            todolistService.update(id, todo)
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
        });
    }

    @Delete("/todolists/:id")
    @ApiDoc("Delete given todolist")
    @SecuredAction(value = "", type = ActionType.AUTHENTICATED)
    public void deleteTodolist(HttpServerRequest request) {
        Integer id = Integer.parseInt(request.params().get("id"));
        todolistService.delete(id)
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




}