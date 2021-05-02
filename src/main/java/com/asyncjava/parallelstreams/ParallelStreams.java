package com.asyncjava.parallelstreams;
 import com.asyncjava.util.DataSet;

import java.util.List;
 import java.util.stream.Collectors;
 import java.util.stream.Stream;

 import static com.asyncjava.util.CommonUtil.*;
 import static com.asyncjava.util.LoggerUtil.log;

public class ParallelStreams {


    public List<String> stringTransform(List<String> namesList){

        return namesList
                //.stream()
                .parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public List<String> stringTransform_1(List<String> namesList, boolean isParallel){

        Stream<String> namesStream = namesList.stream();

        if(isParallel)
            namesStream.parallel();

        return  namesStream
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {

        List<String> namesList = DataSet.namesList();
        ParallelStreams parallelStreams = new ParallelStreams();
        startTimer();
        List<String> resultList = parallelStreams.stringTransform(namesList);
        log("resultList : " + resultList);
        timeTaken();

    }

    private  String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }
}
