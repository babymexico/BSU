package com.example.coursework;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonSimpleParserTest {

    @Test
    void parse() {
        List<String> expected = new ArrayList<>();
        expected.add("4 + 3 * 4");
        expected.add("3 + 2 - 7");
        expected.add("4 * (5 - 6) / 3");

        JsonSimpleParser parser = new JsonSimpleParser();
        assertEquals(expected, parser.parse("json_input.json"));
    }
}