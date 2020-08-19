package by.epamtc.information.dao.impl;

import by.epamtc.information.configuration.annotation.Singleton;
import by.epamtc.information.dao.CustomFileReaderFromExternalFIle;

import java.io.*;

@Singleton
public class CustomFileReaderFromExternalFIleImpl implements CustomFileReaderFromExternalFIle {

    @Override
    public String stringFromFile(String path) {
        String source = null;
        try(Reader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            source = bufferedReader.lines()
                    .map(line -> line + "\n")
                    .reduce((line1, line2) -> line1 + line2)
                    .orElse(null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return source;
    }
}
