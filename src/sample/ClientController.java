package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.Client.Client;
import sample.Model.Communicator;
import sample.Model.ToyMerchant;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class ClientController implements Initializable {

    @FXML
    public JFXTextField portNumber;

    @FXML
    public JFXTextField serverName;

    @FXML
    public JFXButton btnConnect;

    @FXML
    public JFXTextArea messageBoard;

    @FXML
    public JFXTextField toyField;

    @FXML
    public JFXButton sendMessage;

    @FXML
    public JFXRadioButton clientStateRadioButton;


    private Client client;

    public void connectToServer(ActionEvent actionEvent){
        if(!portNumber.getText().isEmpty() && !serverName.getText().isEmpty()){
            client = new Client(Integer.parseInt(portNumber.getText()),serverName.getText(),this);
            client.serverConnection();

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields are required!");
            alert.showAndWait();

        }

    }

    public void sendToy(ActionEvent actionEvent){
        if(!toyField.getText().isEmpty()){
            String message = toyField.getText().trim();
            ToyMerchant clientToyMerchant = new ToyMerchant();
            clientToyMerchant.setCustomMessage(message);

            if (message.contains("Thanks") || message.contains("Thank you")) {
                UUID uuid = UUID.randomUUID();
                message = message.concat("-"+uuid.toString());
                clientToyMerchant.setName(message);

            }
            try{
                messageBoard.appendText("|"+Util.Companion.getCurrentDateTime()+"| "+"Client : "+ message +"\n");
                client.sendToy(clientToyMerchant);
                toyField.clear();
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server name required!",ButtonType.CLOSE,ButtonType.CANCEL);
            alert.showAndWait();

        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnConnect.setOnAction(this::connectToServer);
        sendMessage.setOnAction(this::sendToy);

    }

    public void postMessage(Communicator communicator){
        messageBoard.appendText("|"+Util.Companion.getCurrentDateTime()+"| "+ communicator.getMessage()+"\n");
    }

    @FXML
    public void openForm(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Form.fxml"));
        try {
            Parent root= fxmlLoader.load();
            FormController formController = fxmlLoader.<FormController>getController();
            formController.setClient(client);
            Stage stage = new Stage();
            stage.setTitle("Form");
            stage.setScene(new Scene(root,600,450));
            stage.setResizable(false);
            stage.show();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void printToyObject(ToyMerchant toyMerchantObject){
        messageBoard.appendText("|"+Util.Companion.getCurrentDateTime()+"|"+" Client : "+Util.Companion.printToyObject(toyMerchantObject)+"\n");
    }
}
