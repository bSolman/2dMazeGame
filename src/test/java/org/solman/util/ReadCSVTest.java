package org.solman.util;

import org.junit.jupiter.api.Test;
import org.solman.logic.util.ManageFileInput;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReadCSVTest {
    ManageFileInput read = new ManageFileInput();

    @Test
    void saveCsvToListTest() throws IOException {
        read.saveCsvToList(1);
        assertEquals(5, read.getFileAsMap().size());
    }

}