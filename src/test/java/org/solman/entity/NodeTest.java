package org.solman.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(NodeResolver.class)
@DisplayName("Node tests")
class NodeTest {

    Node target = new Node(0,3, true, 1);
    @ParameterizedTest
    @CsvFileSource(resources = "/manhattanTest.csv")
    void calculateManhattanDistanceTest(int x, int y, int expected) {
        Node start = new Node(x, y, true, 1);
        start.calculateManhattanDistance(target);
        assertEquals(expected, start.getManhattanDistance());
    }
}