import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Created by damaz on 03.11.2017.
 */
public class ChatController {

    @FXML private TextArea clientsOutput, textOutput;
    @FXML private TextField output;
    @FXML private Button btnSend;

    private PrintWriter out;

    public void initialize(){
        clientsOutput.setEditable(false);
        textOutput.setEditable(false);
        textOutput.setText("Welcome to chat!");
    }

    public void send(){
        textOutput.appendText("Here I am");
        out.println(output.getText());
        output.clear();
    }

    public void setOut(PrintWriter out) {this.out = out;}

    public void receive(String message){
        textOutput.appendText("\n" + message);
    }

}
