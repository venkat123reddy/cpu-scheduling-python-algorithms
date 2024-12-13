package org.example.algorithms.rb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.algorithms.sjf.ShortestJobInput;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RoundRobin {

     public static List<RoundRobinInput> roundRobinInputList = new LinkedList<>();
     public static List<Integer> processWaiting;
    public static List<Integer> turnOverTime;
     public static List<String> process = new ArrayList<>();
     public static int QUANTUM = 2;
     public static Map<String,Integer> map = new HashMap<>();

     private static void loadData() {
         String filePath = "src/main/java/org/example/algorithms/rb/RoundRobinInput.json";
         ObjectMapper objectMapper = new ObjectMapper();

         processWaiting = new ArrayList<>();
         turnOverTime = new ArrayList<>();
         try {

             roundRobinInputList = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(Queue.class, RoundRobinInput.class));
         } catch (IOException e) {
             e.printStackTrace();
         }
         roundRobinInputList.forEach(inp->map.put(inp.getProcessName(),inp.getBurstTime()));
     }

     public static void run() {

         loadData();
         int currentTime = 0;
         while(!roundRobinInputList.isEmpty()) {

             RoundRobinInput roundRobinInput = roundRobinInputList.remove(0);

             System.out.println(roundRobinInput.getProcessName());
             if(roundRobinInput.getBurstTime()<=QUANTUM) {
                 currentTime+=roundRobinInput.getBurstTime();
                 turnOverTime.add(currentTime);
                 process.add(roundRobinInput.getProcessName());
                 processWaiting.add(currentTime-map.get(roundRobinInput.getProcessName()));
             }
             else {
                 currentTime+=QUANTUM;
                 roundRobinInput.setBurstTime(roundRobinInput.getBurstTime()-QUANTUM);
                 roundRobinInputList.add(roundRobinInput);
             }

         }
         print();
     }

     private static void print() {

         int currentTime = 0;
         for(int i =0;i<processWaiting.size();i++) {

             currentTime+=processWaiting.get(i);
             System.out.println(process.get(i)+"  "+turnOverTime.get(i)+"  "+processWaiting.get(i));
         }

         float avg = (float)currentTime/processWaiting.size();

         System.out.println("RB AVG Waiting Time : "+avg);

     }
}
