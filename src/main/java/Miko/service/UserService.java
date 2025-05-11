package Miko.service;

import Miko.repository.UserRepository;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Future<JsonArray> getAllUsers() {
    return userRepository.getAllUsers();
  }
}
