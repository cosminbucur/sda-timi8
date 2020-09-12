package com.sda.spring.core.qualifier;

import org.springframework.stereotype.Component;

@Component
public class FooFormatter implements Formatter {

    // knows how to format a string
    public String format(String someString) {
        System.out.println("foo formatting");
        return "foo formatting";
    }
}
