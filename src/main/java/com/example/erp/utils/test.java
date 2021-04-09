package com.example.erp.utils;

import javax.script.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

public class test {
    public static void main(String[] args) throws ScriptException, IOException {

        StringWriter writer = new StringWriter(); //ouput will be stored here

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptContext context = new SimpleScriptContext();

        context.setWriter(writer); //configures output redirection
        ScriptEngine engine = manager.getEngineByName("python3");
        engine.eval(new FileReader("abc.py"), context);
        System.out.println(writer.toString());
    }
}
