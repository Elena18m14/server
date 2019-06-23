package Utils;

import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.Map;

public class Util {
    public static String getQueryParametrs(HttpServerExchange exchange, String key) {
        Map<String, Deque<String>> queryParametrs = exchange.getQueryParameters();
        Deque<String> paramsForKey = queryParametrs.get(key);
        if (paramsForKey != null && paramsForKey.size() > 0) {
            return paramsForKey.getFirst();
        } else {
            return null;
        }
    }
}
