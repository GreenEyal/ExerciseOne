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
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int bytesRead;
        try {
            bytesRead = inputStream.read(buffer);
            while (bytesRead != -1) {
                baos.write(buffer);
                bytesRead = inputStream.read(buffer);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
