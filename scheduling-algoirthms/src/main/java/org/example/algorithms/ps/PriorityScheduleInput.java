package org.example.algorithms.ps;


import lombok.Data;

@Data
public class PriorityScheduleInput {
    private String processName;
    private Integer burstTime;
    private Integer priority;
}
