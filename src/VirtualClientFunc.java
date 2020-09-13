import java.io.*;
import java.net.Socket;

public class VirtualClientFunc {

    String name;
    private DataInputStream in;
    private DataOutputStream out;
    Socket socket;
    private boolean isConnected;


    public VirtualClientFunc(String name) {
        this.name = name;
        try {
            start();
        } catch (IOException e) {
            System.out.println("something went wrong on starting of client app.");
            e.printStackTrace();
        }

    }

    public void start() throws IOException {
        while(!isConnected) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please write down login");
            String login = reader.readLine().trim();
            System.out.println("pass...");
            String password = reader.readLine();
            if(clientToConnect(login, password).equals("status:login/pass mistake")) {
                System.out.println("write down new login");
                login = reader.readLine().trim();
                System.out.println("write down new password phrase");
                password = reader.readLine();
                reader.close();
            } else break;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        String message = in.readUTF();
                        System.out.println("Server wrote " + message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    try {
                        String message = reader.readLine();
                        if(!message.trim().isBlank()) out.writeUTF(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                        try {
                            reader.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }

    public String clientToConnect(String login, String password) {
        isConnected = false;
        try {
            socket = new Socket("127.0.0.1", 8888);
            System.out.println(String.format("%s is online, starting authorization... " , name + " " + socket));
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(String.format("-auth %s %s", login, password));
            String message = in.readUTF();
            if(message.contains("authok")) {
                System.out.println("Client has connected");
                return "status:ok";
            } else if(message.contains("Incorrect credentials")) {
                System.out.println("Wrong login or password\n forgot login or password?\n try l(1-3), p(1-3)");
                return "status:login/pass mistake";
            } else {
                System.out.println("Something went wrong, please try again later/never");
                return "status:tbi";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "internal error";
    }

    public static void main(String[] args) {
        new VirtualClientFunc("John");
//        new VirtualClientFunc("Mickhael");
    }
}
