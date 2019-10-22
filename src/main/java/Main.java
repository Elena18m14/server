import Utils.Speech;
import edu.cmu.sphinx.api.Configuration;
import endpoint.ServerHandler;
import io.undertow.Handlers;
import io.undertow.Undertow;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sphinx.Config;

import java.io.IOException;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure(); // needed for logger to work
        Configuration configuration = Config.addCconfig();

        Speech recognizer = new Speech(
                configuration);
        recognizer.start();
        Undertow server = Undertow.builder()
                .addHttpListener(4274, "0.0.0.0")
                .setHandler(
                        Handlers.path()
                                .addExactPath("/endpoint", new ServerHandler(recognizer))
                )
                .build();
        logger.info("Server start port 4274");
        server.start();
    }

    public static void main1(String[] args) throws Exception {
       //  File source = new File("1.ogg");
       // File wavTarget = new File("2.wav");
       // FFmpegBuilder builder = new FFmpegBuilder().addInput("1.ogg");


        System.out.println("!@");
    }
}