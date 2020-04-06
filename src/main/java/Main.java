import org.apache.commons.codec.binary.Base64InputStream;

import java.io.*;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        File file = new File(Main.class.getResource(properties.getProperty("file")).getFile());
        byte[] buffer = new byte[1024];
        int signal;
        String decodedString = "";
        try {
            InputStream fileInputStream = new FileInputStream(file);
            GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
            Base64InputStream base64InputStream = new Base64InputStream(gzipInputStream);
            signal = base64InputStream.read(buffer);
            while (signal != -1) {
                decodedString += new String(buffer);
                signal = base64InputStream.read(buffer);
            }
            gzipInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(decodedString);
    }
}
