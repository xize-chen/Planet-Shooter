package com.example.assignmentseven;

import java.util.ArrayList;
import java.util.Collections;

public class Scoreboard {
    private ArrayList<Integer> queue;
    private int thisTime = 100;
    private static Scoreboard instance = new Scoreboard();

    private Scoreboard(){
        queue = new ArrayList<>();
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
        String[] output;
        if(queue.size()<=5)
            output = new String[printer.size() + queue.size()];
        else output = new String[printer.size() + 5];
        String[] p = new String[printer.size()];
        p = printer.toArray(p);
        System.arraycopy(p, 0, output, 0, p.length);
        Integer[] q;
        if(!queue.isEmpty()){
            q = new Integer[queue.size()];
            Collections.sort(queue);
            Collections.reverse(queue);
            q = queue.toArray(q);
            for(int i=0; i<q.length & i<5; i++){
                output[p.length+i] = Integer.toString(q[i]);
            }
        }
        thisTime = 100;
        return output;
    }
}
