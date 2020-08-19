package by.epamtc.information.service.impl;

import by.epamtc.information.configuration.annotation.Singleton;
import by.epamtc.information.service.TextPrinterService;
import by.epamtc.informationHandle.entity.SentenceElement;
import by.epamtc.informationHandle.entity.TextElement;
import by.epamtc.informationHandle.entity.impl.*;

@Singleton
public class TextPrinterServiceImpl implements TextPrinterService {
    
    @Override
    public void printText(Text text) {
        for (TextElement textElement : text.getTextElements()) {
            if (textElement.getClass() == Title.class) {
                Title title = (Title) textElement;
                Sentence sentence1 = title.getSentence();
                for (SentenceElement sentenceElement : sentence1.getSentenceElements()) {
                    if (sentenceElement.getClass() == Word.class) {
                        System.out.print(sentenceElement.getString() + " ");
                    } else {
                        System.out.print(sentenceElement.getString());
                    }
                }
                System.out.println();
            }

            if (textElement.getClass() == Paragraph.class) {
                Paragraph paragraph = (Paragraph) textElement;
                for (Sentence sentence : paragraph.getSentences()) {
                    for (SentenceElement sentenceElement : sentence.getSentenceElements()) {
                        if (sentenceElement.getClass() == Word.class) {
                            System.out.print(sentenceElement.getString() + " ");
                        } else {
                            System.out.print(sentenceElement.getString());
                        }
                    }
                }
                System.out.println();
            }
            if (textElement.getClass() == CodeBlock.class){
                System.out.println(textElement.getString());
            }
        }
    }
}
