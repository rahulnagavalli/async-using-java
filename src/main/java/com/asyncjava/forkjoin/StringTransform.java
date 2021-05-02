package com.asyncjava.forkjoin;

import com.asyncjava.util.DataSet;

import java.util.ArrayList;
import java.util.List;

import static com.asyncjava.util.CommonUtil.delay;
import static com.asyncjava.util.CommonUtil.stopWatch;
import static com.asyncjava.util.LoggerUtil.log;

public class StringTransform {

    public static void main(String[] args) {

        stopWatch.start();
        List<String> resultList = new ArrayList<>();
        List<String> names = DataSet.namesList();
        names.forEach((name)->{
            String newValue = addNameLengthTransform(name);
            resultList.add(newValue);
        });
        stopWatch.stop();
        log("Final Result : "+ resultList);
        log("Total Time Taken : "+ stopWatch.getTime());
    }


    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }
}
