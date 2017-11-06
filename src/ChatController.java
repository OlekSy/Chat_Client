import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.PrintWriter;

/**
 * Created by damaz on 03.11.2017.
 */
public class ChatController {

    @FXML private TextArea clientsOutput, textOutput;
    @FXML private TextField input;
    @FXML private Button btnSend;

    private PrintWriter out;

    public void initialize(){
        clientsOutput.setEditable(false);
        textOutput.setEditable(false);
        textOutput.setText("Welcome to chat!");

        //set focus to chat input field
        Platform.runLater(() -> input.requestFocus());
    }

    public void send(){
        out.println(input.getText());
        if (input.getText().equals("\\quit")) Platform.exit();
        input.clear();
    }

    public void setOut(PrintWriter out) {this.out = out;}

    public void receive(String message){
        textOutput.appendText("\n" + message);
    }

    public void listReceive(String message){clientsOutput.setText(message);}

}
