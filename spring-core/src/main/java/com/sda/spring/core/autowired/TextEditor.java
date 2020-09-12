package com.sda.spring.core.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TextEditor {

    // dependency injection: 3 options

    private SpellChecker spellChecker;

    // 2. field injection is evil
    @Autowired
    private TextFormatter textFormatter;

    private ImageConverter imageConverter;

    // 1. constructor injection
    @Autowired
    public TextEditor(SpellChecker newSpellChecker) {
        this.spellChecker = newSpellChecker;
    }

    // 3. setter injection

    // take a new image converter
    // and put it on the text editor
    // command vs query
    @Autowired
    public void setImageConverter(ImageConverter imageConverter) {
        this.imageConverter = imageConverter;
    }

    // delegation
    public void spellCheck() {
        spellChecker.checkSpelling();
    }

    public void format() {
        textFormatter.formatText();
    }

    public void convertImage() {
        imageConverter.convert();
    }
}
