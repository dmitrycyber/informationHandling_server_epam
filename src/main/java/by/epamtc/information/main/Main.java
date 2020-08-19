package by.epamtc.information.main;

import by.epamtc.information.controller.SocketController;
import java.io.IOException;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = Application.run("by.epamtc.information", new HashMap<>());

        SocketController socketController = context.getObject(SocketController.class);

        socketController.handle();
    }
}
