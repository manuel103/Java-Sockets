package sample.Server;

import sample.Model.Messages;
import sample.Model.Communicator;
import sample.Model.ToyMerchant;
import sample.ServerController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int serverPort;
    private boolean isConnected = false;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private Communicator communicator;
    private ServerController serverController;

    public Server(int port, ServerController serverController) {
        serverPort = port;
        this.serverController = serverController;
    }

    public void startServer() {
        final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);

        Runnable serverTask = () -> {
            try {
                ServerSocket serverSocket = new ServerSocket(serverPort);
                System.out.println("Waiting for a client ...");
                isConnected= true;
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    clientProcessingPool.submit(new ClientTask(clientSocket));
                }
            } catch (IOException e) {
                System.err.println("Unable to process client request");
                e.printStackTrace();
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }

    public boolean isConnected(){
        return isConnected;

    }

    public void requestToyName() throws IOException {
        if(isConnected){
            communicator = new Communicator();
            communicator.setMessage(Messages.MESSAGE_A);
            communicator.setMessageCode(Messages.A);
            objectOutputStream.writeObject(communicator);
        }
    }

    public void requestMessage() throws IOException{
        if(isConnected){
            communicator = new Communicator();
            communicator.setMessage(Messages.MESSAGE_D);
            communicator.setMessageCode(Messages.D);
            objectOutputStream.writeObject(communicator);
        }
    }

    public void requestManufacturerDetails() throws IOException {
        if(isConnected){
            communicator = new Communicator();
            communicator.setMessage(Messages.MESSAGE_C);
            communicator.setMessageCode(Messages.C);
            objectOutputStream.writeObject(communicator);
        }
    }

//    public void sendWelcomeMessage() throws IOException{
//        if(isConnected){
//            communicator = new Communicator();
//            communicator.setMessageCode(Messages.WELCOME);
//            communicator.setMessage(Messages.WELCOME_MESSAGE);
//            objectOutputStream.writeObject(communicator);
//        }
//
//
//    }

    public void requestToyInformation() throws IOException{
        if(isConnected){
            communicator = new Communicator();
            communicator.setMessageCode(Messages.B);
            communicator.setMessage(Messages.MESSAGE_B);
            objectOutputStream.writeObject(communicator);
        }
    }

    public void requestAllToyInformation() throws IOException {
        if(isConnected){
            communicator = new Communicator();
            communicator.setMessageCode(Messages.E);
            communicator.setMessage(Messages.MESSAGE_E);
            objectOutputStream.writeObject(communicator);
        }
    }

    private class ClientTask implements Runnable {
        private final Socket clientSocket;

        private ClientTask(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                outputStream = clientSocket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);

                inputStream = clientSocket.getInputStream();
                objectInputStream = new ObjectInputStream(inputStream);

                // sendWelcomeMessage();
            }catch (IOException e){
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            try{
                while (clientSocket.getInputStream()!=null){
                    ToyMerchant clientToyMerchant = (ToyMerchant)objectInputStream.readObject();
                    System.out.println("Message from : "+ clientToyMerchant.getName());
                    serverController.postToy(clientToyMerchant);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}

