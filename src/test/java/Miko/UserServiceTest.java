package Miko;


import Miko.repository.UserRepository;
import Miko.service.UserService;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({VertxExtension.class, MockitoExtension.class})
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  private UserService userService;
  private final Vertx vertx = Vertx.vertx();

  @BeforeEach
  void setUp() {
    userService = new UserService(userRepository);
  }

  @Test
  void getAllUsers_Success(VertxTestContext testContext) {
    // Mock data
    JsonArray mockUsers = new JsonArray()
      .add(new JsonObject().put("id", 1).put("name", "John").put("email", "john@test.com"))
      .add(new JsonObject().put("id", 2).put("name", "Jane").put("email", "jane@test.com"));

    // Mock repository response
    when(userRepository.getAllUsers()).thenReturn(Future.succeededFuture(mockUsers));

    // Test service method
    userService.getAllUsers()
      .onComplete(testContext.succeeding(users -> {
        testContext.verify(() -> {
          assertNotNull(users);
          assertEquals(2, users.size());
          assertEquals("John", users.getJsonObject(0).getString("name"));
          testContext.completeNow();
        });
      }));
  }



}
