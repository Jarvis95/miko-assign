package Miko.controller;

import Miko.service.UserService;
import io.vertx.ext.web.RoutingContext;

public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  public void getAllUsers(RoutingContext ctx) {
    userService.getAllUsers()
      .onSuccess(users -> ctx.response()
        .setStatusCode(200)
        .putHeader("Content-Type", "application/json")
        .end(users.encode()))
      .onFailure(err -> ctx.response()
        .setStatusCode(500)
        .end("Error fetching users: " + err.getMessage()));
  }
}
