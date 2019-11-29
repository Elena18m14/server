package sphinx;

import edu.cmu.sphinx.api.Configuration;

public class Config {
    public static Configuration addCconfig() {
        Configuration configuration = new Configuration();
        configuration
                .setAcousticModelPath("resource:/zero_ru_cont_8k_v3/zero_ru.cd_cont_4000");

        configuration
                .setDictionaryPath("resource:/zero_ru_cont_8k_v3/ru.dic");
        configuration
                .setLanguageModelPath("resource:/zero_ru_cont_8k_v3/ru.lm");
        //configuration.setSampleRate(8000);
        return configuration;
    }
}