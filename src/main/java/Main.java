import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

public class Main {
    static final String configFile = "conf.properties";

    public static void main(String[] args) {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties readProperties(String propertiesFile) throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader();
        return propertiesReader.read(propertiesFile);
    }

    private static InputStream createInputStreamPath(String propertiesFile) throws IOException {
        Properties properties = readProperties(propertiesFile);
        File file = new File(properties.getProperty("inputFile"));
        FileInputStream fileInputStream = new FileInputStream(file);
        GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
        return new Base64InputStream(gzipInputStream);
    }

    private static void writeOutput(String propertiesFile, InputStream inputStream) throws IOException {
        Properties properties = readProperties(propertiesFile);
        FileOutputStream fileOutputStream = new FileOutputStream(properties.getProperty("outputFile"));
        IOUtils.copy(inputStream, fileOutputStream);
    }

    private static void start() throws IOException {
        InputStream inputStream = createInputStreamPath(configFile);
        writeOutput(configFile, inputStream);
    }
}
