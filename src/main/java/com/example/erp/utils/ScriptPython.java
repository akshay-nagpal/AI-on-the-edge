package com.example.erp.utils;
import java.io.*;

public class ScriptPython {

    public static void runPython(String arg1) {
        System.out.println("Printing");
        String[] cmd = {
                "python3",
                "/home/sachin/Desktop/spe_major/AI-on-the-edge/src/main/java/com/example/erp/utils/load_balencer.py",
                arg1,
        };
        try {
            Runtime.getRuntime().exec(cmd);
            System.out.println("Executed");
        } catch (IOException e) {
            System.out.println("Not executed");
            e.printStackTrace();
        }
    }

}
class Solution {
    public static void main(String[] args){
         ScriptPython scriptPython = new ScriptPython();
//        scriptPython.runScript();
          ScriptPython.runPython("");

    }

}