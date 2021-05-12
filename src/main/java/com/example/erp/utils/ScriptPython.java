package com.example.erp.utils;
import java.io.*;

public class ScriptPython {

    public void runPython(String arg1,String arg2) {
        System.out.println("Printing");
        String[] cmd = {
                "python3",
                "/home/rahul/Documents/AI-on-the-edge/src/main/java/com/example/erp/utils/load_balancer.py",
                arg1,
                arg2,
        };
        try {
            Runtime.getRuntime().exec("python3"+" "+"/./mnt/nfs_share/newfolder/AI-on-the-edge/src/main/java/com/example/erp/utils/load_balancer.py "+arg1+" "+arg2);
            System.out.println("Executed");
        } catch (IOException e) {
            System.out.println("Not executed");
            e.printStackTrace();
        }
    }

}
//class Solution {
//    public void run_python(String arg){
//         ScriptPython scriptPython = new ScriptPython();
////        scriptPython.runScript();
//          ScriptPython.runPython(arg);
//
//    }
//
//}
