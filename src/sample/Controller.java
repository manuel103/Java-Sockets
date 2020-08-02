package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;

public class Controller {

    @FXML
    private Label portLabel;

    @FXML
    public void openServerWindow(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ServerController.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Server");
            stage.setScene(new Scene(root,650,500));
            stage.setResizable(false);
            stage.show();
        }catch (IOException exception){
            exception.printStackTrace();
        }

    }


    @FXML
    private void openClientWindow(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ClientController.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Client");
            stage.setScene(new Scene(root,700,450));
            stage.setResizable(false);
            stage.show();
        }catch (IOException exception){
            exception.printStackTrace();
        }

    }

    @FXML
    public void showAvailablePort(ActionEvent actionEvent) {
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            String availablePortNumber = String.valueOf(serverSocket.getLocalPort());
            portLabel.setDisable(false);
            portLabel.setText("PORT: "+availablePortNumber);
        } catch (IOException e) {
            portLabel.setDisable(false);
            portLabel.setText("AN ERROR OCCURRED");
            e.printStackTrace();
        }
    }
}
