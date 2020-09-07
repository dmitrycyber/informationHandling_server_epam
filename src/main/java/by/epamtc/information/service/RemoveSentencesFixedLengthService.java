package by.epamtc.information.service;

import by.epamtc.informationHandle.entity.impl.Text;
import by.epamtc.informationHandle.models.RemoveWordModel;

public interface RemoveSentencesFixedLengthService {
    Text removeSentences(RemoveWordModel removeWordModel);
}
