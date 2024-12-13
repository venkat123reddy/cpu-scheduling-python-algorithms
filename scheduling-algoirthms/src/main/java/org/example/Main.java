package org.example;

import org.example.algorithms.fcfs.FCFSAlgorithm;
import org.example.algorithms.ps.PrioritySchedulingAlgorithm;
import org.example.algorithms.rb.RoundRobin;
import org.example.algorithms.sjf.ShortestJobAlgorithm;

import java.math.BigInteger;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {

      FCFSAlgorithm.run();
      RoundRobin.run();
      ShortestJobAlgorithm.run();
    }
}