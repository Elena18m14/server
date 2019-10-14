package sphinx;

import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.decoder.adaptation.Stats;
import edu.cmu.sphinx.decoder.adaptation.Transform;
import edu.cmu.sphinx.result.WordResult;

import java.io.InputStream;

public class SpeechRecord {
/*
    private static void simple(StreamSpeechRecognizer recognizer, String wave) throws Exception {
        InputStream stream = Audio.inputStreemFromPat(wave);
        recognizer.startRecognition(stream);
        String text = result(recognizer);
        recognizer.stopRecognition();
    }

    private static void hard(StreamSpeechRecognizer recognizer, String wave) throws Exception {
        InputStream stream = Audio.inputStreemFromPat(wave);

        recognizer = stats(recognizer, wave);
        // Decode again with updated transform
        recognizer.startRecognition(stream);
        String text = result(recognizer);
        recognizer.stopRecognition();
    }

    private static StreamSpeechRecognizer stats(StreamSpeechRecognizer recognizer, String wave) throws Exception {
        SpeechResult result;
        InputStream stream = Audio.inputStreemFromPat(wave);
        Stats stats = recognizer.createStats(1);
        recognizer.startRecognition(stream);
        while ((result = recognizer.getResult()) != null) {
            stats.collect(result);
        }
        recognizer.stopRecognition();

        // Transform represents the speech profile
        Transform transform = stats.createTransform();
        recognizer.setTransform(transform);
        return recognizer;
    }
*/
    public static String result(Speech recognizer) {
        SpeechResult result;
        StringBuilder text = new StringBuilder();
        while ((result = recognizer.getResult()) != null) {
            text.append(result.getHypothesis()).append(" ");
            //System.out.format("Hypothesis: %s\n", result.getHypothesis());
        }
        //recognizer.stopRecognition();
       /* while ((result = recognizer.getResult()) != null) {
            System.out.format("Hypothesis: %s\n", result.getHypothesis());
            //TODO
            text += result.getNbest(2).iterator().next().replaceAll("<s> ","")
                    .replaceAll("</s>", "");
            System.out.println("List of recognized words and their times:");
            for (WordResult r : result.getWords()) {
                System.out.println(r);
            }

            System.out.println("Best 3 hypothesis:");
            for (String s : result.getNbest(3)) {
                System.out.println(s);
            }
        }
        System.out.println(text);

        */
       System.out.println(text.toString());
        return text.toString();
    }
}
