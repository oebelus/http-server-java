import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  private static final int PORT = 4221;

  public static void main(String[] args) {

    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      serverSocket.setReuseAddress(true);

      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("accepted new connection");

        RequestHandler requestHandler = new RequestHandler(clientSocket);

        new Thread(requestHandler).start();
      }
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
