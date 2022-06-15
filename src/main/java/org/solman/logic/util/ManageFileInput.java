package org.solman.logic.util;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ManageFileInput {
    List<String> csvAsList;

    public ManageFileInput(){
    }

    public void saveCsvToList(int value) throws IOException {
        //todo create a file chooser.
        InputStream inputStream = this.getClass().getResourceAsStream("/map2.txt");
        assert inputStream != null;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Stream<String> lines = bufferedReader.lines();
        csvAsList = lines.collect(Collectors.toList());
    }

    public String getMapSize(){
        return csvAsList.get(0);
    }

    public String getStartCoordinates(){
        return csvAsList.get(1);
    }

    public String getTargetCoordinate(){
        return csvAsList.get(2);
    }

    public List<String> getFileAsMap(){
        return IntStream
                .range(3, csvAsList.size())
                .mapToObj(i -> csvAsList.get(i))
                .collect(Collectors.toList());
    }
}
