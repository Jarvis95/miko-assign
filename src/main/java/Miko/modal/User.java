package Miko.modal;

import io.vertx.core.json.JsonObject;

public class User {
  private int id;
  private String name;
  private String email;

  public User(int id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public JsonObject toJson() {
    return new JsonObject()
      .put("id", id)
      .put("name", name)
      .put("email", email);
  }
}
