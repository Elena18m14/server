package Utils;

import io.undertow.server.HttpServerExchange;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Deque;
import java.util.Map;

public class Util {
    public static String getQueryParametrs(HttpServerExchange exchange, String key) {
        Map<String, Deque<String>> queryParametrs = exchange.getQueryParameters();
        Deque<String> paramsForKey = queryParametrs.get(key);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        exchange.getRequestReceiver().receiveFullBytes((ex, data) -> {
            try {
                byteArrayOutputStream.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ByteArrayInputStream inStream = new ByteArrayInputStream( byteArrayOutputStream.toByteArray() );
        AudioFormat format = new AudioFormat(16*1000, 16, 1, true, false);
        //AudioSystem.getAudioFileFormat(r);
        AudioInputStream ais  = new AudioInputStream( inStream , format, byteArrayOutputStream.size());
        if (paramsForKey != null && paramsForKey.size() > 0) {
            return paramsForKey.getFirst();
        } else {
            return null;
        }
    }
}
