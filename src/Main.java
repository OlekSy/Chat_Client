import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage mainStage;

    private Parent logInLayOut;
    private Parent chatLayOut;

    private Scene logInScene;
    private Scene chatScene;

    private static String userName = "";

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        mainStage.setResizable(false);

        logInLayOut = FXMLLoader.load(getClass().getResource("LogInWindow.fxml"));
        logInScene = new Scene(logInLayOut);

        mainStage.setTitle("ClientPart v1.0");
        mainStage.setScene(logInScene);
        //------------BLACK MAGIC-------------------------------------
        //-------------DONT TOUCH-------------------------------------
        mainStage.setOnCloseRequest(e -> Platform.exit());
        //------------------------------------------------------------
        mainStage.show();
    }

    public void setChatLayOut() throws IOException {
        mainStage.setResizable(true);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatWindow.fxml"));
        chatLayOut = loader.load();
        chatScene = new Scene(chatLayOut, 600, 400);

        new ClientPart(loader.getController()).start();

        mainStage.setScene(chatScene);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void setUserName(String name){userName = name;}

    public static String getUserName(){return userName;}
}
