package com.example.assignmentseven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Scoreboard {
    private PriorityQueue<Integer> queue;
    private int thisTime = 100;
    private static Scoreboard instance = new Scoreboard();

    private Scoreboard(){
        queue = new PriorityQueue<>(5, Collections.<Integer>reverseOrder());
    }

    public static Scoreboard getInstance(){
        return instance;
    }
    public void update(int num){
        thisTime = num;
        queue.add(num);
    }
    public String[] printout(){
        ArrayList<String> printer = new ArrayList<>();
        if(thisTime != 100){
            String first = "THIS TIME ： "+thisTime;
            printer.add(first);
        }
        printer.add("RANKING LIST");
        String[] output = new String[printer.size() + queue.size()];
        String[] p = new String[printer.size()];
        p = printer.toArray(p);
        Integer[] q = new Integer[queue.size()];
        if(queue.peek() != null)
            q = queue.toArray(q);
        System.arraycopy(p, 0, output, 0, p.length);
        for(int i=0; i<q.length; i++){
            output[p.length+i] = Integer.toString(q[i]);
        }
        thisTime = 100;
        return output;
    }
}
