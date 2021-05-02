package com.asyncjava.parallelstreams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.asyncjava.util.CommonUtil.startTimer;
import static com.asyncjava.util.CommonUtil.timeTaken;

public class ArrayListSpliterator {

    public List<Integer> multiplyEachValue(ArrayList<Integer> inputList, int multiplyValue,
                                           boolean isParallel){

        startTimer();
        Stream<Integer> integerStream = inputList.stream(); //sequential

        if(isParallel)
            integerStream.parallel();

        List<Integer> resultList = integerStream
                .map(integer -> integer*multiplyValue)
                .collect(Collectors.toList());

        timeTaken();
        return resultList;
    }

  }
