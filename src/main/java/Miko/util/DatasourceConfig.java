package Miko.util;

import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;


public class DatasourceConfig {
  public static MySQLPool setupPool(Vertx vertx) {
    MySQLConnectOptions connectOptions = new MySQLConnectOptions()
      .setPort(3306)
      .setHost("localhost")
      .setDatabase("vertx_assign")
      .setUser("root")
      .setPassword("root");

    PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
    return MySQLPool.pool(vertx, connectOptions, poolOptions);
  }

}
