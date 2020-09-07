package by.epamtc.information.dao.impl;

import by.epamtc.information.dao.Parser;
import by.epamtc.information.configuration.annotation.InjectProperty;
import by.epamtc.information.configuration.annotation.Singleton;
import by.epamtc.informationHandle.entity.SentenceElement;
import by.epamtc.informationHandle.entity.TextElement;
import by.epamtc.informationHandle.entity.impl.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class TextElementParser implements Parser {

    @InjectProperty("regex.textElement")
    private String textElementRegex;

    @InjectProperty("regex.sentence")
    private String sentenceRegex;

    @InjectProperty("regex.sentenceElement")
    private String sentenceElementRegex;

    @Override
    public List<TextElement> parseTextElements(String string) {
        List<TextElement> textElements = new ArrayList<>();

        Pattern pattern = Pattern.compile(textElementRegex);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()){
            String titleText = matcher.group("Title");
            String paragraphText = matcher.group("Paragraph");
            String codeBlockText = matcher.group("CodeBlock");

            if (titleText != null){
                Title title = new Title();
                List<SentenceElement> sentenceElements = parseSentenceElements(titleText);

                List<Sentence> sentenceList = new ArrayList<>();

                Sentence sentence = new Sentence();

                sentence.setSentenceElements(sentenceElements);
                sentenceList.add(sentence);

                title.setSentences(sentenceList);
                textElements.add(title);
            }
            if (paragraphText != null){
                List<Sentence> sentences = parseSentence(paragraphText);
                Paragraph paragraph = new Paragraph();
                paragraph.setSentences(sentences);
                textElements.add(paragraph);
            }
            if (codeBlockText != null){
                CodeBlock codeBlock = new CodeBlock();
                codeBlock.setCodeBlock(codeBlockText);
                textElements.add(codeBlock);
            }
        }
        return textElements;
    }

    @Override
    public List<Sentence> parseSentence(String string) {
        List<Sentence> sentences = new ArrayList<>();

        Pattern pattern = Pattern.compile(sentenceRegex);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()){
            String group = matcher.group();
            if (group != null){
                String textSentence = group.trim();
                List<SentenceElement> sentenceElements = parseSentenceElements(textSentence);
                Sentence sentence = new Sentence();
                sentence.setSentenceElements(sentenceElements);
                sentences.add(sentence);
            }
        }

        return sentences;
    }

    @Override
    public List<SentenceElement> parseSentenceElements(String string) {
        List<SentenceElement> sentenceElements = new ArrayList<>();

        Pattern pattern = Pattern.compile(sentenceElementRegex);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()){
            String word = matcher.group("Word");
            String mark = matcher.group("Mark");

            if (word != null){
                sentenceElements.add(new Word(word));
            }

            if (mark != null){
                sentenceElements.add(new PunctuationMark(mark));
            }
        }
        return sentenceElements;
    }
}
