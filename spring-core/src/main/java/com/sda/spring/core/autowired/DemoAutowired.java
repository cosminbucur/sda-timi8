package com.sda.spring.core.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoAutowired {

    public static void main(String[] args) {
//        withoutSpring();

        // create context
        ApplicationContext context =
            new AnnotationConfigApplicationContext(AutowiredConfig.class);

        // get component
        TextEditor textEditor = context.getBean("textEditor", TextEditor.class);

        // use component
        textEditor.format();
        textEditor.spellCheck();
        textEditor.convertImage();
    }

    private static void withoutSpring() {
        SpellChecker spellChecker = new SpellChecker();
        TextEditor textEditor = new TextEditor(spellChecker);
    }
}
