package com.asyncjava.parallelstreams;

import com.asyncjava.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListSpliteratorTest {

    LinkedListSpliterator linkedListSpliterator
            = new LinkedListSpliterator();


    @RepeatedTest(5)
    void multiplyEachValue() {
        //given
        int size = 1000000;
        LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);


        //when
        List<Integer> resultList  = linkedListSpliterator.multiplyEachValue(inputList, 2,false);


        //then
        assertEquals(size, resultList.size());
    }

    @RepeatedTest(5)
    void multiplyEachValue_parallel() {
        //given
        int size = 1000000;
        LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);


        //when
        List<Integer> resultList  = linkedListSpliterator.multiplyEachValue(inputList, 2,true);


        //then
        assertEquals(size, resultList.size());
    }
}