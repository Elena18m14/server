package endpoint;

import Utils.Util;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.Map;

public class ServerHandler implements HttpHandler {
    public static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void handleRequest(HttpServerExchange exchange) {
        logger.info(exchange.getRequestURI() + "?" + exchange.getQueryString());

        boolean checkToken = validateRequest(exchange);

        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        if (checkToken) {
            exchange.getResponseSender().send("Hello Word!");
        } else {
            exchange.getResponseSender().send("Please enter a valid token.");
        }
    }

    private static boolean validateRequest(HttpServerExchange exchange) {
        //Map<String, Deque<String>> queryParametrs = exchange.getQueryParameters();

        String token = StringUtils.trimToNull(Util.getQueryParametrs(exchange, "token"));

        logger.info("token is " + token);
        return token.equals("speach");
    }
}
