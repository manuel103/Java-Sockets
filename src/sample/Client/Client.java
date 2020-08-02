package sample.Client;

import sample.Model.Communicator;
import sample.Model.ToyMerchant;
import sample.ClientController;

import java.io.*;
import java.net.Socket;

public class Client {
    private int serverPort;
    private String serverName;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private ClientController clientController;
    private ObjectOutputStream objectOutputStream;
    private OutputStream outputStream;

    public Client(int port, String server_name, ClientController clientController){
        serverPort = port;
        serverName = server_name;
        this.clientController = clientController;
    }

    public void serverConnection(){
        Runnable serverTask = () -> {
            try {
                Socket clientSocket = new Socket(serverName,serverPort);
                System.out.println("Client Connected!");
                clientController.clientStateRadioButton.setSelected(true);
                clientController.clientStateRadioButton.setDisable(false);
                inputStream = clientSocket.getInputStream();
                objectInputStream= new ObjectInputStream(inputStream);
                outputStream = clientSocket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);
                while (clientSocket.getInputStream()!=null){
                    Communicator communicator = (Communicator)objectInputStream.readObject();
                    clientController.postMessage(communicator);
                }

            } catch (IOException exception) {
                System.err.println("An error occurred");
                exception.printStackTrace();
            }catch (ClassNotFoundException classNotFound){
                classNotFound.printStackTrace();
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }

    public void sendToy(ToyMerchant clientToyMerchant) throws IOException{
        System.out.println("Client is communicating ......:"+ clientToyMerchant.getName());
        objectOutputStream.writeObject(clientToyMerchant);
    }

    public void sendForm(ToyMerchant clientToyMerchant) throws IOException{
        clientController.printToyObject(clientToyMerchant);
        System.out.println("Client is communicating ......:"+ clientToyMerchant.getName());
        objectOutputStream.writeObject(clientToyMerchant);

    }
}
