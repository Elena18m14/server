package endpoint;

import Utils.Util;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sphinx.Config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static sphinx.SpeechRecord.result;


public class ServerHandler implements HttpHandler {
    public static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private StreamSpeechRecognizer recognizer;

    public ServerHandler(StreamSpeechRecognizer recognizer) {
        this.recognizer = recognizer;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws IOException {
        logger.info(exchange.getRequestURI() + "?" + exchange.getQueryString());
        boolean checkToken = validateRequest(exchange);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        if (checkToken) {
            exchange.getResponseSender().send(get(exchange));
        } else {
            exchange.getResponseSender().send("Please enter a valid token.");
        }
    }

    private boolean validateRequest(HttpServerExchange exchange) throws IOException {
        String token = StringUtils.trimToEmpty(Util.getQueryParametrs(exchange, "token"));
        logger.info("token is " + token);
        return token.equals("speach");
    }

    private  String get(HttpServerExchange exchange) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        exchange.getRequestReceiver().receiveFullBytes((ex, data) -> {
            try {
                byteArrayOutputStream.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ByteArrayInputStream inStream = new ByteArrayInputStream( byteArrayOutputStream.toByteArray() );

        recognizer.startRecognition(inStream);
        String text = result(recognizer);
        recognizer.stopRecognition();
        return text;
    }
}