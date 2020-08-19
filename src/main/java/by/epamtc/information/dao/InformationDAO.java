package by.epamtc.information.dao;


import by.epamtc.informationHandle.entity.impl.Text;

import java.io.File;
import java.util.List;

public interface InformationDAO {
    Text createTextFromLocalFile();
    Text createTextFromExternalFile(String path);
}
