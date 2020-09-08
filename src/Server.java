import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private DataInputStream in;
    private DataOutputStream out;

    public static boolean isCloseConnection() {
        return closeConnection;
    }

    private static boolean closeConnection = true;

    public Server () {
        try {
            ServerSocket serverSocket = new ServerSocket(14334);
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
                        while (closeConnection) {
                            String message = in.readUTF();
                            if(message.equals("close terminal")) {
                                closeConnection = false;
                                throw new IOException();
                            }
                            System.out.println("client > server: " + message);
                        }
                    } catch (IOException e) {
                        System.out.println("client closed connection");


                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("server thread waiting console");
                    try {
                        while(closeConnection) {
                            String message = reader.readLine();
                            if (!message.trim().isBlank()) {
                                out.writeUTF(message);
                                System.out.println("sent: " + message );
                            }
                            else throw new IOException();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        try {
                            reader.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                in.close();
                out.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


    }


    public static void main(String[] args) {
        new Server();
    }
}
