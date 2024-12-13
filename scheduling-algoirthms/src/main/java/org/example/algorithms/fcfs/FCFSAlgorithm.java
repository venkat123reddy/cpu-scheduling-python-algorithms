package org.example.algorithms.fcfs;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FCFSAlgorithm {
    public static List<FCFSInput> fcfsInputList;
    public static List<String> processOrder;
    public static List<Integer> processWaitTime;
    public static List<Integer> processturnOverTime;


    public static void dataLoad() {

        String filePath = "src/main/java/org/example/algorithms/fcfs/FCFSInputData.json";
        ObjectMapper objectMapper = new ObjectMapper();
        processWaitTime = new ArrayList<>();
        processturnOverTime = new ArrayList<>();
        try {

            fcfsInputList = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, FCFSInput.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer getTopElement(List<Integer> arr) {
        int length = arr.size();
        return arr.get(length-1);
    }
    public static void run() {
        dataLoad();
        int currentTIme = 0;
        for(int i=0;i<fcfsInputList.size();i++) {
            processWaitTime.add(currentTIme);
            currentTIme+=fcfsInputList.get(i).burstTime;
            processturnOverTime.add(currentTIme);
        }
        print();
    }
    public static void print() {
        int currentTime = 0;
      for(int i=0;i<fcfsInputList.size();i++) {
          currentTime+=processWaitTime.get(i);
          System.out.println(fcfsInputList.get(i).processName+"  "+processWaitTime.get(i)+"  "+processturnOverTime.get(i));
      }

      float avg = (float)currentTime/fcfsInputList.size();

      System.out.println("FCFS AVERAGE WAITING TIME : "+avg);
    }



}
