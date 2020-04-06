import org.apache.commons.codec.binary.Base64InputStream;

import java.io.*;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

public class Main {
    public static void main(String[] args) {
        try {
            writeOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties readProperties() throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader();
        return propertiesReader.read("conf.properties");
    }

    private static InputStreamReader createInputStreamPath(Properties properties) throws IOException {
        File file = new File(Main.class.getResource(properties.getProperty("inputFile")).getFile());
        FileInputStream fileInputStream = new FileInputStream(file);
        GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
        Base64InputStream base64InputStream = new Base64InputStream(gzipInputStream);
        return new InputStreamReader(base64InputStream);
    }

    private static void writeOutput() throws IOException {
        Properties properties = readProperties();
        InputStreamReader inputStreamReader = createInputStreamPath(properties);
        FileOutputStream fileOutputStream = new FileOutputStream(properties.getProperty("outputFile"));
        fileOutputStream.write(inputStreamReader.readStream());
    }
}
