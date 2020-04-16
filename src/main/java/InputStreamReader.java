import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamReader {
    private InputStream inputStream;

    public InputStreamReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Method to read from inputStream and return a byte array of all bytes read
     * Uses ByteArrayOutputStream to construct byte array which will be returned
     */
    public byte[] readStream() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            IOUtils.copy(inputStream, baos);
            inputStream.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
