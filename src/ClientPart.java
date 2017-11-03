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

    public ClientPart(ChatController controller){
        this.name = Main.getUserName();
        this.controller = controller;
        this.setDaemon(true);
    }

    @Override
    public void run(){
        InetAddress address = null;
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Socket socket;
        try {
            socket = new Socket(address, 27777);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            controller.setOut(out);
            try {
                while (true) {
                    controller.receive(in.readLine());
                }
            } finally {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
