import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  private static final int PORT = 4221;

  public static void main(String[] args) {

    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      serverSocket.setReuseAddress(true);

      Socket clientSocket = serverSocket.accept();
      System.out.println("accepted new connection");

      new Thread(new RequestHandler(clientSocket)).start();
      ;

    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
