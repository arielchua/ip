package chuachua;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    //start automatically has a stage object (main window)
    //we need to create a scene and set it on the stage then show it.

        //loading FXML
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        Scene scene = new Scene(loader.load());

        //configuring the window
        MainWindow mainWindow = loader.getController();
        mainWindow.setChuaChua(new ChuaChua("./data/chua.txt"));

        stage.setScene(scene); //attach scene to window
        stage.show();
    }
}
