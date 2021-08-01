package mock;
import model.Configuration;
import model.Resolution;

import java.util.Random;

public class ConfigurationMock {

    public static Configuration getConfiguration() {
            return new Configuration(Resolution.values()[new Random().nextInt(Resolution.values().length - 1)]);
        }

        private ConfigurationMock() {

        }
}
