import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

public class Read implements HttpHandler {
    @Override
    public void handleRequest(HttpServerExchange exchange) {
        exchange.getResponseSender().send("Read me");
    }

}
