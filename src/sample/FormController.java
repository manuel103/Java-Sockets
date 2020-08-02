package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.Client.Client;
import sample.Model.Communicator;
import sample.Model.ToyMerchant;
import sample.Model.Manufacturer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormController implements Initializable{
    private Client client;
    private Communicator communicator;
    private ToyMerchant toyMerchant;
    private Manufacturer manufacturer;
    @FXML
    public JFXTextField textFieldToyName;

    @FXML
    public JFXTextField textFieldToyCode;

    @FXML
    public JFXTextField textFieldToyDescription;

    @FXML
    public JFXTextField textFieldToyPrice;

    @FXML
    public JFXDatePicker datePicker;

    @FXML
    public JFXTextField textFieldManufacturerName;

    @FXML
    public JFXTextField textFieldManufacturerStreetAddress;

    @FXML
    public JFXTextField textFieldManufacturerZipCode;

    @FXML
    public JFXTextField textFieldManufacturerCountry;



    @FXML
    public JFXButton btnSubmitToy;

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMessage(Communicator communicator){
        this.communicator = communicator;
    }


    public void submitToy(ActionEvent actionEvent){
        toyMerchant =new ToyMerchant();
        manufacturer = new Manufacturer();
        if(!textFieldToyName.getText().isEmpty() ) {
                toyMerchant.setName(textFieldToyName.getText().trim());
            }
        if(!textFieldToyCode.getText().isEmpty()) {
            toyMerchant.setCode(textFieldToyCode.getText().trim());
        }
        if(!textFieldToyDescription.getText().isEmpty()) {
            toyMerchant.setDescription(textFieldToyDescription.getText().trim());
        }
        if(!textFieldToyPrice.getText().isEmpty()) {
            toyMerchant.setPrice(Integer.parseInt(textFieldToyPrice.getText()));
        }
        if(datePicker.getValue()!=null){
            toyMerchant.setDOM(datePicker.getValue().toString().trim());

        }
        if(!textFieldManufacturerName.getText().isEmpty()) {
            manufacturer.setCompanyName(textFieldManufacturerName.getText().trim());
        }
        if(!textFieldManufacturerZipCode.getText().isEmpty()) {
            manufacturer.setZipCode(textFieldManufacturerZipCode.getText().trim());
        }
        if(!textFieldManufacturerStreetAddress.getText().isEmpty()) {
            manufacturer.setStreetAddress(textFieldManufacturerStreetAddress.getText().trim());
        }
        if(!textFieldManufacturerCountry.getText().isEmpty()) {
            manufacturer.setCountry(textFieldManufacturerCountry.getText().trim());
        }
        toyMerchant.setToyManufacturer(manufacturer);
        try {
            client.sendForm(toyMerchant);
        }catch (IOException io){
            io.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSubmitToy.setOnAction(this::submitToy);
    }
}
