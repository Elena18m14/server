package Utils;


import java.io.IOException;
import java.io.InputStream;

import edu.cmu.sphinx.api.AbstractSpeechRecognizer;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.util.TimeFrame;

    /**
     * Speech recognizer that works with audio resources.
     *
     */
    public class Speech extends AbstractSpeechRecognizer {

        /**
         * Constructs new stream recognizer.
         *
         * @param configuration configuration
         * @throws IOException error occured during model load
         */
        public Speech(Configuration configuration)
                throws IOException
        {
            super(configuration);
        }

        public void startRecognition(InputStream stream) {
            startRecognition(stream, TimeFrame.INFINITE);
        }

        /**
         * Starts recognition process.
         *
         * Starts recognition process and optionally clears previous data.
         *
         * @param stream input stream to process
         * @param timeFrame time range of the stream to process
         * @see edu.cmu.sphinx.api.StreamSpeechRecognizer#stopRecognition()
         */


        public void startRecognition(InputStream stream, TimeFrame timeFrame) {
            recognizer.allocate();
            context.setSpeechSource(stream, timeFrame);
        }
        public void start() {
            recognizer.allocate();
        }

        public void startS(InputStream stream) {
            context.setSpeechSource(stream, TimeFrame.INFINITE);
        }
        /**
         * Stops recognition process.
         *
         * Recognition process is paused until the next call to startRecognition.
         *
         * @see edu.cmu.sphinx.api.StreamSpeechRecognizer#startRecognition(InputStream, TimeFrame)
         */
        public void stopRecognition() {
            recognizer.deallocate();
        }

        public Recognizer recognizer() {
            return recognizer;
        }
    }

