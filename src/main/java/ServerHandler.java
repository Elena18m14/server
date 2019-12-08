import Utils.Speech;
import Utils.Util;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sphinx.Audio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static sphinx.SpeechRecord.result;


public class ServerHandler implements HttpHandler {
    public static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private Speech recognizer;

    public ServerHandler(Speech recognizer) {
        this.recognizer = recognizer;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        logger.info(exchange.getRequestURI() + "?" + exchange.getQueryString());
        boolean checkToken = validateRequest(exchange);
        String filedName = StringUtils.trimToEmpty(Util.getQueryParametrs(exchange, "filedName"));
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        if (checkToken) {
            if (!filedName.isEmpty()) {
                System.out.println("filedName");
                exchange.getResponseSender().send(get(filedName));
            } else {
                exchange.getResponseSender().send(get(exchange));
            }
        } else {
            exchange.getResponseSender().send("Please enter a valid token.");
        }
    }

    private boolean validateRequest(HttpServerExchange exchange) {
        String token = StringUtils.trimToEmpty(Util.getQueryParametrs(exchange, "token"));
        logger.info("token is " + token);
        return token.equals("speach");
    }

    private String get(String fieldname) throws Exception {
        InputStream stream = Audio.inputStreemFromPat(fieldname);
        recognizer.startS(stream);
        String text = result(recognizer);
        recognizer.recognizer().resetMonitors();
        System.out.println("Print filedName");
        return new String(text.getBytes(), StandardCharsets.UTF_8);
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

       ByteArrayInputStream inStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
       recognizer.startS(inStream);
       String text = result(recognizer);
       recognizer.recognizer().resetMonitors();
       inStream.close();
       return new String(text.getBytes(), StandardCharsets.UTF_8);

    }
}