import java.io.*;
import java.util.Properties;

public class PropertiesReader {

    public Properties read(String propertiesFile) throws IOException {
        Properties properties = new Properties();
        FileReader reader = new FileReader(PropertiesReader.class.getResource(propertiesFile).getFile());
        properties.load(reader);
        return properties;
    }
}
