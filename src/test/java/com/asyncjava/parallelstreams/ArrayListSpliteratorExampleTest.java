package com.asyncjava.parallelstreams;

import com.asyncjava.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListSpliteratorExampleTest {

    ArrayListSpliterator arrayListSpliterator = new ArrayListSpliterator();

    @RepeatedTest(5)
    void multiplyEachValue() {
        //given
            int size = 1000000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);


        //when
        List<Integer> resultList  = arrayListSpliterator.multiplyEachValue(inputList, 2,false);


        //then
        assertEquals(size, resultList.size());
    }

    @RepeatedTest(5)
    void multiplyEachValue_parallel() {
        //given
        int size = 1000000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);


        //when
        List<Integer> resultList  = arrayListSpliterator.multiplyEachValue(inputList, 2,true);


        //then
        assertEquals(size, resultList.size());
    }
}