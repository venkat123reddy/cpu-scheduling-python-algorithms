package org.example.algorithms.sjf;

import lombok.Data;

@Data
public class ShortestJobInput {
    private String processName;
    private Integer burstTime;
}
