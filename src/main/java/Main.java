import org.apache.commons.codec.binary.Base64InputStream;

import java.io.*;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File(Main.class.getResource("file.txt.gz").getFile());
            InputStream fileInputStream = new FileInputStream(file);
            GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
            Base64InputStream base64InputStream = new Base64InputStream(gzipInputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(base64InputStream);
            FileOutputStream fileOutputStream = new FileOutputStream("output.xml");
            fileOutputStream.write(inputStreamReader.readStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
