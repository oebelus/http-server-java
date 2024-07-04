import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  private static final int PORT = 4221;

  public static void main(String[] args) {
    String directory = null;
    if (args.length > 1 && args[0].equals("--directory")) {
      directory = args[1];
    }

    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      serverSocket.setReuseAddress(true);

      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("accepted new connection");

        RequestHandler requestHandler = new RequestHandler(clientSocket, directory);

        new Thread(requestHandler).start();
      }
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
