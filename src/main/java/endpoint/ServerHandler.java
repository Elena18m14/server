package endpoint;

import Utils.Util;
import edu.cmu.sphinx.api.*;
import edu.cmu.sphinx.frontend.util.StreamDataSource;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.util.TimeFrame;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sphinx.Config;
import sphinx.Speech;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static sphinx.SpeechRecord.result;
import edu.cmu.sphinx.decoder.Decoder;


public class ServerHandler implements HttpHandler {
    public static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private Speech recognizer;

    public ServerHandler(Speech recognizer) {
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


       // recognizer.

        ByteArrayInputStream inStream = new ByteArrayInputStream( byteArrayOutputStream.toByteArray() );


      //  Speech recognizer1 = new Speech(Config.addCconfig());

      //  recognizer1 = recognizer;

       // ServerHandler s =  new ServerHandler(recognizer1);
     //   s.recognizer.startS(inStream);

       // String text = "!!!!!" + result(recognizer);
        //String text1 = "SPEECH";
        //Context context = new Context(Config.addCconfig());
        //Recognizer realrecognizer = context.getInstance(Recognizer.class);
       // realrecognizer.allocate();
       // System.out.println("see 1");
       // ByteArrayInputStream inStream = new ByteArrayInputStream( byteArrayOutputStream.toByteArray() );

       /// context.setSpeechSource(inStream, TimeFrame.INFINITE);
       // realrecognizer.recognize();


      // .out.format("Hypothesis: %s\n", result.getHypothesis());
     //   }

       // AbstractSpeechRecognizer recognizer1 = new AbstractSpeechRecognizer(Config.addCconfig());

      //  System.out.println("see 2");
        //recognizer.startRecognition(inStream);

        //Speech rec = new Speech(Config.addCconfig());


       recognizer.startS(inStream);
      // recognizer.recognizer.resetMonitors();
     //   System.out.println("see 3");
           String text = "!!!!!" + result(recognizer);
     //   System.out.println("see 4");
     //   recognizer.stopRecognition();
     //   System.out.println("see 5");
        recognizer.recognizer().resetMonitors();

        return text;
    }
}