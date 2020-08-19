package by.epamtc.information.dao.impl;

import by.epamtc.information.dao.CustomFileReaderFromExternalFIle;
import by.epamtc.information.dao.CustomFileReaderFromLocalFIle;
import by.epamtc.information.dao.InformationDAO;
import by.epamtc.information.dao.Parser;
import by.epamtc.information.configuration.annotation.InjectByType;
import by.epamtc.information.configuration.annotation.Singleton;
import by.epamtc.informationHandle.entity.TextElement;
import by.epamtc.informationHandle.entity.impl.Text;

import java.io.File;
import java.util.List;

@Singleton
public class InformationDAOImpl implements InformationDAO {
    @InjectByType
    private CustomFileReaderFromLocalFIle customFileReaderFromLocalFIle;

    @InjectByType
    private CustomFileReaderFromExternalFIle customFileReaderFromExternalFIle;

    @InjectByType
    private Parser parser;

    @Override
    public Text createTextFromLocalFile() {
        Text text = new Text();
        final String sourceText = customFileReaderFromLocalFIle.stringFromFile();

        List<TextElement> textElements = parser.parseTextElements(sourceText);
        text.setTextElements(textElements);
        return text;
    }

    @Override
    public Text createTextFromExternalFile(String path) {
        Text textObj = new Text();
        final String sourceText = customFileReaderFromExternalFIle.stringFromFile(path);

        List<TextElement> textElements = parser.parseTextElements(sourceText);
        textObj.setTextElements(textElements);
        return textObj;
    }
}
