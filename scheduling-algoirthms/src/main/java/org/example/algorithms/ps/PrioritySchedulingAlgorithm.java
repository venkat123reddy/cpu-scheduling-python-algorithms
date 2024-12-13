package org.example.algorithms.ps;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.algorithms.fcfs.FCFSInput;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrioritySchedulingAlgorithm {

    public static List<PriorityScheduleInput> priorityScheduleInputList;
    public static List<Integer> processWaitingTime;

    public static void loadData() {

        String filePath = "src/main/java/org/example/algorithms/ps/PriorityScheduleInput.json";
        ObjectMapper objectMapper = new ObjectMapper();
        processWaitingTime = new ArrayList<>();
        try {

            priorityScheduleInputList = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, PriorityScheduleInput.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void run() {
         Comparator <PriorityScheduleInput>comparator = Comparator.comparing(PriorityScheduleInput::getPriority);

//        priorityScheduleInputList
//                .sort(comparator);

        priorityScheduleInputList.sort(comparator);

        processWaitingTime.add(0);

        priorityScheduleInputList
                .stream()
                .map(PriorityScheduleInput::getBurstTime)
                .forEach(time->{
                    processWaitingTime.add(processWaitingTime.get(processWaitingTime.size()-1)+time);
                });
    }

    public static void print() {

        System.out.println("===========PROCESS ORDER==============");
        priorityScheduleInputList
                .forEach(System.out::println);
        System.out.println("===========AVERAGE WAITING TIME==============");
        processWaitingTime
                .forEach(System.out::println);

    }


}
