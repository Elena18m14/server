package Utils;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import io.undertow.server.HttpServerExchange;
import sphinx.Config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Deque;
import java.util.Map;

import static sphinx.SpeechRecord.result;

public class Util {
    public static String getQueryParametrs(HttpServerExchange exchange, String key) throws IOException {
        Map<String, Deque<String>> queryParametrs = exchange.getQueryParameters();
        Deque<String> paramsForKey = queryParametrs.get(key);



        if (paramsForKey != null && paramsForKey.size() > 0) {
            return paramsForKey.getFirst();
        } else {
            return null;
        }
    }
}
