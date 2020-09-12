package com.sda.spring.core.autowired;

import org.springframework.stereotype.Component;

@Component
public class TextFormatter {

    public void formatText() {
        System.out.println("formatting text");
    }
}
