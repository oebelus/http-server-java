import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public class Utils {
    public static Map<String, String> getHeaders(BufferedReader in) throws IOException {
        Map<String, String> headers = new HashMap<>();

        String headerline;

        while ((headerline = in.readLine()) != null && !headerline.isEmpty()) {
            if (headerline.isEmpty())
                continue;

            String[] headerParts = headerline.split(":");

            if (headerParts.length > 1) {
                headers.put(headerParts[0].trim().toLowerCase(), headerParts[1].trim());
            }
        }

        return headers;
    }

    public static String getBody(String[] arr) {
        if (arr[0].equals("echo")) {
            return arr[1];
        }

        return String.join("/", arr);
    }

    public static byte[] gzip(byte[] bodyBytes) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(bodyBytes.length * 4);
        GZIPOutputStream zipStream = new GZIPOutputStream(byteStream);
        zipStream.write(bodyBytes);
        zipStream.close();

        byte[] compressedBytes = byteStream.toByteArray();
        return compressedBytes;
    }

    public static String bytesToHex(byte[] bytes, int length) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x", bytes[i]));
        }

        return sb.toString();

    }
}
