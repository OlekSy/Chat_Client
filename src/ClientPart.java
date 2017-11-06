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
        boolean socketIsClosed = false;
        InetAddress address = null;
        String message;
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Socket socket;
        try {
            socket = new Socket(address, 8080);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            controller.setOut(out);

            out.println(name);
            try {
                while (true) {
                    message = in.readLine();
                    if(message.equals("\\quit")){
                        socket.close();
                        socketIsClosed = true;
                        break;
                    }else
                    if(message.equals("\\list")){
                        message = in.readLine();
                        controller.listReceive(message.replaceAll("@~#", "\n"));
                    }else
                    controller.receive(message);
                }
            } finally {
                out.println("\\out");
                if(!socketIsClosed) {
                    socket.close();
                }
                controller.shutDown();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
