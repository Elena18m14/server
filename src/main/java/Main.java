import endpoint.ServerHandler;
import io.undertow.Handlers;
import io.undertow.Undertow;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        BasicConfigurator.configure(); // nedded for logger to work

        Undertow server = Undertow.builder()
                .addHttpListener(4274, "0.0.0.0")
                .setHandler(
                        Handlers.path()
                            .addExactPath("/endpoint", new ServerHandler())
                )
        .build();
        logger.info("Server start port 4274");
        server.start();
    }
}
