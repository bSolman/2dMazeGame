package org.solman.logic.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.solman.entity.Node;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CreateMapResolver.class)
@DisplayName("Create map tests")
class CreateMapTest {
    static CreateMap createMap = new CreateMap(0);

    @Test
    @BeforeAll
    static void prepLogicTests() throws IOException {
        List<String> mapAsString = createMap.getMapAsString();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/neighbourTest.csv")
    @DisplayName("Count neighbour test")
    void countNeighbourTest(int x, int y, int expected){
    }

    @Test
    void createCPURoadMap() throws IOException {
        Node start = new Node(4,9,true,1);
        Node end = new Node(7,0,true,2);
        assertEquals(21, createMap.getCpuShortestPath(start, end, createMap.getNodes()).size());
    }

    @Test
    void testNodeListSize(){
        assertEquals(10, createMap.getNodes().size());
    }

}