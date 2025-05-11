package Miko.repository;


import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;

public class UserRepository {
  private final MySQLPool client;

  public UserRepository(MySQLPool client) {
    this.client = client;
  }

  public Future<JsonArray> getAllUsers() {
    return client.withConnection(connection ->
      connection.query("SELECT * FROM users")
        .execute()
        .map(rows -> {
          JsonArray users = new JsonArray();
          for (Row row : rows) {
            users.add(new JsonObject()
              .put("id", row.getInteger("id"))
              .put("name", row.getString("name"))
              .put("email", row.getString("email")));
          }

          return users;
        })
    );
  }
}
