import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    DataInputStream in;
    DataOutputStream out;

    public Server () {
        try {
            ServerSocket serverSocket = new ServerSocket(14333);
            System.out.println("Server starting");
            Socket socket = serverSocket.accept();
            System.out.println("Server got connection " + socket);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("Server thread network waiting...");
                        while (true) {
                            String message = in.readUTF();
                            System.out.println("client > server: " + message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("server thread waiting console");
                    try {
                        while(true) {
                            String message = reader.readLine();
                            if (!message.trim().isBlank()) out.writeUTF(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new Server();
    }
}
