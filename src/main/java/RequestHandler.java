import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class RequestHandler implements Runnable {
    private static final String CRLF = "\r\n";
    private final Socket clientSocket;

    public RequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())))) {
            OutputStream out = clientSocket.getOutputStream();

            String[] requestParts = (in.readLine()).split("\\s+");
            final Map<String, String> headers = Utils.getHeaders(in);

            String path = requestParts[1];

            String response;

            if (path.startsWith("/user-agent")) {
                response = handleUserAgent(headers);
            } else if (path.equals("/")) {
                response = "HTTP/1.1 200 OK" + CRLF + CRLF;
            } else {
                response = "HTTP/1.1 404 Not Found" + CRLF + CRLF;
            }

            out.write(response.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String handleUserAgent(Map<String, String> headers) {
        String text = headers.get("user-agent");
        return "HTTP/1.1 200 OK" + CRLF +
                "Content-Type: text/plain" + CRLF +
                "Content-Length: " + text.length() + CRLF + CRLF +
                text;
    }
}
