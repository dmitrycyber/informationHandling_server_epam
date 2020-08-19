package by.epamtc.information.dao;


import by.epamtc.informationHandle.entity.SentenceElement;
import by.epamtc.informationHandle.entity.TextElement;
import by.epamtc.informationHandle.entity.impl.Sentence;

import java.util.List;

public interface Parser {
    List<TextElement> parseTextElements(String string);

    List<Sentence> parseSentence(String string);

    List<SentenceElement> parseSentenceElements(String string);



}
