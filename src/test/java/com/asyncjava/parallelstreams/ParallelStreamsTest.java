package com.asyncjava.parallelstreams;

import com.asyncjava.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.List;

import static com.asyncjava.util.CommonUtil.startTimer;
import static com.asyncjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamsTest {

    ParallelStreams parallelStreams = new ParallelStreams();

    @Test
    void stringTransform() {
        //given
        List<String> inputList = DataSet.namesList();

        //when
        startTimer();
        List<String> resultList = parallelStreams.stringTransform(inputList);
        timeTaken();

        //then
        assertEquals(4, resultList.size());
        resultList.forEach(name->{
            assertTrue(name.contains("-"));
        });

    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void stringTransform_1(boolean isParallel) {
        //given
        List<String> inputList = DataSet.namesList();

        //when
        startTimer();
        List<String> resultList = parallelStreams.stringTransform_1(inputList,isParallel);
        timeTaken();

        //then
        assertEquals(4, resultList.size());
        resultList.forEach(name->{
            assertTrue(name.contains("-"));
        });

    }


}