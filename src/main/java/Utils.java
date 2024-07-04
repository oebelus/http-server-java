import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
}
