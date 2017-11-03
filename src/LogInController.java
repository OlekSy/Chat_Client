import javafx.fxml.FXML;
import javafx.scene.control.TextField;


import java.io.IOException;


public class LogInController {

    @FXML
    TextField inputField;

    public void logInButtonAction(){
        if(inputField.getText().equals("")){
            inputField.setText("Put you name in this field");
        } else {
            Main.setUserName(inputField.getText());
            try {
                new Main().setChatLayOut();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
