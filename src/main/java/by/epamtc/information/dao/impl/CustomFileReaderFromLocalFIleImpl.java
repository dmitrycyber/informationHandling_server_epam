package by.epamtc.information.dao.impl;

import by.epamtc.information.configuration.annotation.InjectProperty;
import by.epamtc.information.dao.CustomFileReaderFromLocalFIle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CustomFileReaderFromLocalFIleImpl implements CustomFileReaderFromLocalFIle {

    @InjectProperty("filename")
    private String fileName;

    @Override
    public String stringFromFile(){
        String source = null;
        try(Reader reader = new FileReader(CustomFileReaderFromLocalFIleImpl.class.getClassLoader().getResource(fileName).getPath());
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
