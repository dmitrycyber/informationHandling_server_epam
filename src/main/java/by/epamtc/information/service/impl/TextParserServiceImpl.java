package by.epamtc.information.service.impl;

import by.epamtc.information.configuration.annotation.InjectByType;
import by.epamtc.information.configuration.annotation.Singleton;
import by.epamtc.information.dao.InformationDAO;
import by.epamtc.information.service.TextParserService;
import by.epamtc.informationHandle.entity.impl.Text;

import java.io.File;

@Singleton
public class TextParserServiceImpl implements TextParserService {

    @InjectByType
    private InformationDAO informationDAO;

    @Override
    public Text parseText(String path) {
        System.out.println("TEXT PARSER SERVICE RUN");
        return informationDAO.createTextFromExternalFile(path);
    }
}
