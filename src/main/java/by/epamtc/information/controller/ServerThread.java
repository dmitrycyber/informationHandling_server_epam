package by.epamtc.information.controller;

import by.epamtc.information.service.RemoveSentencesFixedLengthService;
import by.epamtc.information.service.TextParserService;
import by.epamtc.informationHandle.entity.impl.Text;
import by.epamtc.informationHandle.models.MessageType;
import by.epamtc.informationHandle.models.RemoveWordModel;
import by.epamtc.informationHandle.models.Wrapper;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;
    private TextParserService textParserService;
    private RemoveSentencesFixedLengthService removeSentencesFixedLengthService;


    public ServerThread(Socket socket, TextParserService textParserService, RemoveSentencesFixedLengthService removeSentencesFixedLengthService) throws IOException {
        this.socket = socket;
        this.textParserService = textParserService;
        this.removeSentencesFixedLengthService = removeSentencesFixedLengthService;
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        in = new BufferedReader(new InputStreamReader(inputStream));
        out = new BufferedWriter(new OutputStreamWriter(outputStream));
        objOut = new ObjectOutputStream(outputStream);
        objIn = new ObjectInputStream(inputStream);
        start();
    }


    @Override
    public void run() {
        try {
            while (true) {
                Wrapper messageFromClient = (Wrapper) objIn.readObject();
                System.out.println("get message " + messageFromClient.getMessageType());

                if (messageFromClient.getMessageType().equals(MessageType.STOP_MESSAGING)){
                    killSocket();
                    System.out.println("Socket closed successfully");
                    break;
                }

                if (messageFromClient.getMessageType().equals(MessageType.FILE_OBJECT)){
                    String message = (String) messageFromClient.getMessage();
                    sendTextToClient(message);
                }

                if (messageFromClient.getMessageType().equals(MessageType.REMOVE_WORDS_FIXED_LENGTH)){
                    RemoveWordModel message = (RemoveWordModel) messageFromClient.getMessage();
                    Text text = removeSentencesFixedLengthService.removeSentences(message);
                    Wrapper wrapper = new Wrapper(MessageType.REMOVE_WORDS_FIXED_LENGTH, text);
                    sendObject(wrapper);
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendTextToClient(String messageFromClient){
        Text text = textParserService.parseText(messageFromClient);
        Wrapper wrapper = new Wrapper(MessageType.FILE_OBJECT, text);
        sendObject(wrapper);
        System.out.println("Sending object...: " + wrapper);
    }



    private void killSocket() {
        try {
            socket.close();
            inputStream.close();
            outputStream.close();
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SocketController.getServerList().remove(this);
    }

    public void sendObject(Object object) {
        try {
            objOut.writeObject(object);
            objOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
