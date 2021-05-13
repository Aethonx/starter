package io.shaded.vertx_sample.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    // Let's make a new Http server.
    HttpServer server = vertx.createHttpServer();

    // Used to handle routing the requests.
    Router router = Router.router(vertx);
    router
      .get("/hello/world")
      .respond(
        context -> Future.succeededFuture(new JsonObject().put("hello", "world"))
      );

    server.requestHandler(router).listen(8080);
  }

}
