import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    private Set<ClientHandler> clientHandlers;
    private AuthenticationService authenticationService;
    private long startConnectionTime;


    public Server() {
        this.clientHandlers = new HashSet<>();
        this.authenticationService = new AuthenticationService();
        start(8888);
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    private void start(int port) {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            clientListener(serverSocket);
        }catch(IOException e) {
            throw new RuntimeException("ServerSocket is in troubles...");
        }
    }

    private void clientListener(ServerSocket serverSocket) throws IOException {
        while(true) {
            System.out.println("Looking for a client...");
            Socket clientSocket = serverSocket.accept();

            System.out.println("Client connected: " + clientSocket);
            new ClientHandler( clientSocket, this);
            timeAuthCheck(clientSocket);
        }
    }
    public void timeAuthCheck(Socket socket) {
        boolean isAuth = false;
        try {
            Thread.sleep(1000);
            for(ClientHandler ch: clientHandlers) {
                if(ch.getSocket().equals(socket)) {
                    isAuth = true;
                    break;
                }
                break;
            }
            if(!isAuth) {
                try {

                    socket.close();
                    
                } catch (IOException e) {
                    System.out.println("Client has been disconnected due to exceeded time for authorization");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String incomingMessage) {
        for(ClientHandler ch : clientHandlers) {
            ch.sendMessage(incomingMessage);
        }
    }

    public void subscribe(ClientHandler client) {
        clientHandlers.add(client);

    }

    public void unSubscribe(ClientHandler client) {
        clientHandlers.remove(client);

    }

    public boolean checkLogin(String name) {
        for(ClientHandler ch: clientHandlers) {
            if(ch.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new Server().start(8888);
    }


}

