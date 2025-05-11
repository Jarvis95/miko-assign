package Miko;


import Miko.controller.UserController;
import Miko.repository.UserRepository;
import Miko.service.UserService;
import Miko.util.DatasourceConfig;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.mysqlclient.MySQLPool;


public class MainVerticle extends AbstractVerticle {



  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    MySQLPool client = DatasourceConfig.setupPool(vertx);

    UserRepository userRepository = new UserRepository(client);
    UserService userService = new UserService(userRepository);
    UserController userController = new UserController(userService);

    Router router = Router.router(vertx);

    router.get("/api/users").handler(userController::getAllUsers);

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8888, http -> {
        if (http.succeeded()) {
          startPromise.complete();
          System.out.println("HTTP server started on port 8888");
        } else {
          startPromise.fail(http.cause());
        }
      });
  }
}
