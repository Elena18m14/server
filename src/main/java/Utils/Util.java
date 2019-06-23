package Utils;

import io.undertow.server.HttpServerExchange;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Deque;
import java.util.Map;

public class Util {
    public static String getQueryParametrs(HttpServerExchange exchange, String key) {
        Map<String, Deque<String>> queryParametrs = exchange.getQueryParameters();
        Deque<String> paramsForKey = queryParametrs.get(key);

        ByteArrayOutputStream r = new ByteArrayOutputStream();
       // AudioInputStream a = new AudioInputStream(r, AudioFormat.Encoding.PCM_FLOAT, 10);
        exchange.getRequestReceiver().receiveFullBytes((ex, data) -> {
            try {
                r.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        if (paramsForKey != null && paramsForKey.size() > 0) {
            return paramsForKey.getFirst();
        } else {
            return null;
        }
    }
}
