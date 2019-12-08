import Utils.Speech;
import edu.cmu.sphinx.api.Configuration;
import io.undertow.Handlers;
import io.undertow.Undertow;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sphinx.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure(); // needed for logger to work
        Configuration configuration ;//= Config.addCconfig();

        Speech recognizer = null;// new Speech(
                //configuration);
        //recognizer.start();
        final
        Undertow server = Undertow.builder()
                .addHttpListener(4274, "0.0.0.0")

                .setHandler(
                        Handlers.path()
                                .addExactPath("/read",
                                        Handlers.routing()
                                                .get("/",  new Read()))
                                .addExactPath("/audio",
                                        Handlers.routing()
                                                .get("/",  new Audio(recognizer)))
                )

                .build();
        logger.info("Server start port 4274");
        server.start();
    }

    public static void main1(String[] args) throws Exception {
       //  File source = new File("1.ogg");
       // File wavTarget = new File("2.wav");
        String peticaion = "ffmpeg -i D:/file/bde0eab6-e4a7-49c6-ac8e-505c97957bb3.ogg -ar 16000 -ac 1 D:/file/10.wav";
        Process p = Runtime.getRuntime().exec(peticaion);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";


        while ((line = br.readLine()) != null) {
            System.out.println("DSF");
        }

        System.out.println("!@");
    }
}