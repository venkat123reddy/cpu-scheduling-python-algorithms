package org.example.algorithms.sjf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.algorithms.ps.PriorityScheduleInput;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShortestJobAlgorithm {

    public static List<ShortestJobInput> shortestJobInputList = new ArrayList<>();

    public static List<Integer> processWaitingTime;
    public static List<Integer> processTurnOverTime;

    public static void loadData() {

        String filePath = "src/main/java/org/example/algorithms/sjf/ShortestJobInput.json";
        ObjectMapper objectMapper = new ObjectMapper();

        processWaitingTime = new ArrayList<>();
        processTurnOverTime = new ArrayList<>();
        try {

            shortestJobInputList = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, ShortestJobInput.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void run() {
        loadData();
        Comparator<ShortestJobInput> shortestJobInputComparator = Comparator.comparing(ShortestJobInput::getBurstTime);

        shortestJobInputList.sort(shortestJobInputComparator);

        int currentTime = 0;
        for(int i =0;i<shortestJobInputList.size();i++) {
            processWaitingTime.add(currentTime);
            currentTime+=shortestJobInputList.get(i).getBurstTime();
            processTurnOverTime.add(currentTime);
        }
        print();
    }

    public static void print() {
       int currentTime = 0;
       for(int i = 0;i<shortestJobInputList.size();i++) {
           currentTime+=processWaitingTime.get(i);
           System.out.println(shortestJobInputList.get(i)+"  "+processWaitingTime.get(i)+" "+processTurnOverTime.get(i));
       }
       float avg = (float)currentTime/shortestJobInputList.size();
       System.out.println("SJF AVG TIME : "+avg);

    }
}
