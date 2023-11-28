package com.example.CSVReader.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathSanitiserTest {

    @Test
    void PathSanitiser_RemovesDoubleQuotes() {
        String input1 = "\"E:\\src\\file.txt\"";
        String expectedOutput1 = "E:\\\\src\\\\file.txt";
        assertEquals(expectedOutput1, PathSanitiser.sanitisePath(input1));
    }

    @Test
    void PathSanitiser_RemovesSingleQuotes() {
        String input1 = "\'E:\\src\\file.txt\'";
        String expectedOutput1 = "E:\\\\src\\\\file.txt";
        assertEquals(expectedOutput1, PathSanitiser.sanitisePath(input1));
    }

    @Test
    void PathSanitiser_HandlesNoQuotes() {
        String input1 = "E:\\src\\file.txt";
        String expectedOutput1 = "E:\\\\src\\\\file.txt";
        assertEquals(expectedOutput1, PathSanitiser.sanitisePath(input1));
    }

    @Test
    void PathSanitiser_HandlesForwardSlash() {
        String input1 = "E:/src/file.txt";
        String expectedOutput1 = "E:/src/file.txt";
        assertEquals(expectedOutput1, PathSanitiser.sanitisePath(input1));
    }

}