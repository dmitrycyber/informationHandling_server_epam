package by.epamtc.information.controller;

import by.epamtc.information.configuration.annotation.InjectByType;
import by.epamtc.information.configuration.annotation.Singleton;
import by.epamtc.information.service.RemoveSentencesFixedLengthService;
import by.epamtc.information.service.TextParserService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class SocketController {
    @InjectByType
    private TextParserService textParserService;

    @InjectByType
    private RemoveSentencesFixedLengthService removeSentencesFixedLengthService;

    private static final int port = 4004;
    private static List<ServerThread> serverList = new ArrayList<>();

    public void handle() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is running...");
        System.out.println("Listening port: " + port);

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                try {
                    ServerThread serverThread = new ServerThread(
                            socket,
                            textParserService,
                            removeSentencesFixedLengthService);
                    serverList.add(serverThread);
                }
                catch (IOException e) {
                    socket.close();
                }
            }
        }
        finally {
            serverSocket.close();
        }
    }

    public static List<ServerThread> getServerList() {
        return serverList;
    }

}
