import javafx.application.Platform;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by damaz on 03.11.2017.
 */
public class ClientPart extends Thread{
    private String name;
    private ChatController controller;
    private BufferedReader in;
    private PrintWriter out;
    private boolean socketIsClosed;
    private final static int versionNumber = 0; //1.0.0
    private final static int PORT = 27777;

    public ClientPart(ChatController controller){
        this.name = Main.getUserName();
        this.controller = controller;
        this.setDaemon(true);
    }

    @Override
    public void run(){
        InetAddress address = null;
        String message;
        try {
            //address = InetAddress.getByName("localhost");
            address = InetAddress.getByName("45.76.93.162");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Socket socket;
        try {
            socket = new Socket(address, PORT);
            socketIsClosed = false;

            //add shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                out.println("\\out");
                if(!socketIsClosed) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Platform.exit();
            }));

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            out.println(versionNumber);

            controller.setOut(out);

            out.println(name);
            while (true) {
                message = in.readLine();
                if (message == null) break; //client has left
                if(message.equals("\\quit")){
                    socket.close();
                    socketIsClosed = true;
                    Platform.exit();
                    break;
                }else
                if(message.equals("\\list")){
                    message = in.readLine();
                    controller.listReceive(message.replaceAll("@~#", "\n"));
                } else if (message.contains("\\out")) { //contains because message will be: <username> \out
                    System.out.println("Received out command: " + message); //Don't show \out commands, only log
                } else if (message.equals("\\versionTooLow")) {
                    controller.receive("Your version is too low. Please update!");
                }
                else
                    controller.receive(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
