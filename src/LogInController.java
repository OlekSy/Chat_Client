import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


import java.io.IOException;


public class LogInController {

    @FXML
    TextField inputField;

    public void logInButtonAction(){
        if(inputField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No name entered!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a name with a maximum of 20 characters.");
            alert.showAndWait();
        } else if (inputField.getText().length() > 20) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Name too long!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a name with a maximum of 20 characters.");
            alert.showAndWait();
        }
        else {
            Main.setUserName(inputField.getText());
            try {
                new Main().setChatLayOut();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
