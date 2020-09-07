package by.epamtc.information.service.impl;

import by.epamtc.information.service.RemoveSentencesFixedLengthService;
import by.epamtc.information.util.ConsonantLetterChecker;
import by.epamtc.informationHandle.entity.SentenceElement;
import by.epamtc.informationHandle.entity.TextElement;
import by.epamtc.informationHandle.entity.impl.CodeBlock;
import by.epamtc.informationHandle.entity.impl.Sentence;
import by.epamtc.informationHandle.entity.impl.Text;
import by.epamtc.informationHandle.entity.impl.Word;
import by.epamtc.informationHandle.models.RemoveWordModel;

import java.util.Iterator;
import java.util.List;

public class RemoveSentencesFixedLengthServiceImpl implements RemoveSentencesFixedLengthService {

    @Override
    public Text removeSentences(RemoveWordModel removeWordModel) {
        int length = removeWordModel.getLength();
        Text text = removeWordModel.getText();


        List<TextElement> textElements = text.getTextElements();

        for (TextElement textElement : textElements){
            List<Sentence> sentences = textElement.getSentences();
            if (sentences != null){
                for (Sentence sentence : sentences){
                    List<SentenceElement> sentenceElements = sentence.getSentenceElements();

//                    for (Iterator<DrugStrength> it = aDrugStrengthList.iterator(); it.hasNext(); ) {
//                        DrugStrength aDrugStrength = it.next();
//                        if (!aDrugStrength.isValidDrugDescription()) {
//                            it.remove();
//                        }
//                    }

                    for (Iterator<SentenceElement> it = sentenceElements.iterator(); it.hasNext(); ) {
                        SentenceElement sentenceElement = it.next();
                        if (sentenceElement.getClass().equals(Word.class)){
                            String word = sentenceElement.getString();
                            char firstLetter = word.charAt(0);
                            if (word.length() == length && ConsonantLetterChecker.check(firstLetter)){
                                it.remove();
                            }
                        }
                    }


//                    for (SentenceElement sentenceElement : sentenceElements){
//                        if (sentenceElement.getClass().equals(Word.class)){
//                            String word = sentenceElement.getString();
//                            char firstLetter = word.charAt(0);
//                            if (word.length() == length && ConsonantLetterChecker.check(firstLetter)){
//                                sentenceElements.remove(sentenceElement);
//                            }
//                        }
//                    }

                }
            }
        }
        return text;
    }


}
