import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    private Set<ClientHandler> clientHandlers;
    private AuthenticationService authenticationService;

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

