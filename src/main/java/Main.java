import org.apache.commons.codec.binary.Base64InputStream;

import java.io.*;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

public class Main {
    public static void main(String[] args) {
        try {
            PropertiesReader propertiesReader = new PropertiesReader();
            Properties properties = propertiesReader.read("conf.properties");
            File file = new File(Main.class.getResource(properties.getProperty("inputFile")).getFile());
            InputStream fileInputStream = new FileInputStream(file);
            GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
            Base64InputStream base64InputStream = new Base64InputStream(gzipInputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(base64InputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(properties.getProperty("outputFile"));
            fileOutputStream.write(inputStreamReader.readStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
