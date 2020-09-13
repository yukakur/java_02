import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private String name;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;

    public String getName() {
        return name;
    }

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.server = server;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    authentication();
                    readMessage();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    closeConnection();
                }
            }
        }).start();
    }

    public void authentication() throws IOException{
        while(true) {
            String loginInfo = in.readUTF();
            if(loginInfo.startsWith("-auth")) {
                String [] splittedLoginInfo = loginInfo.split("\\s");
                AuthenticationService.Client maybeClient = server.getAuthenticationService()
                        .findLoginAndPassword(
                                splittedLoginInfo[1],
                                splittedLoginInfo[2]
                        );
                if(maybeClient != null) {
                    if(!server.checkLogin(maybeClient.getName())) {
                        sendMessage("status: authok");
                        name = maybeClient.getName();
                        server.broadcast(String.format("%s is in the chat", name));
                        System.out.println("Client auth completed");
                        server.subscribe(this);
                        return;
                    } else {
                        sendMessage(String.format("%s already logged in", maybeClient.getName()));
                    }
                } else {
                    sendMessage("Incorrect credentials");
                }
            }
        }
    }

    public void closeConnection() {
        server.unSubscribe(this);
        server.broadcast(String.format("%s has left ", name));
    }

    public void readMessage() throws IOException {
        while (true) {
            String message = in.readUTF();
            String formattedMessage = String.format("Message from  %s: %s", name, message);
            System.out.println(formattedMessage);
            if (message.equals("-exit")) {
                return;
            }
            server.broadcast(formattedMessage);
        }
    }


    public void sendMessage (String message) {
//            while (true) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
//            }
    }
}

